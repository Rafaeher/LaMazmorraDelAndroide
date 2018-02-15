package presentacion.comando.venta;

import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

import java.util.*;

import negocio.venta.LineaVenta;

public class ComandoMostrarCarrito implements Comando {

	@Override
	public Contexto execute(Object datos) {
		if (((HashMap<Integer, LineaVenta>) datos).isEmpty()) {
			return new Contexto(Evento.mostrarCarritoVacio, datos);
		} else {
			return new Contexto(Evento.mostrarCarrito, datos);
		}
		
	}

}
