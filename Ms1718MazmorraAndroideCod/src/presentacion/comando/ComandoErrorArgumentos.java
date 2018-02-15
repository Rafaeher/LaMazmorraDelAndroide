package presentacion.comando;

import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoErrorArgumentos implements Comando {

	@Override
	public Contexto execute(Object datos) {
		return new Contexto(Evento.errorArgumentos, null);
	}

}
