package integracion.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import integracion.gestorTransaccion.GestorTransaccion;
import integracion.transaccion.Transaccion;

import negocio.producto.TransferProducto;

public class QueryMostrarProductosEnVentasSuperioresAUnPrecio implements Query {

	@Override
	public Object execute(Object datos) throws Exception {
		double cantidad = (double) datos;
		Transaccion transaccion = GestorTransaccion.obtenerInstancia().obtenerTransaccion();
		Connection conexion = (Connection) transaccion.getResource();
		List<TransferProducto> productos = new ArrayList<>();
		TransferProducto producto = null;
		String query = "SELECT * "
					 + "FROM Producto "
					 + "WHERE id IN (SELECT id_producto "
					 + 				"FROM lineaventa lv, venta v "
					 + 				"WHERE lv.id_venta = v.id "
					 +				"AND v.precio > " + cantidad + ")";
	
		try {
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				producto = new TransferProducto(
						rs.getInt("id"),
						rs.getBoolean("activo"),
						rs.getInt("precio"),
						rs.getInt("stock"),
						rs.getInt("sistema_operativo"),
						rs.getString("nombre"));
				
				productos.add(producto);
			}	
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}
		
		return productos;
	}

}
