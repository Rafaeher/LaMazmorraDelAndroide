
package presentacion.comando.empleado;

import negocio.empleado.Empleado;
import negocio.empleado.SAEmpleado;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoMostrarUnEmpleado implements Comando {
	
	@Override
	public Contexto execute(Object datos) {
		try {
			SAEmpleado saEmpleado = FactoriaNegocioJPA.obtenerInstancia().generarSAEmpleado();
			Empleado empleado= saEmpleado.mostrarUnEmpleado((Integer) datos);
			
			if (empleado == null) {
				return new Contexto(Evento.errorMostrarUnEmpleadoNoExiste, datos);
			} else {
				return new Contexto(Evento.mostrarUnEmpleadoOK, empleado);
			}
			
		} catch (Exception e) {
			return new Contexto(Evento.errorConexionBBDD, null);
		}
	}
}