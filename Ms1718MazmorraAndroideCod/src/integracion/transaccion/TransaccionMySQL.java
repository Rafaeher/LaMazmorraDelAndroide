package integracion.transaccion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransaccionMySQL implements Transaccion {
	private Connection conexion;

	@Override
	public void iniciar() throws Exception {

		try {
			String database = "mazmorra";
			String username = "root";
			String password = "";

			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + database, username, password);
			conexion.setAutoCommit(false);

		} catch (Exception exception) {
			throw new Exception("Error en la conexion a la Base de datos: " + exception.getMessage());
		}
	}

	@Override
	public void comprometer() {
		try {
			conexion.commit();
			conexion.close();
		} catch (SQLException e) {
			System.err.println("Error al comprometer la transaccion en curso: " + e.getMessage());
		}
	}

	@Override
	public void deshacer() {
		try {
			conexion.rollback();
			conexion.close();
		} catch (SQLException e) {
			System.err.println("Error al hacer un rollback sobre la transacción en curso: " + e.getMessage());
		}
	}

	@Override
	public Object getResource() {
		return this.conexion;
	}
}
