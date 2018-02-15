package presentacion.comando.producto;

import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoAbrirVistaMostrarProductosEnVentasSuperioresAUnPrecio implements Comando {
	
	@Override
	public Contexto execute(Object datos) {
		return new Contexto(Evento.abrirMostrarProductosEnVentasSuperioresAUnPrecio, null);
	}
}
