package presentacion.controlador;

import presentacion.eventos.Evento;

public class Contexto {
	private Object datos;
	private Evento evento;
	
	public Contexto (Evento evento, Object datos) {
		this.datos = datos;
		this.evento = evento;
	}
	
	public Object getDatos() {
		return datos;
	}
	
	public Evento getEvento() {
		return evento;	
	}
}
