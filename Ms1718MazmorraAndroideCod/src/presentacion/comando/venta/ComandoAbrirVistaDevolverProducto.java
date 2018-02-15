package presentacion.comando.venta;

import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoAbrirVistaDevolverProducto implements Comando {

	@Override
	public Contexto execute(Object datos) {
		return new Contexto(Evento.abrirDevolverProducto, datos);
	}

}
