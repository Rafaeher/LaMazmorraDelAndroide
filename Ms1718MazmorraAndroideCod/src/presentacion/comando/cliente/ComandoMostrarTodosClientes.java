package presentacion.comando.cliente;

import java.util.List;

import negocio.cliente.SACliente;
import negocio.cliente.TransferCliente;
import negocio.factoriaNegocio.FactoriaNegocio;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoMostrarTodosClientes implements Comando {

	@Override
	public Contexto execute(Object datos) {

		SACliente saCliente = FactoriaNegocio.obtenerInstancia().generarSACliente();

		List<TransferCliente> tCli;
		try {
			tCli = saCliente.mostrarTodosClientes();

			if (tCli.isEmpty()) {
				return new Contexto(Evento.errorMostrarTodosClientes, null);
			} else {
				return new Contexto(Evento.mostrarTodosClientes, tCli);
			}
		} catch (Exception e) {
			return new Contexto(Evento.errorConexionBBDD, null);
		}

	}

}
