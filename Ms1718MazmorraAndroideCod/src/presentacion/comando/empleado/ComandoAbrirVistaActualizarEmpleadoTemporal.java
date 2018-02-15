
package presentacion.comando.empleado;

import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;


public class ComandoAbrirVistaActualizarEmpleadoTemporal implements Comando {

	@Override
	public Contexto execute(Object datos) {
		return new Contexto(Evento.abrirVistaActualizarEmpleadoTemporal, null);
	}
}