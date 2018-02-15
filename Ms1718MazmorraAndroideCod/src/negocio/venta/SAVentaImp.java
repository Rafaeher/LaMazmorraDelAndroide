package negocio.venta;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import integracion.cliente.DAOCliente;
import integracion.producto.DAOProducto;
import integracion.venta.DAOVenta;

import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.factoriaQuery.FactoriaQuery;
import integracion.gestorTransaccion.GestorTransaccion;
import integracion.transaccion.Transaccion;

import integracion.query.DatosQuery;
import integracion.query.Query;

import negocio.cliente.TransferCliente;
import negocio.cliente.TransferClienteVip;
import negocio.producto.TransferProducto;

public class SAVentaImp implements SAVenta {

	@Override
	public int devolverProducto(TransferVenta transferVenta, int idProducto,
			int cantidadADevolver) {
		int respuesta = 0;

		GestorTransaccion gestorTransaccion = GestorTransaccion
				.obtenerInstancia();
		Transaccion transaccion = gestorTransaccion.nuevaTransaccion();
		try {
			int cantidadDevuelta = 0;

			transaccion.iniciar();

			DAOVenta daoVenta = FactoriaIntegracion.obtenerInstancia()
					.generaDAOVenta();
			DAOProducto daoProducto = FactoriaIntegracion.obtenerInstancia()
					.generaDAOProducto();
			DAOCliente daoCliente = FactoriaIntegracion.obtenerInstancia()
					.generaDAOCliente();

			TransferVenta venta = daoVenta.read(transferVenta.getIdVenta());
			TransferProducto producto = daoProducto.read(idProducto);

			if (venta != null) {
				TransferCliente cliente = daoCliente.read(venta.getIdCliente());
				if (cliente != null && cliente.getActivo()) {
					if (producto != null) {
						LineaVenta lineaVenta = venta.getProductos().get(
								producto.getId());
						if (lineaVenta != null) {
							int cantidadRestante, nuevoStock;

							if (lineaVenta.getCantidad() < cantidadADevolver) {
								cantidadRestante = 0;
								cantidadDevuelta = lineaVenta.getCantidad();
								nuevoStock = producto.getStock()
										+ lineaVenta.getCantidad();
								respuesta = -5;
							} else {
								cantidadRestante = lineaVenta.getCantidad()
										- cantidadADevolver;
								cantidadDevuelta = cantidadADevolver;
								nuevoStock = producto.getStock()
										+ cantidadADevolver;
								respuesta = 0;
							}

							lineaVenta.setCantidad(cantidadRestante);
							producto.setStock(nuevoStock);

							if (!producto.getActivo()) {
								producto.setActivo(true);
								respuesta -= 7;
							}

							if (cliente.getTipo().equalsIgnoreCase("Vip")) {
								TransferClienteVip clienteVip = (TransferClienteVip) cliente;
								double precioProdConDesc = (lineaVenta
										.getPrecio() * (1 - (clienteVip
										.getDescuento() / 100.0)));
								venta.setPrecio(venta.getPrecio()
										- cantidadDevuelta * precioProdConDesc);
							} else {
								venta.setPrecio(venta.getPrecio()
										- (cantidadDevuelta * lineaVenta
												.getPrecio()));
							}

							daoProducto.update(producto);
							daoVenta.update(venta);
							transaccion.comprometer();

						} else {
							transaccion.deshacer();
							respuesta = -4;
						}
					} else {
						transaccion.deshacer();
						respuesta = -1;
					}
				} else {
					transaccion.deshacer();
					respuesta = -6;
				}
			} else {
				transaccion.deshacer();
				respuesta = -3;
			}
		} catch (Exception ex) {
			respuesta = -9;
		} finally {
			gestorTransaccion.eliminarTransaccion();
		}
		return respuesta;
	}

