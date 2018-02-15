package negocio.cliente;

import java.util.List;

import integracion.cliente.DAOCliente;
import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.gestorTransaccion.GestorTransaccion;
import integracion.transaccion.Transaccion;


public class SAClienteImp implements SACliente {

	
	public int altaCliente(TransferCliente transferCliente) {
		int id = -1;
		int respuesta = -1;
		GestorTransaccion gestorTransaccion = GestorTransaccion
				.obtenerInstancia();
		Transaccion transaccion = gestorTransaccion.nuevaTransaccion();
		try {
			DAOCliente daoCliente = FactoriaIntegracion.obtenerInstancia()
					.generaDAOCliente();

			transaccion.iniciar();
			TransferCliente cliente = daoCliente.readByEmail(transferCliente
					.getCorreo());

			if (cliente == null) {
				id = daoCliente.write(transferCliente);
				transferCliente.setId(id);
				respuesta = 0;
				transaccion.comprometer();
			} else if (cliente.getActivo()) {
				respuesta = -1;
				transaccion.deshacer();
			} else if (!cliente.getTipo().equalsIgnoreCase(
					transferCliente.getTipo())) {
				respuesta = -2;
				transaccion.deshacer();
			} else {
				respuesta = -3;
				transferCliente.setId(cliente.getId());
				transferCliente.setActivo(true);

				if (cliente.getTipo().equalsIgnoreCase("VIP")) {
					respuesta = -4;
					((TransferClienteVip) transferCliente)
							.setPuntos(((TransferClienteVip) cliente)
									.getPuntos());
					((TransferClienteVip) transferCliente)
							.setDescuento(((TransferClienteVip) cliente)
									.getDescuento());
				} else {
					((TransferClienteEstandar) transferCliente)
							.setRecibePublicidad(((TransferClienteEstandar) cliente)
									.getRecibePublicidad());
				}

				daoCliente.update(transferCliente);
				transaccion.comprometer();
			}

		} catch (Exception ex) {
			respuesta = -9;
		} finally {
			gestorTransaccion.eliminarTransaccion();
		}
		return respuesta;
	}

	@Override
	public int bajaCliente(TransferCliente transferCliente) {
		int respuesta = -2;
		GestorTransaccion gestorTransaccion = GestorTransaccion
				.obtenerInstancia();
		Transaccion transaccion = gestorTransaccion.nuevaTransaccion();
		try {

			DAOCliente daoCliente = FactoriaIntegracion.obtenerInstancia()
					.generaDAOCliente();

			transaccion.iniciar();
			TransferCliente cliente = daoCliente.read(transferCliente.getId());

			if (cliente == null) {
				respuesta = -1;
				transaccion.deshacer();
			} else if (cliente.getActivo()) {
				cliente.setActivo(false);
				respuesta = daoCliente.update(cliente);
				transaccion.comprometer();
				respuesta = 0;
			} else {
				respuesta = -3;
				transaccion.deshacer();
			}

		} catch (Exception ex) {
			respuesta = -9;
		} finally {
			gestorTransaccion.eliminarTransaccion();
		}
		return respuesta;
	}

	@Override
	public int actualizarCliente(TransferCliente transferCliente) {
		int resultado = -2;
		GestorTransaccion gestorTransaccion = GestorTransaccion
				.obtenerInstancia();
		Transaccion transaccion = gestorTransaccion.nuevaTransaccion();
		try {
			DAOCliente daoCliente = FactoriaIntegracion.obtenerInstancia()
					.generaDAOCliente();

			transaccion.iniciar();

			TransferCliente cliente = daoCliente.read(transferCliente.getId());
			TransferCliente clienteConMismoEmail = daoCliente
					.readByEmail(transferCliente.getCorreo());

			if (cliente == null) {
				resultado = -1;
				transaccion.deshacer();
			} else if (clienteConMismoEmail != null
					&& cliente.getId() != clienteConMismoEmail.getId()) {
				resultado = -2;
				transaccion.deshacer();
			} else if (!transferCliente.getTipo().equalsIgnoreCase(
					cliente.getTipo())) {
				resultado = -4;
				transaccion.deshacer();
			} else {

				if (cliente.getActivo())
					resultado = 0;
				else {
					resultado = -3;
					transferCliente.setActivo(true);
				}

				if (cliente.getTipo().equalsIgnoreCase("VIP")) {
					((TransferClienteVip) transferCliente)
							.setPuntos(((TransferClienteVip) cliente)
									.getPuntos());
					((TransferClienteVip) transferCliente)
							.setDescuento(((TransferClienteVip) cliente)
									.getDescuento());
				}

				transferCliente.setId(cliente.getId());
				daoCliente.update(transferCliente);
				transaccion.comprometer();
			}

		} catch (Exception ex) {
			resultado = -9;
		} finally {
			gestorTransaccion.eliminarTransaccion();
		}
		return resultado;
	}

	
	@Override
	public TransferCliente mostrarUnCliente(TransferCliente transferCliente)
			throws Exception {
		try {
			DAOCliente daoCliente = FactoriaIntegracion.obtenerInstancia()
					.generaDAOCliente();
			TransferCliente cliente = daoCliente.read(transferCliente.getId());

			return cliente;
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}

	}

	@Override
	public List<TransferCliente> mostrarTodosClientes() throws Exception {
		DAOCliente daoCliente = FactoriaIntegracion.obtenerInstancia()
				.generaDAOCliente();
		try {
			List<TransferCliente> clientes = daoCliente.readAll();

			return clientes;
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}

	}
}
