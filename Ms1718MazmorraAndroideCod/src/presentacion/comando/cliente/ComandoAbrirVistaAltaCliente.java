package presentacion.comando.cliente;

import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoAbrirVistaAltaCliente implements Comando {

	@Override
	public Contexto execute(Object datos) {
		return new Contexto(Evento.abrirAltaCliente, datos);
	}

}