	@Override
	public int cerrarVenta(TransferVenta venta) {
		int respuesta = 0;
		GestorTransaccion gestorTransaccion = GestorTransaccion
				.obtenerInstancia();
		Transaccion transaccion = gestorTransaccion.nuevaTransaccion();
		try {
			Collection<LineaVenta> productos = venta.getProductos().values();

			transaccion.iniciar();

			DAOCliente daoCliente = FactoriaIntegracion.obtenerInstancia()
					.generaDAOCliente();

			TransferCliente cliente = daoCliente.read(venta.getIdCliente());

			if (cliente != null && cliente.getActivo()) {
				Iterator<LineaVenta> itProductos = productos.iterator();

				DAOProducto daoProducto = FactoriaIntegracion
						.obtenerInstancia().generaDAOProducto();

				while (itProductos.hasNext()) {
					LineaVenta lineaVenta = itProductos.next();
					int idProducto = lineaVenta.getId();
					int cantidad = lineaVenta.getCantidad();

					TransferProducto producto = daoProducto.read(idProducto);
					if (producto != null && producto.getActivo()) {
						int stock = producto.getStock();
						lineaVenta.setPrecio(producto.getPrecio());

						if (stock > 0) {
							if (cantidad <= stock) {
								producto.setStock(stock - cantidad);
								daoProducto.update(producto);
								double precioActualDeLaVenta = venta
										.getPrecio();

								venta.setPrecio(precioActualDeLaVenta
										+ producto.getPrecio() * cantidad);

							} else {
								producto.setStock(0);
								daoProducto.update(producto);
								double precioActualDeLaVenta = venta
										.getPrecio();
								lineaVenta.setCantidad(stock);
								venta.setPrecio(precioActualDeLaVenta
										+ producto.getPrecio() * stock);
								respuesta = -2;
							}
						} else {
							itProductos.remove();
							respuesta = -4;
						}
					} else {
						itProductos.remove();
						respuesta = -1;
					}
				}
			} else {
				respuesta = -3;
				transaccion.deshacer();
			}

			if (cliente != null && cliente.getActivo() && !productos.isEmpty()) {
				DAOVenta daoVenta = FactoriaIntegracion.obtenerInstancia()
						.generaDAOVenta();

				if (cliente.getTipo().equalsIgnoreCase("vip")) {
					int descuento = ((TransferClienteVip) cliente)
							.getDescuento();
					int puntos = ((TransferClienteVip) cliente).getPuntos();

					((TransferClienteVip) cliente).setPuntos(puntos + 10);
					daoCliente.update(cliente);

					double precioVentaTotal = venta.getPrecio();

					venta.setPrecio(precioVentaTotal
							* (1 - (descuento / 100.0)));
				}

				Integer id = daoVenta.write(venta);
				venta.setIdVenta(id);
				transaccion.comprometer();
			} else if (cliente != null && productos.isEmpty()) {
				respuesta = -5;
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
	public TransferVenta mostrarUnaVenta(TransferVenta tVen) throws Exception {
		try {
			DAOVenta daoVentas = FactoriaIntegracion.obtenerInstancia()
					.generaDAOVenta();
			TransferVenta venta = daoVentas.read(tVen.getIdVenta());

			return venta;
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}
	}

	@Override
	public List<TransferVenta> mostrarTodasVentas() throws Exception {
		try {
			DAOVenta daoVentas = FactoriaIntegracion.obtenerInstancia()
					.generaDAOVenta();
			List<TransferVenta> ventas = daoVentas.readAll();

			return ventas;
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransferVenta> mostrarVentasConProductosAClientesTipo(
			DatosQuery datos) throws Exception {
		GestorTransaccion gestorTransaccion = GestorTransaccion
				.obtenerInstancia();
		Transaccion transaccion = gestorTransaccion.nuevaTransaccion();
		try {
			Query query = FactoriaQuery.obtenerInstancia().nuevaQuery(
					"mostrarVentasConProductosAClientesTipo");
			DAOProducto daoProducto = FactoriaIntegracion.obtenerInstancia()
					.generaDAOProducto();
			List<TransferVenta> ventas = null;

			transaccion.iniciar();
			TransferProducto producto = daoProducto.read(datos.getIdProducto());

			if (producto != null) {
				ventas = (List<TransferVenta>) query.execute(datos);
				transaccion.comprometer();
			} else {
				transaccion.deshacer();
			}

			return ventas;
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la Base de datos");
		} finally {
			gestorTransaccion.eliminarTransaccion();
		}

	}

}
