package presentacion.comando.actividad;

import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoAbrirMenuActividad implements Comando {

	@Override
	public Contexto execute(Object datos) {
		return new Contexto(Evento.abrirMenuActividad, null);
	}

}
