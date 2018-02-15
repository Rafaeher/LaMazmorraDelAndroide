package integracion.cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import integracion.gestorTransaccion.GestorTransaccion;
import integracion.transaccion.Transaccion;

import negocio.cliente.TransferCliente;
import negocio.cliente.TransferClienteEstandar;
import negocio.cliente.TransferClienteVip;

public class DAOClienteImp implements DAOCliente {
	
	@Override
	public TransferCliente read(int id) throws Exception {
		TransferCliente transferCliente = null;
		Transaccion transaccion = GestorTransaccion.obtenerInstancia().obtenerTransaccion();
		Connection conexion = null;
		String forUpdateString;
		
		if (transaccion == null) {
			conexion = this.getConnection();
			forUpdateString = "";
		} else {
			conexion = (Connection) transaccion.getResource();
			forUpdateString = " FOR UPDATE";
		}
		 
		try {
			
			String query = "SELECT tipo FROM CLIENTE WHERE id = " + id + forUpdateString;
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
		
			if (rs.next()) {
				
				if (rs.getString("tipo").equalsIgnoreCase("estandar")) {

					query = "SELECT C.id AS id, C.activo AS activo, C.nombre AS nombre, C.apellidos AS apellidos, C.direccion AS direccion, C.email AS email, CE.recibepublicidad AS recibepublicidad "
						  + "FROM Cliente C, ClienteEstandar CE "
						  + "WHERE C.id = CE.id "
						  + "AND C.id = " + id + forUpdateString;
		
					preparedStatement = conexion.prepareStatement(query);
					rs = preparedStatement.executeQuery();
				
					if (rs.next()) {
					
						transferCliente = new TransferClienteEstandar(
								rs.getInt("id"),
								rs.getBoolean("activo"),
								rs.getString("nombre"),
								rs.getString("apellidos"),
								rs.getString("direccion"),
								rs.getString("email"),
								rs.getBoolean("recibepublicidad"));
					}
				
				} else {
					
					query = "SELECT C.id AS id, C.activo AS activo, C.nombre AS nombre, C.apellidos AS apellido, C.direccion AS direccion, C.email AS email, CV.descuento AS descuento, CV.puntos AS puntos "
						  + "FROM Cliente C, ClienteVip CV "
						  + "WHERE C.id = CV.id "
						  + "AND C.id = " + id + forUpdateString;
					
					
					preparedStatement = conexion.prepareStatement(query);
					rs = preparedStatement.executeQuery();
					
					if (rs.next()) {
						
						transferCliente = new TransferClienteVip(
								rs.getInt("id"),
								rs.getBoolean("activo"),
								rs.getString("nombre"),
								rs.getString("apellido"),
								rs.getString("direccion"),
								rs.getString("email"),
								rs.getInt("descuento"),
								rs.getInt("puntos"));
					}
					
				}
			}
			
			if (transaccion == null) {
				conexion.close();
			}
		
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}
		
		return transferCliente;
	}
	
	@Override
	public TransferCliente readByEmail(String email) throws Exception {
		TransferCliente transferCliente = null;
		Transaccion transaccion = GestorTransaccion.obtenerInstancia().obtenerTransaccion();
		Connection conexion = null;
		String forUpdateString;
		
		if (transaccion == null) {
			conexion = this.getConnection();
			forUpdateString = "";
		} else {
			conexion = (Connection) transaccion.getResource();
			forUpdateString = " FOR UPDATE";
		}
		
		try {
			String query = "SELECT tipo "
						 + "FROM cliente "
						 + "WHERE email = '" + email + "'" + forUpdateString;
			
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
		
			if (rs.next()) {
				if (rs.getString("tipo").equalsIgnoreCase("estandar")) {
					
					query = "SELECT C.id AS id, C.activo AS activo, C.nombre AS nombre, C.apellidos AS apellidos, C.direccion AS direccion, C.email AS email, CE.recibepublicidad AS recibepublicidad "
							  + "FROM Cliente C, ClienteEstandar CE "
							  + "WHERE C.id = CE.id "
							  + "AND C.email = '" + email + "'" + forUpdateString;
					preparedStatement = conexion.prepareStatement(query);
					rs = preparedStatement.executeQuery();
					
					if (rs.next()) {

						transferCliente = new TransferClienteEstandar(
								rs.getInt("id"),
								rs.getBoolean("activo"),
								rs.getString("nombre"),
								rs.getString("apellidos"),
								rs.getString("direccion"),
								rs.getString("email"),
								rs.getBoolean("recibepublicidad")
								);
					}
				
				} else {
					query = "SELECT C.id AS id, C.activo AS activo, C.nombre AS nombre, C.apellidos AS apellido, C.direccion AS direccion, C.email AS email, CV.descuento AS descuento, CV.puntos AS puntos "
							  + "FROM Cliente C, ClienteVip CV "
							  + "WHERE C.id = CV.id "
							  + "AND C.email = '" + email + "'" + forUpdateString;
					
					preparedStatement = conexion.prepareStatement(query);
					rs = preparedStatement.executeQuery();
					
					if (rs.next()) {
					transferCliente = new TransferClienteVip(
							rs.getInt("id"),
							rs.getBoolean("activo"),
							rs.getString("nombre"),
							rs.getString("apellido"),
							rs.getString("direccion"),
							rs.getString("email"),
							rs.getInt("descuento"),
							rs.getInt("puntos")
							);
					}
				}
			}
			
			if (transaccion == null) {
				conexion.close();
			}
		
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}
		
		return transferCliente;
		
	}

