package negocio;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import integracion.cliente.DAOCliente;
import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.gestorTransaccion.GestorTransaccion;
import integracion.producto.DAOProducto;
import integracion.transaccion.Transaccion;
import integracion.venta.DAOVenta;
import negocio.cliente.TransferClienteEstandar;
import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.producto.TransferProducto;
import negocio.venta.LineaVenta;
import negocio.venta.SAVenta;
import negocio.venta.TransferVenta;

public class SAVentaImpTest {

	@Before
	public void setup() {
		this.saVenta = FactoriaNegocio.obtenerInstancia().generarSAVenta();
		this.daoProducto = FactoriaIntegracion.obtenerInstancia().generaDAOProducto();
		this.daoCliente = FactoriaIntegracion.obtenerInstancia().generaDAOCliente();
		this.daoVenta = FactoriaIntegracion.obtenerInstancia().generaDAOVenta();
		this.gestorTransaccion = GestorTransaccion.obtenerInstancia();
		transaccion = gestorTransaccion.nuevaTransaccion();

		inicializarVentas();
	}

	@Test
	public void testCerrarVenta() {
		assertTrue("Cliente no existe", saVenta.cerrarVenta(ventaConClienteNoExistente) == -3);
		assertTrue("Ningún producto existe", saVenta.cerrarVenta(ventaConProductosNoExistentes) == -5);
		assertTrue("Ningún producto activo", saVenta.cerrarVenta(ventaConProductosNoActivos) == -5);
		assertTrue("Productos activos y productos inactivos",
				saVenta.cerrarVenta(ventaConProductosActivosYProductosInactivos) == -4);
		assertTrue("Productos activos y productos no existentes",
				saVenta.cerrarVenta(ventaConProductosActivosYProductosNoExistentes) == -1);
		assertTrue("Productos sin stock suficiente", saVenta.cerrarVenta(ventaConProductosSinStockSuficiente) == -2);
	}

	@Test
	public void testMostrarUnaVenta() throws Exception {
		assertNull("Venta no existente", saVenta.mostrarUnaVenta(new TransferVenta(-1)));
		assertNull("Venta no existente", saVenta.mostrarUnaVenta(new TransferVenta(0)));
		assertNull("Venta no existente", saVenta.mostrarUnaVenta(new TransferVenta(999990)));

		assertNotNull("Venta existente", saVenta.mostrarUnaVenta(ventaExistente));
	}

	@Test
	public void testDevolverVenta() {
		assertTrue("Venta no existente", saVenta.devolverProducto(new TransferVenta(0), 1, 1) == -3);
		assertTrue("Venta existente con producto no existente", saVenta.devolverProducto(ventaExistente, -1, 1) == -1);
		assertTrue("Venta existente con producto existente pero no en la venta",
				saVenta.devolverProducto(ventaExistente, productoInactivo1.getId(), 1) == -4);
		assertTrue("Venta con producto existente en la venta",
				saVenta.devolverProducto(ventaExistente, productoActivo1.getId(), 1) == 0);
	}

