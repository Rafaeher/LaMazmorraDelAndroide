package integracion;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.*;

import integracion.cliente.DAOCliente;
import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.gestorTransaccion.GestorTransaccion;
import integracion.transaccion.Transaccion;
import negocio.cliente.TransferCliente;

public class DAOClienteImpTest {
	private DAOCliente daoCliente;
	
	private Transaccion transaccion;	
	private TransferCliente clienteConIdExistente;
	private TransferCliente clienteConIdNoExistente;
	
	private TransferCliente clienteConEmailExistente;
	private TransferCliente clienteConEmailNoExistente;
	
	@Before
	public void setup() {
		daoCliente = FactoriaIntegracion.obtenerInstancia().generaDAOCliente();
		transaccion = GestorTransaccion.obtenerInstancia().nuevaTransaccion();
		
		try {
			transaccion.iniciar();
		} catch (Exception e) {
			System.out.println("Error en la conexion a la BBDD.");
		}
		
		clienteConIdExistente = new TransferCliente(1);
		clienteConIdNoExistente = new TransferCliente(284747);
		
		clienteConEmailExistente = new TransferCliente("", "", "", "a@gmail.com");
		clienteConEmailNoExistente = new TransferCliente("", "", "", "eiubfekdjn");	
	}
	
	@Test
	public void leerPorIdClienteExistente() throws Exception {
		assertNotNull(daoCliente.read(clienteConIdExistente.getId()));
	}
	
	@Test
	public void leerPorIdClienteNoExistente() throws Exception {
		assertNull(daoCliente.read(clienteConIdNoExistente.getId()));
	}
	
	@Test
	public void leerClienteConEmailExistente() throws Exception {
		assertNotNull(daoCliente.readByEmail(clienteConEmailExistente.getCorreo()));
	}
	
	@Test
	public void leerClienteConEmailNoExistente() throws Exception {
		assertNull(daoCliente.readByEmail(clienteConEmailNoExistente.getCorreo()));
	}
	
	
	@After
	public void end() {
		transaccion.deshacer();
		GestorTransaccion.obtenerInstancia().eliminarTransaccion();
	}
	
	
	
	
	
}
