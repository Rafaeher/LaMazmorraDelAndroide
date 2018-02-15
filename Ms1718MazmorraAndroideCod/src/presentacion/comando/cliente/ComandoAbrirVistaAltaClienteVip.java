package presentacion.comando.cliente;

import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoAbrirVistaAltaClienteVip implements Comando {

	@Override
	public Contexto execute(Object datos) {
		return new Contexto(Evento.abrirAltaClienteVip, datos);
	}

}
