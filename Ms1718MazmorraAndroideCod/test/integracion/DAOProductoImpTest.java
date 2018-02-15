package integracion;
import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.gestorTransaccion.GestorTransaccion;
import integracion.producto.DAOProducto;
import integracion.transaccion.Transaccion;
import negocio.producto.TransferProducto;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;



import org.junit.*;



public class DAOProductoImpTest {
	private DAOProducto daoProducto;
	private Transaccion transaccion;
	private GestorTransaccion gestorTransaccion;
	private TransferProducto productoExistente1;
	private TransferProducto productoExistente2;
	
	@Before
	public void setup() {
		this.daoProducto = FactoriaIntegracion.obtenerInstancia().generaDAOProducto();
		this.gestorTransaccion = GestorTransaccion.obtenerInstancia();
		this.transaccion = gestorTransaccion.nuevaTransaccion();
		
		this.productoExistente1 = new TransferProducto(1, 1, 1, 1, "Mario");
		this.productoExistente2 = new TransferProducto(2, 1, 1, 1, "Zelda");
		
		
		try {
			transaccion.iniciar();
		} catch (Exception e) {
			System.out.println("Error en la conexion a la BBDD.");
		}
	}
	
	@Test
	public void leerPorIdUnProductoExistente() throws Exception {
		
		assertTrue(daoProducto.read(productoExistente1.getId()).equals(productoExistente1));
		assertTrue(daoProducto.read(productoExistente2.getId()).equals(productoExistente2));
	}
	
	@Test
	public void leerPorIdProductoNoExistente() throws Exception {
		
		assertNull(daoProducto.read(587586));
		assertNull(daoProducto.read(5767));
	}
	
	@Test
	public void leerPorNombreProductoExistente() throws Exception {
		
		assertTrue(daoProducto.readByName(productoExistente1.getName()).equals(productoExistente1));
		assertTrue(daoProducto.readByName(productoExistente2.getName()).equals(productoExistente2));
	}
	
	@Test
	public void leerPorNombreProductoNoExistente() throws Exception {
		
		assertNull(daoProducto.readByName("cjknfnfg"));
		assertNull(daoProducto.readByName("flkjngiugt"));
	}
	
	@After
	public void end() {
		this.transaccion.deshacer();
		this.gestorTransaccion.eliminarTransaccion();
	}
	
}