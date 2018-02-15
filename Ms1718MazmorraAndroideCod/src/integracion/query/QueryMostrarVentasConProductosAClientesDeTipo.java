package integracion.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import integracion.gestorTransaccion.GestorTransaccion;
import integracion.transaccion.Transaccion;

import negocio.venta.LineaVenta;
import negocio.venta.TransferVenta;

public class QueryMostrarVentasConProductosAClientesDeTipo implements Query {

	@Override
	public Object execute(Object datos) throws Exception {
		Transaccion transaccion = GestorTransaccion.obtenerInstancia().obtenerTransaccion();
		Connection conexion = (Connection) transaccion.getResource();
		
		List<TransferVenta> ventas = new ArrayList<>();
		TransferVenta venta = null;
		int idProducto = ((DatosQuery) datos).getIdProducto();
		String tipoCliente = ((DatosQuery) datos).getTipoCliente();
		
		try {
			String query = "SELECT id, v.precio AS precio, fecha, idCliente "
						+ "FROM Venta v, LineaVenta lv "
						+ "WHERE v.id = lv.id_venta "
						+ "AND lv.id_producto = " + idProducto + " "
						+ "AND v.idCliente IN (SELECT id "
						+ 					  "FROM Cliente c "
						+					  "WHERE c.tipo = '" + tipoCliente + "')";
			
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				String queryProductosEnLaVenta = "SELECT p.id AS id, lv.cantidad AS cantidad "
											  +  "FROM Producto p, Venta v, LineaVenta lv "
											  +  "WHERE v.id = lv.id_venta "
											  +  "AND p.id = lv.id_producto "
											  +  "AND v.id = " + resultSet.getInt("id");
				
				preparedStatement = conexion.prepareStatement(queryProductosEnLaVenta);
				ResultSet resultSetProductos = preparedStatement.executeQuery();
				HashMap<Integer, LineaVenta> productos = new HashMap<>();
				
				while (resultSetProductos.next()) {
					productos.put(resultSetProductos.getInt("id"), new LineaVenta(resultSetProductos.getInt("id"), 
																				 resultSetProductos.getInt("cantidad")));
				}
										  
											  
				venta = new TransferVenta(resultSet.getInt("id"), 
										  resultSet.getInt("idCliente"), 
										  resultSet.getString("fecha"), 
										  resultSet.getDouble("precio"), 
										  productos);
				
				ventas.add(venta);
			}
			
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}
		
		return ventas;
	}

}
