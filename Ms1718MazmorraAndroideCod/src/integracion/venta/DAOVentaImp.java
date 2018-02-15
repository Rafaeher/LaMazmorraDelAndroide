package integracion.venta;

import java.sql.Connection;
import java.sql.DriverManager;
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

public class DAOVentaImp implements DAOVenta {
	
	@Override
	public TransferVenta read(int id) throws Exception {
		Transaccion transaccion = GestorTransaccion.obtenerInstancia().obtenerTransaccion(); 
		Connection conexion = null;
		TransferVenta venta = null;
		String forUpdateString;
		
		if (transaccion == null) {
			conexion = getConnection();
			forUpdateString = "";
		} else {
			conexion = (Connection) transaccion.getResource();
			forUpdateString = " FOR UPDATE";
		}
		
		try {
			String query = "SELECT id, precio, fecha, idCliente "
					 + "FROM Venta "
					 + "WHERE id = " + id + forUpdateString;
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				String queryProductosEnLaVenta = "SELECT lv.id_producto AS id, lv.cantidad AS cantidad, lv.precio AS precio "
						  +  "FROM Venta v, LineaVenta lv "
						  +  "WHERE v.id = lv.id_venta "
						  +  "AND v.id = " + id + forUpdateString;
				preparedStatement = conexion.prepareStatement(queryProductosEnLaVenta);
				ResultSet resultSetProductos = preparedStatement.executeQuery();
				HashMap<Integer, LineaVenta> productos = new HashMap<>();
				
				while (resultSetProductos.next()) {
					productos.put(resultSetProductos.getInt("id"), new LineaVenta(resultSetProductos.getInt("id"), 
																				 resultSetProductos.getInt("cantidad"),
																				 resultSetProductos.getDouble("precio")));
				}
										  
											  
				venta = new TransferVenta(id, 
										  resultSet.getInt("idCliente"), 
										  resultSet.getString("fecha"), 
										  resultSet.getDouble("precio"), 
										  productos);
			}
			
			if (transaccion == null) {
				conexion.close();
			}
			
		} catch(Exception ex) {
			throw new Exception("Error en la conexion a la Base de datos");
		}
		
		return venta;
	}

	@Override
	public List<TransferVenta> readAll() throws Exception {
		Transaccion  transaccion = GestorTransaccion.obtenerInstancia().obtenerTransaccion();
		Connection conexion = null;
		List<TransferVenta> ventas = new ArrayList<>();
		TransferVenta venta = null;
		String forUpdateString;
		
		if (transaccion == null) {
			 conexion = this.getConnection();
			 forUpdateString = "";
		} else {
			conexion = (Connection) transaccion.getResource();
			forUpdateString = " FOR UPDATE";
		}
		
		try {
			String query = "SELECT id, precio, fecha, idCliente "
						 + "FROM Venta" + forUpdateString;
			
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				String queryProductosEnLaVenta = "SELECT p.id AS id, lv.cantidad AS cantidad, lv.precio AS precio "
											  +  "FROM Producto p, Venta v, LineaVenta lv "
											  +  "WHERE v.id = lv.id_venta "
											  +  "AND p.id = lv.id_producto "
											  +  "AND v.id = " + resultSet.getInt("id") + forUpdateString;
				
				preparedStatement = conexion.prepareStatement(queryProductosEnLaVenta);
				ResultSet resultSetProductos = preparedStatement.executeQuery();
				HashMap<Integer, LineaVenta> productos = new HashMap<>();
				
				while (resultSetProductos.next()) {
					productos.put(resultSetProductos.getInt("id"), new LineaVenta(resultSetProductos.getInt("id"),
																				 resultSetProductos.getInt("cantidad"),
																				 resultSetProductos.getDouble("precio")));
				}
										  
											  
				venta = new TransferVenta(resultSet.getInt("id"), 
										  resultSet.getInt("idCliente"), 
										  resultSet.getString("fecha"), 
										  resultSet.getDouble("precio"), 
										  productos);
				
				ventas.add(venta);
			}
			
			if (transaccion == null) {
				conexion.close();
			}
			
		} catch(Exception ex) {
			throw new Exception("Error en la conexion a la Base de datos");
		}
		
		return ventas;
	}

	@Override
	public int write(TransferVenta venta) throws Exception {
		Transaccion transaccion = GestorTransaccion.obtenerInstancia().obtenerTransaccion();
		Connection conexion = (Connection) transaccion.getResource();
		Integer id = -1;
		
		try {
			String queryVenta = "INSERT INTO Venta(precio, fecha, idCliente) VALUES (" + venta.getPrecio() + 
																				   ", '" + venta.getFecha() + "', " + 
																				   venta.getIdCliente() + " )";
			
			PreparedStatement preparedStatement = conexion.prepareStatement(queryVenta);
			preparedStatement.executeUpdate();
			
			preparedStatement = conexion.prepareStatement("SELECT LAST_INSERT_ID()");
			ResultSet resultSetId = preparedStatement.executeQuery();
			
			if (resultSetId.next()) {
				id = resultSetId.getInt(1);
			}
			
			for (LineaVenta lineaVenta : venta.getProductos().values()) {
				String queryLineasVenta = "INSERT INTO LineaVenta(id_venta, id_producto, precio, cantidad) "
									    + "VALUES (" + id + ", " + lineaVenta.getId() + ", " 
									    	+ lineaVenta.getPrecio() + ", " + lineaVenta.getCantidad() + ")";
				
				preparedStatement = conexion.prepareStatement(queryLineasVenta);
				preparedStatement.executeUpdate();
			}

		} catch (SQLException ex) {
			throw new Exception("Error en la conexion a la Base de datos");
		}
	
		return id;
	}

	@Override
	public int update(TransferVenta venta) throws Exception {
		Transaccion transaccion = GestorTransaccion.obtenerInstancia().obtenerTransaccion(); 
		Connection conexion = (Connection) transaccion.getResource();
		
		try {
			String query = "UPDATE Venta "
						 + "SET precio = " + venta.getPrecio() + ", "
						 + "fecha = '" + venta.getFecha() + "', " 
						 + "idCliente = " + venta.getIdCliente() + " "
						 + "WHERE id = " + venta.getIdVenta();
			
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.executeUpdate();
				
			for (LineaVenta lineaVenta : venta.getProductos().values()) {
				String queryActualizarLineaVenta = "UPDATE LineaVenta "
									    			+ "SET cantidad = " + lineaVenta.getCantidad() + " "
									    			+ "WHERE id_producto = " + lineaVenta.getId() + " "
									    			+ "AND id_venta = " + venta.getIdVenta();
					
				String queryEliminarLineaVenta = "DELETE FROM LineaVenta "
											   + "WHERE id_producto = " + lineaVenta.getId() + " "
											   + "AND id_venta= " + venta.getIdVenta();
					
				if (lineaVenta.getCantidad() == 0) {
					preparedStatement = conexion.prepareStatement(queryEliminarLineaVenta);
				} else {
					preparedStatement = conexion.prepareStatement(queryActualizarLineaVenta);
				}
				
				preparedStatement.executeUpdate();
			}
		
		} catch(SQLException ex) {
			throw new Exception("Error en la conexion a la Base de datos");
		}	
		return 0;
	}
	
	protected Connection getConnection() throws Exception {
		Connection conexion = null;
		try {
			String database = "mazmorra";
			String username = "root";
			String password = "";

			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + database, username, password);
			conexion.setAutoCommit(false);

		} catch (Exception exception) {
			throw new Exception("Error en la conexion a la Base de datos");
		}
		
		return conexion;
	}
}
