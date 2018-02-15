package negocio;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import integracion.cliente.DAOCliente;
import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.gestorTransaccion.GestorTransaccion;

import integracion.transaccion.Transaccion;
import negocio.cliente.SACliente;
import negocio.cliente.TransferCliente;
import negocio.cliente.TransferClienteEstandar;
import negocio.cliente.TransferClienteVip;
import negocio.factoriaNegocio.FactoriaNegocio;

public class SAClienteImpTest {

	@Before
	public void setup() {
		this.saCliente = FactoriaNegocio.obtenerInstancia().generarSACliente();
		this.daoCliente = FactoriaIntegracion.obtenerInstancia().generaDAOCliente();

		inicializarClientes();
		guardarClientesEnLaBBDD();
	}

	@Test
	public void testAltaCliente() {
		assertTrue("Cliente estandar activo", saCliente.altaCliente(altaClienteEstandarExistenteActivo) == -1);
		assertTrue("Cliente estandar no activo", saCliente.altaCliente(altaClienteEstandarExistenteInactivo) == -3);
		assertTrue("Cliente no existente", saCliente.altaCliente(altaClienteEstandarNoExistente) == 0);

		assertTrue("Cliente vip activo", saCliente.altaCliente(altaClienteVipExistenteActivo) == -1);
		assertTrue("Cliente vip no activo", saCliente.altaCliente(altaClienteVipExistenteInactivo) == -3);
		assertTrue("Cliente no existente", saCliente.altaCliente(altaClienteVipNoExistente) == 0);
	}

	@Test
	public void testBajaCliente() {
		assertTrue("Cliente estandar activo", saCliente.bajaCliente(bajaClienteEstandarExistenteActivo) == 0);
		assertTrue("Cliente estandar no activo", saCliente.bajaCliente(bajaClienteEstandarExistenteInactivo) == -3);
		assertTrue("Cliente no existente", saCliente.bajaCliente(bajaClienteEstandarNoExistente) == -1);

		assertTrue("Cliente vip activo", saCliente.bajaCliente(bajaClienteVipExistenteActivo) == 0);
		assertTrue("Cliente vip no activo", saCliente.bajaCliente(bajaClienteVipExistenteInactivo) == -3);
		assertTrue("Cliente no existente", saCliente.bajaCliente(bajaClienteVipNoExistente) == -1);
	}

	@Test
	public void testActualizarCliente() {
		assertTrue("Cliente no existente", saCliente.actualizarCliente(new TransferClienteVip(-1)) == -1);
		assertTrue("Cliente no existente", saCliente.actualizarCliente(new TransferClienteEstandar(0)) == -1);
		assertTrue("Cliente no existente", saCliente.actualizarCliente(new TransferClienteVip(283743)) == -1);

		assertTrue("Cliente estandar activo", saCliente.actualizarCliente(altaClienteEstandarExistenteActivo) == 0);
		assertTrue("Cliente estandar inactivo",
				saCliente.actualizarCliente(bajaClienteEstandarExistenteInactivo) == -3);

		assertTrue("Cliente vip activo", saCliente.actualizarCliente(altaClienteVipExistenteActivo) == 0);
		assertTrue("Cliente vip inactivo", saCliente.actualizarCliente(bajaClienteVipExistenteInactivo) == -3);

		actualizarClienteEstandarConMismoEmail.setCorreo(altaClienteEstandarExistenteActivo.getCorreo());
		assertTrue("Cambio email cliente estandar a otro ya existente",
				saCliente.actualizarCliente(actualizarClienteEstandarConMismoEmail) == -2);

		actualizarClienteVipConMismoEmail.setCorreo(altaClienteVipExistenteActivo.getCorreo());
		assertTrue("Cambio email cliente vip a otro ya existente",
				saCliente.actualizarCliente(actualizarClienteVipConMismoEmail) == -2);

		int id = actualizarClienteTipoNoCoincide.getId();
		assertTrue("Tipo de lciente no correcto", saCliente.actualizarCliente(new TransferClienteEstandar(id)) == -4);
	}

	@Test
	public void testMostrarUnCliente() throws Exception {
		assertNull("Cliente no existente", saCliente.mostrarUnCliente(new TransferClienteVip(-1)));
		assertNull("Cliente no existente", saCliente.mostrarUnCliente(new TransferClienteEstandar(0)));
		assertNull("Cliente no existente", saCliente.mostrarUnCliente(new TransferClienteVip(9485765)));

		assertNotNull("Cliente estandar activo", saCliente.mostrarUnCliente(altaClienteEstandarExistenteActivo));
		assertNotNull("Cliente estandar inactivo", saCliente.mostrarUnCliente(bajaClienteEstandarExistenteInactivo));

		assertNotNull("Cliente vip activo", saCliente.mostrarUnCliente(altaClienteVipExistenteActivo));
		assertNotNull("Cliente vip inactivo", saCliente.mostrarUnCliente(bajaClienteVipExistenteInactivo));
	}

