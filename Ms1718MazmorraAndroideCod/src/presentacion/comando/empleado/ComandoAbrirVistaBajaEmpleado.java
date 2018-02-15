
package presentacion.comando.empleado;

import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;


public class ComandoAbrirVistaBajaEmpleado implements Comando {
	
	@Override
	public Contexto execute(Object datos) {
		return new Contexto(Evento.abrirVistaBajaEmpleado, null);
	}
}