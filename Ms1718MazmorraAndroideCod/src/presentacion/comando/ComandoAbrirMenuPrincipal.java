package presentacion.comando;

import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoAbrirMenuPrincipal implements Comando {

	@Override
	public Contexto execute(Object datos) {
		return new Contexto(Evento.abrirMenuPrincipal, null);
	}

}
