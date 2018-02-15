package negocio;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.sql.SQLException;
import java.util.Random;

import org.junit.*;

import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.gestorTransaccion.GestorTransaccion;
import integracion.producto.DAOProducto;
import integracion.transaccion.Transaccion;
import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TransferProducto;

public class SAProductoImpTest {
	private SAProducto saProducto;
	public DAOProducto daoProducto;

	private TransferProducto altaProductoExistenteActivo;
	private TransferProducto altaProductoExistenteInactivo;
	private TransferProducto altaProductoNoExistente;

	private TransferProducto bajaProductoExistenteActivo;
	private TransferProducto bajaProductoExistenteInactivo;
	private TransferProducto bajaProductoNoExistente;

	private TransferProducto actualizarProductoConMismoNombre;

	@Before
	public void setup() {
		this.saProducto = FactoriaNegocio.obtenerInstancia().generarSAProducto();
		this.daoProducto = FactoriaIntegracion.obtenerInstancia().generaDAOProducto();

		inicializarProductos();
		guardarProductosEnLaBBDD();
	}

	@Test
	public void testAltaProducto() {
		assertTrue("Producto existente y activo", saProducto.altaProducto(altaProductoExistenteActivo) == -1);
		assertTrue("Producto existente y no activo", saProducto.altaProducto(altaProductoExistenteInactivo) == -3);
		assertTrue("Producto no existente", saProducto.altaProducto(altaProductoNoExistente) == 0);
	}

	@Test
	public void testBajaProducto() {
		assertTrue("Producto existente y activo", saProducto.bajaProducto(bajaProductoExistenteActivo) == 0);
		assertTrue("Producto existente y no activo", saProducto.bajaProducto(bajaProductoExistenteInactivo) == -3);
		assertTrue("Producto no existente", saProducto.bajaProducto(bajaProductoNoExistente) == -1);
	}

	@Test
	public void testActualizarProducto() {
		assertTrue("Producto no existente", saProducto.actualizarProducto(new TransferProducto(-1)) == -1);
		assertTrue("Producto no existente", saProducto.actualizarProducto(new TransferProducto(0)) == -1);
		assertTrue("Producto no existente", saProducto.actualizarProducto(new TransferProducto(1223434)) == -1);

		assertTrue("Producto activo", saProducto.actualizarProducto(altaProductoExistenteActivo) == 0);
		assertTrue("Producto inactivo", saProducto.actualizarProducto(bajaProductoExistenteInactivo) == -3);

		actualizarProductoConMismoNombre.setName(altaProductoExistenteActivo.getName());
		assertTrue("Cambio nombre a otro ya existente",
				saProducto.actualizarProducto(actualizarProductoConMismoNombre) == -4);
	}

	@Test
	public void testMostrarUnProducto() throws Exception {
		assertNull("Producto no existente", saProducto.mostrarUnProducto(new TransferProducto(-1)));
		assertNull("Producto no existente", saProducto.mostrarUnProducto(new TransferProducto(1283746)));

		assertNotNull("Producto activo", saProducto.mostrarUnProducto(altaProductoExistenteActivo));
		assertNotNull("Producto inactivo", saProducto.mostrarUnProducto(bajaProductoExistenteInactivo));
	}

	private void inicializarProductos() {
		Random rand = new Random();
		this.altaProductoExistenteActivo = new TransferProducto(1, 1, 1, 1, "Mario " + rand.nextInt(100000));
		this.altaProductoExistenteInactivo = new TransferProducto(4, 1, 1, 1, "Team Fortress " + rand.nextInt(100000));
		this.altaProductoNoExistente = new TransferProducto(1, 1, 1, "Zelda1111 " + rand.nextInt(1000000));

		this.bajaProductoExistenteActivo = new TransferProducto(1, 1, 1, 1, "Mario Bros. " + rand.nextInt(100000));
		this.bajaProductoExistenteInactivo = new TransferProducto(4, 1, 1, 1, "Team Fortress 2" + rand.nextInt(100000));
		this.bajaProductoNoExistente = new TransferProducto(1, 1, 1, "Ze " + rand.nextInt(1000000));

		this.actualizarProductoConMismoNombre = new TransferProducto(1, 1, 1, "Zoeru " + rand.nextInt(1000000));

	}

	private void guardarProductosEnLaBBDD() {
		Transaccion transaccion = GestorTransaccion.obtenerInstancia().nuevaTransaccion();
		try {
			transaccion.iniciar();

			int id = daoProducto.write(this.altaProductoExistenteActivo);
			altaProductoExistenteActivo.setId(id);
			int id1 = daoProducto.write(this.altaProductoExistenteInactivo);
			altaProductoExistenteInactivo.setActivo(false);
			altaProductoExistenteInactivo.setId(id1);
			daoProducto.update(altaProductoExistenteInactivo);

			int id2 = daoProducto.write(this.bajaProductoExistenteActivo);
			bajaProductoExistenteActivo.setId(id2);
			int id3 = daoProducto.write(this.bajaProductoExistenteInactivo);
			bajaProductoExistenteInactivo.setActivo(false);
			bajaProductoExistenteInactivo.setId(id3);
			daoProducto.update(bajaProductoExistenteInactivo);

			int id4 = daoProducto.write(actualizarProductoConMismoNombre);
			actualizarProductoConMismoNombre.setId(id4);
		} catch (Exception e) {
			System.out.println("Error en la conexion a la BBDD.");
		}

		transaccion.comprometer();
		GestorTransaccion.obtenerInstancia().eliminarTransaccion();

	}
}