	@Override
	public List<TransferCliente> readAll() throws Exception {
		Transaccion transaccion = GestorTransaccion.obtenerInstancia().obtenerTransaccion();
		List<TransferCliente> clientes = new ArrayList<TransferCliente>();
		Connection conexion = null;
		String forUpdateString;
		
		if (transaccion == null) {
			conexion = this.getConnection();
			forUpdateString = "";
		} else {
			conexion = (Connection) transaccion.getResource();
			forUpdateString = " FOR UPDATE";
		}
		 
		
		try {
			String query = "SELECT * FROM CLIENTE" + forUpdateString;
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
		
			while (rs.next()) {
				TransferCliente transferCliente;
				int id = rs.getInt("id");
				boolean activo = rs.getBoolean("activo");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellidos");
				String direccion = rs.getString("direccion");
				String email = rs.getString("email");
				
				if(rs.getString("tipo").equalsIgnoreCase("estandar")){
					
					query = "SELECT CE.recibepublicidad AS recibepublicidad "
							  + "FROM Cliente C, ClienteEstandar CE "
							  + "WHERE C.id = CE.id "
							  + "AND C.id = " + id + forUpdateString;	
					
					preparedStatement = conexion.prepareStatement(query);				
					ResultSet resultSetCliente = preparedStatement.executeQuery();
					
					boolean recibePublicidad = false;
					
					if (resultSetCliente.next()) {
						recibePublicidad = resultSetCliente.getBoolean("recibepublicidad");
					}
					
					transferCliente = new TransferClienteEstandar(id, activo, nombre, apellido,
							direccion, email, recibePublicidad);
				} else {
					
					query = "SELECT CV.descuento AS descuento, CV.puntos AS puntos "
						  + "FROM Cliente C, ClienteVip CV "
						  + "WHERE C.id = CV.id "
						  + "AND C.id = " + id + forUpdateString;
					
					preparedStatement = conexion.prepareStatement(query);				
					ResultSet resultSetCliente = preparedStatement.executeQuery();
					
					int descuento = -1, puntos = -1;
					if (resultSetCliente.next()) {
						descuento = resultSetCliente.getInt("descuento");
						puntos = resultSetCliente.getInt("puntos");
					}
					
					transferCliente = new TransferClienteVip(id, activo, nombre, apellido,
							direccion, email, descuento, puntos);
					
				}
				
				clientes.add(transferCliente);
				
			}
			if (transaccion == null) {
				conexion.close();
			}
		
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}
		
		return clientes;
	}
	
	@Override
	public int write(TransferCliente tCli) throws Exception{
		int id = -1;
		Transaccion transaccion = GestorTransaccion.obtenerInstancia().obtenerTransaccion();
		Connection conexion = (Connection) transaccion.getResource();
		
		try {
			String query = "INSERT INTO CLIENTE (nombre, tipo, apellidos, direccion, email)"
					+ " VALUES ('" +
					tCli.getNombre() + "', '" +
					tCli.getTipo() + "', '" +
					tCli.getApellido() + "', '" +
					tCli.getDireccion() + "', '" +
					tCli.getCorreo() + "')";

			
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.execute();
			
			preparedStatement = conexion.prepareStatement("SELECT LAST_INSERT_ID()");
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				id = rs.getInt(1);
				
				if (tCli.getTipo().equalsIgnoreCase("estandar"))
				{
					query = "INSERT INTO CLIENTEESTANDAR (id, recibepublicidad) VALUES (" +
							id + ", " +
							((TransferClienteEstandar) tCli).getRecibePublicidad() + ")";
				}
				else
				{
					query = "INSERT INTO CLIENTEVIP (id, descuento, puntos) VALUES (" +
							id + ", " +
							((TransferClienteVip) tCli).getDescuento() + ", " +
							((TransferClienteVip) tCli).getPuntos() + ")";
				}
					
				preparedStatement = conexion.prepareStatement(query);
				preparedStatement.execute();
			}
	
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}
		
		return id;
	}

	@Override
	public int update(TransferCliente tCli) throws Exception
	{
		Transaccion transaccion = GestorTransaccion.obtenerInstancia().obtenerTransaccion();
		Connection conexion = (Connection) transaccion.getResource();
		
		
		try {
			
			String query = "UPDATE cliente "
					+ "SET nombre = '" + tCli.getNombre()
					+ "', apellidos = '" + tCli.getApellido()
					+ "', direccion = '" + tCli.getDireccion()
					+ "', email = '" + tCli.getCorreo()
					+ "', activo = " + tCli.getActivo()
					+ " WHERE id = " + tCli.getId();

			
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.executeUpdate();
			
			if (tCli.getTipo().equalsIgnoreCase("estandar")) {
				query = "UPDATE clienteestandar SET "
						+ "recibepublicidad = " + ((TransferClienteEstandar) tCli).getRecibePublicidad();
			} else {
				query = "UPDATE clientevip SET "
						+ "puntos = " + ((TransferClienteVip) tCli).getPuntos();
			}
			
			query += " WHERE id = " + tCli.getId();
			
			preparedStatement = conexion.prepareStatement(query);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
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

		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}
		
		return conexion;
	}
}
