package integracion.producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import negocio.producto.TransferProducto;

import integracion.gestorTransaccion.GestorTransaccion;
import integracion.transaccion.Transaccion;

public class DAOProductoImp implements DAOProducto {

	@Override
	public TransferProducto read(int id) throws Exception {
		TransferProducto transferProducto = null;

		try {
			Transaccion transaccion = GestorTransaccion.obtenerInstancia().obtenerTransaccion();
			Connection conexion = null;
			String query = "SELECT * FROM PRODUCTO WHERE id = " + id;

			if (transaccion == null) {
				conexion = this.getConnection();
			} else {
				conexion = (Connection) transaccion.getResource();
				query += " FOR UPDATE";
			}

			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {

				transferProducto = new TransferProducto(rs.getInt("id"), rs.getBoolean("activo"), rs.getDouble("precio"),
						rs.getInt("stock"), rs.getInt("sistema_operativo"), rs.getString("nombre"));
			}

			if (transaccion == null) {
				conexion.close();
			}

		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}

		return transferProducto;
	}

	@Override
	public List<TransferProducto> readAll() throws Exception {
		Transaccion transaccion = GestorTransaccion.obtenerInstancia().obtenerTransaccion();
		List<TransferProducto> productos = new ArrayList<>();
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

			String query = "SELECT * FROM Producto" + forUpdateString;
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				productos.add(new TransferProducto(rs.getInt("id"), rs.getBoolean("activo"), rs.getDouble("precio"),
						rs.getInt("stock"), rs.getInt("sistema_operativo"), rs.getString("nombre")));
			}

			if (transaccion == null) {
				conexion.close();
			}

		} catch (SQLException e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}

		return productos;
	}

	@Override
	public int write(TransferProducto tPro) throws Exception {
		int id = -1;
		Transaccion transaccion = GestorTransaccion.obtenerInstancia().obtenerTransaccion();
		Connection conexion = (Connection) transaccion.getResource();

		try {
			String query = "INSERT INTO producto (nombre, precio, sistema_operativo, stock)" + " VALUES ('"
					+ tPro.getName() + "', " + tPro.getPrecio() + ", " + tPro.getSo() + ", " + tPro.getStock() + ")";

			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.execute();

			preparedStatement = conexion.prepareStatement("SELECT LAST_INSERT_ID()");
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}

		return id;
	}

	@Override
	public int update(TransferProducto tPro) throws Exception {
		Transaccion transaccion = GestorTransaccion.obtenerInstancia().obtenerTransaccion();
		Connection conexion = (Connection) transaccion.getResource();

		try {

			String query = "UPDATE producto " + "SET nombre = '" + tPro.getName() + "', precio = " + tPro.getPrecio()
					+ ", sistema_operativo = " + tPro.getSo() + ", stock = " + tPro.getStock() + ", activo = "
					+ tPro.getActivo() + " WHERE id = " + tPro.getId();

			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}

		return 0;
	}

	@Override
	public TransferProducto readByName(String name) throws Exception {
		TransferProducto transferProducto = null;
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
			String query = "SELECT * FROM PRODUCTO WHERE nombre = '" + name + "'" + forUpdateString;
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {

				transferProducto = new TransferProducto(rs.getInt("id"), rs.getBoolean("activo"), rs.getDouble("precio"),
						rs.getInt("stock"), rs.getInt("sistema_operativo"), rs.getString("nombre"));
			}
			
			if (transaccion == null) {
				conexion.close();
			}

		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}

		return transferProducto;
	}

	private Connection getConnection() throws Exception {
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
