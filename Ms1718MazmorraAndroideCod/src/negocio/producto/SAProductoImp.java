package negocio.producto;

import java.util.List;

import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.factoriaQuery.FactoriaQuery;
import integracion.gestorTransaccion.GestorTransaccion;
import integracion.transaccion.Transaccion;

import integracion.producto.DAOProducto;
import integracion.query.Query;

public class SAProductoImp implements SAProducto {

	@Override
	public int altaProducto(TransferProducto transferProducto) {
		int id = -1;
		int respuesta = 0;
		GestorTransaccion gestorTransaccion = GestorTransaccion
				.obtenerInstancia();
		Transaccion transaccion = gestorTransaccion.nuevaTransaccion();
		try {
			DAOProducto daoProducto = FactoriaIntegracion.obtenerInstancia()
					.generaDAOProducto();

			transaccion.iniciar();
			TransferProducto producto = daoProducto.readByName(transferProducto
					.getName());

			if (producto == null) {
				id = daoProducto.write(transferProducto);
				transferProducto.setId(id);
				transaccion.comprometer();
			} else if (producto.getActivo()) {
				respuesta = -1;
				transaccion.deshacer();
			} else {
				respuesta = -3;
				transferProducto.setId(producto.getId());
				daoProducto.update(transferProducto);
				transaccion.comprometer();
			}

		} catch (Exception e) {
			respuesta = -9;
		} finally {
			gestorTransaccion.eliminarTransaccion();
		}

		return respuesta;
	}

	@Override
	public int bajaProducto(TransferProducto transferProducto) {
		int respuesta = 0;
		GestorTransaccion gestorTransaccion = GestorTransaccion
				.obtenerInstancia();
		Transaccion transaccion = gestorTransaccion.nuevaTransaccion();
		try {
			DAOProducto daoProducto = FactoriaIntegracion.obtenerInstancia()
					.generaDAOProducto();

			transaccion.iniciar();
			TransferProducto producto = daoProducto.read(transferProducto
					.getId());

			if (producto == null) {
				respuesta = -1;
				transaccion.deshacer();
			} else if (producto.getActivo()) {
				producto.setActivo(false);
				respuesta = daoProducto.update(producto);
				transaccion.comprometer();
			} else {
				respuesta = -3;
				transaccion.deshacer();
			}

		} catch (Exception e) {
			respuesta = -9;
		} finally {
			gestorTransaccion.eliminarTransaccion();
		}

		return respuesta;
	}

	@Override
	public int actualizarProducto(TransferProducto transferProducto) {
		int respuesta = 0;
		GestorTransaccion gestorTransaccion = GestorTransaccion
				.obtenerInstancia();
		Transaccion transaccion = gestorTransaccion.nuevaTransaccion();
		try {
			DAOProducto daoProducto = FactoriaIntegracion.obtenerInstancia()
					.generaDAOProducto();

			transaccion.iniciar();

			TransferProducto producto = daoProducto.read(transferProducto
					.getId());
			TransferProducto productoConMismoNombre = daoProducto
					.readByName(transferProducto.getName());

			if (producto == null) {
				respuesta = -1;
				transaccion.deshacer();
			} else if (productoConMismoNombre != null
					&& producto.getId() != productoConMismoNombre.getId()) {
				respuesta = -4;
				transaccion.deshacer();
			} else if (producto.getActivo()) {
				respuesta = 0;
				respuesta = daoProducto.update(transferProducto);
				transaccion.comprometer();
			} else {
				transferProducto.setActivo(true);
				respuesta = -3;
				daoProducto.update(transferProducto);
				transaccion.comprometer();
			}
		} catch (Exception e) {
			respuesta = -9;
		} finally {
			gestorTransaccion.eliminarTransaccion();
		}

		return respuesta;
	}

	@Override
	public TransferProducto mostrarUnProducto(TransferProducto transferProducto)
			throws Exception {
		try {
			DAOProducto daoProducto = FactoriaIntegracion.obtenerInstancia()
					.generaDAOProducto();
			TransferProducto producto = daoProducto.read(transferProducto
					.getId());

			return producto;

		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}
	}

	@Override
	public List<TransferProducto> mostrarTodosProductos() throws Exception {
		try {
			DAOProducto daoProducto = FactoriaIntegracion.obtenerInstancia()
					.generaDAOProducto();
			List<TransferProducto> productos = daoProducto.readAll();

			return productos;

		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}
	}


	@Override
	public List<TransferProducto> mostrarProductosEnVentasSuperioresA(
			double precio) throws Exception {
		GestorTransaccion gestorTransaccion = GestorTransaccion
				.obtenerInstancia();
		Transaccion transaccion = gestorTransaccion.nuevaTransaccion();
		try {
			Query query = FactoriaQuery.obtenerInstancia().nuevaQuery(
					"mostrarProductosEnVentasSuperioresAX");

			transaccion.iniciar();
			@SuppressWarnings("unchecked")
			List<TransferProducto> productos = (List<TransferProducto>) query
					.execute(precio);
			transaccion.comprometer();

			return productos;

		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		} finally {
			gestorTransaccion.eliminarTransaccion();
		}
	}
}