	private void inicializarClientes() {
		Random rand = new Random();

		this.altaClienteVipExistenteActivo = new TransferClienteVip("sjs", "sjd", "sks", "eked " + rand.nextInt(100000),
				1);
		this.altaClienteVipExistenteInactivo = new TransferClienteVip("sjs", "sjd", "sks",
				"qowie " + rand.nextInt(100000), 1);
		this.altaClienteVipNoExistente = new TransferClienteVip("sjs", "sjd", "sks", "qiehd " + rand.nextInt(100000),
				1);

		this.bajaClienteVipExistenteActivo = new TransferClienteVip("sjs", "sjd", "sks",
				"xejknc " + rand.nextInt(100000), 1);
		this.bajaClienteVipExistenteInactivo = new TransferClienteVip("sjs", "sjd", "sks",
				"c eioe " + rand.nextInt(100000), 1);
		this.bajaClienteVipNoExistente = new TransferClienteVip("sjs", "sjd", "sks", "eier " + rand.nextInt(100000), 1);

		this.actualizarClienteVipConMismoEmail = new TransferClienteVip("sjs", "sjd", "sks",
				"eieei38r " + rand.nextInt(100000), 1);

		this.altaClienteEstandarExistenteActivo = new TransferClienteEstandar("sjs", "sjd", "sks",
				"eked " + rand.nextInt(100000));
		this.altaClienteEstandarExistenteInactivo = new TransferClienteEstandar("sjs", "sjd", "sks",
				"qowie " + rand.nextInt(100000));
		this.altaClienteEstandarNoExistente = new TransferClienteEstandar("sjs", "sjd", "sks",
				"qiehd " + rand.nextInt(100000));

		this.bajaClienteEstandarExistenteActivo = new TransferClienteEstandar("sjs", "sjd", "sks",
				"xejknc " + rand.nextInt(100000));
		this.bajaClienteEstandarExistenteInactivo = new TransferClienteEstandar("sjs", "sjd", "sks",
				"c eioe " + rand.nextInt(100000));
		this.bajaClienteEstandarNoExistente = new TransferClienteEstandar("sjs", "sjd", "sks",
				"eier " + rand.nextInt(100000));

		this.actualizarClienteEstandarConMismoEmail = new TransferClienteEstandar("sjs", "sjd", "sks",
				"eieei38r " + rand.nextInt(100000));

		this.actualizarClienteTipoNoCoincide = new TransferClienteVip("sjs", "sjd", "sks",
				"efkfiru " + rand.nextInt(100000), 1);
	}

	private void guardarClientesEnLaBBDD() {
		Transaccion transaccion = GestorTransaccion.obtenerInstancia().nuevaTransaccion();
		try {
			transaccion.iniciar();

			int id = daoCliente.write(this.altaClienteVipExistenteActivo);
			altaClienteVipExistenteActivo.setId(id);
			id = daoCliente.write(this.altaClienteVipExistenteInactivo);
			altaClienteVipExistenteInactivo.setId(id);
			altaClienteVipExistenteInactivo.setActivo(false);
			daoCliente.update(altaClienteVipExistenteInactivo);

			id = daoCliente.write(this.altaClienteEstandarExistenteActivo);
			altaClienteEstandarExistenteActivo.setId(id);
			id = daoCliente.write(this.altaClienteEstandarExistenteInactivo);
			altaClienteEstandarExistenteInactivo.setId(id);
			altaClienteEstandarExistenteInactivo.setActivo(false);
			daoCliente.update(altaClienteEstandarExistenteInactivo);

			id = daoCliente.write(this.bajaClienteVipExistenteActivo);
			bajaClienteVipExistenteActivo.setId(id);
			id = daoCliente.write(this.bajaClienteVipExistenteInactivo);
			bajaClienteVipExistenteInactivo.setId(id);
			bajaClienteVipExistenteInactivo.setActivo(false);
			daoCliente.update(bajaClienteVipExistenteInactivo);

			id = daoCliente.write(this.bajaClienteEstandarExistenteActivo);
			bajaClienteEstandarExistenteActivo.setId(id);
			id = daoCliente.write(this.bajaClienteEstandarExistenteInactivo);
			bajaClienteEstandarExistenteInactivo.setId(id);
			bajaClienteEstandarExistenteInactivo.setActivo(false);
			daoCliente.update(bajaClienteEstandarExistenteInactivo);

			id = daoCliente.write(actualizarClienteVipConMismoEmail);
			actualizarClienteVipConMismoEmail.setId(id);
			id = daoCliente.write(actualizarClienteEstandarConMismoEmail);
			actualizarClienteEstandarConMismoEmail.setId(id);

			id = daoCliente.write(actualizarClienteTipoNoCoincide);
			actualizarClienteTipoNoCoincide.setId(id);
		} catch (Exception e) {
			System.out.println("Error en la conexion a la BBDD.");
		}

		transaccion.comprometer();
		GestorTransaccion.obtenerInstancia().eliminarTransaccion();
	}

	private SACliente saCliente;
	public DAOCliente daoCliente;

	private TransferCliente altaClienteVipExistenteActivo;
	private TransferCliente altaClienteVipExistenteInactivo;
	private TransferCliente altaClienteVipNoExistente;

	private TransferCliente bajaClienteVipExistenteActivo;
	private TransferCliente bajaClienteVipExistenteInactivo;
	private TransferCliente bajaClienteVipNoExistente;

	private TransferCliente actualizarClienteVipConMismoEmail;

	private TransferCliente altaClienteEstandarExistenteActivo;
	private TransferCliente altaClienteEstandarExistenteInactivo;
	private TransferCliente altaClienteEstandarNoExistente;

	private TransferCliente bajaClienteEstandarExistenteActivo;
	private TransferCliente bajaClienteEstandarExistenteInactivo;
	private TransferCliente bajaClienteEstandarNoExistente;

	private TransferCliente actualizarClienteEstandarConMismoEmail;

	private TransferCliente actualizarClienteTipoNoCoincide;
}