package presentacion.comando.cliente;

import negocio.cliente.SACliente;
import negocio.cliente.TransferCliente;
import negocio.factoriaNegocio.FactoriaNegocio;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoMostrarUnCliente implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SACliente saCliente = FactoriaNegocio.obtenerInstancia().generarSACliente();
		try {
			TransferCliente tCli = saCliente.mostrarUnCliente((TransferCliente) datos);

			if (tCli == null) {
				return new Contexto(Evento.errorMostrarUnCliente, null);
			} else {
				return new Contexto(Evento.mostrarUnCliente, tCli);
			}
		} catch (Exception e) {
			return new Contexto(Evento.errorConexionBBDD, null);
		}

	}

}