	private void inicializarVentas() {
		Random rand = new Random();
		try {
			transaccion.iniciar();

			ventaConClienteNoExistente = new TransferVenta(1, 4755833, "", 2, new HashMap<Integer, LineaVenta>());

			int idCliente = daoCliente
					.write(new TransferClienteEstandar("dd", "ddk", "djri", "enedunruqubnd " + rand.nextInt(9183747)));

			HashMap<Integer, LineaVenta> productosNoExistentes = new HashMap<>();
			productosNoExistentes.put(847575, new LineaVenta(847575, 1));
			productosNoExistentes.put(84755, new LineaVenta(84755, 1));
			productosNoExistentes.put(1004755, new LineaVenta(1004755, 1));
			ventaConProductosNoExistentes = new TransferVenta(1, idCliente, "", 2, productosNoExistentes);

			productoInactivo1 = new TransferProducto(1, 11111, 111111, "dkmfiekeury " + rand.nextInt(9933844));
			TransferProducto productoInactivo2 = new TransferProducto(1, 11111, 11111,
					"eoirjfngb " + rand.nextInt(9933844));
			int id1 = daoProducto.write(productoInactivo1);
			productoInactivo1.setId(id1);
			productoInactivo1.setActivo(false);
			int id2 = daoProducto.write(productoInactivo2);
			productoInactivo2.setId(id2);
			productoInactivo2.setActivo(false);
			daoProducto.update(productoInactivo1);
			daoProducto.update(productoInactivo2);

			HashMap<Integer, LineaVenta> productosNoActivos = new HashMap<>();
			productosNoActivos.put(id1, new LineaVenta(id1, 1));
			productosNoActivos.put(id2, new LineaVenta(id2, 1));

			ventaConProductosNoActivos = new TransferVenta(1, idCliente, "", 2, productosNoActivos);

			productoActivo1 = new TransferProducto(1, 111111, 111111, "dkmowi38ry " + rand.nextInt(9933844));
			TransferProducto productoActivo2 = new TransferProducto(1, 111111, 11111,
					"10348xjnd " + rand.nextInt(9933844));
			int id3 = daoProducto.write(productoActivo1);
			productoActivo1.setId(id3);
			int id4 = daoProducto.write(productoActivo2);
			productoActivo2.setId(id4);

			HashMap<Integer, LineaVenta> productosActivos = new HashMap<>();
			productosActivos.put(id3, new LineaVenta(id3, 1));
			productosActivos.put(id4, new LineaVenta(id4, 1));

			HashMap<Integer, LineaVenta> productosActivosYProductosInactivos = new HashMap<>();
			productosActivosYProductosInactivos.putAll(productosActivos);
			productosActivosYProductosInactivos.putAll(productosNoActivos);

			ventaConProductosActivosYProductosInactivos = new TransferVenta(1, idCliente, "", 2,
					productosActivosYProductosInactivos);

			HashMap<Integer, LineaVenta> productosActivosYProductosNoExistentes = new HashMap<>();
			productosActivosYProductosNoExistentes.putAll(productosActivos);
			productosActivosYProductosNoExistentes.putAll(productosNoExistentes);

			ventaConProductosActivosYProductosNoExistentes = new TransferVenta(1, idCliente, "", 2,
					productosActivosYProductosNoExistentes);

			TransferProducto productoSinStockSuficiente = new TransferProducto(1, 111, 1,
					"393kndnf " + rand.nextInt(9933844));
			int id5 = daoProducto.write(productoSinStockSuficiente);
			productoSinStockSuficiente.setId(id5);

			HashMap<Integer, LineaVenta> productosSinStockSuficiente = new HashMap<>();
			productosSinStockSuficiente.put(id5, new LineaVenta(id5, 1929838));

			ventaConProductosSinStockSuficiente = new TransferVenta(1, idCliente, "", 2, productosSinStockSuficiente);

			ventaExistente = new TransferVenta(1, idCliente, "", 2, productosActivos);
			int idVenta = daoVenta.write(ventaExistente);
			ventaExistente.setIdVenta(idVenta);

		} catch (Exception e) {
			System.out.println("Error en la conexion a la BBDD.");
		}

		transaccion.comprometer();
		gestorTransaccion.eliminarTransaccion();
	}

	private SAVenta saVenta;
	private DAOProducto daoProducto;
	private DAOCliente daoCliente;
	private DAOVenta daoVenta;
	private TransferVenta ventaConClienteNoExistente;

	private TransferVenta ventaConProductosNoExistentes;
	private TransferVenta ventaConProductosNoActivos;
	private TransferVenta ventaConProductosActivosYProductosInactivos;
	private TransferVenta ventaConProductosActivosYProductosNoExistentes;
	private TransferVenta ventaConProductosSinStockSuficiente;

	private TransferVenta ventaExistente;

	private TransferProducto productoActivo1;
	private TransferProducto productoInactivo1;

	private GestorTransaccion gestorTransaccion;
	private Transaccion transaccion;
}
