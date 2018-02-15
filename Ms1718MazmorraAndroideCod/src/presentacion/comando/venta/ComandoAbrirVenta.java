package presentacion.comando.venta;

import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoAbrirVenta implements Comando {

	@Override
	public Contexto execute(Object datos) {
		return new Contexto(Evento.abrirVenta, datos);
	}

}
