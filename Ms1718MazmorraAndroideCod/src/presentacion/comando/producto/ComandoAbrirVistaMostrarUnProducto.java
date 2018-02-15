package presentacion.comando.producto;

import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoAbrirVistaMostrarUnProducto implements Comando {

	@Override
	public Contexto execute(Object datos) {
		return new Contexto(Evento.abrirMostrarUnProducto, null);
	}

}
