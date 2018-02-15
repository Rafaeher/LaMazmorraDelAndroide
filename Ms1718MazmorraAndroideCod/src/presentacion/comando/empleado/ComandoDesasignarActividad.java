
package presentacion.comando.empleado;

import negocio.empleado.AsignacionActividad;
import negocio.empleado.SAEmpleado;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;


public class ComandoDesasignarActividad implements Comando {
	
	@Override
	public Contexto execute(Object datos)
	{
		SAEmpleado saEmpleado = FactoriaNegocioJPA.obtenerInstancia().generarSAEmpleado();
		
		AsignacionActividad asignacion = (AsignacionActividad) datos;
		
		switch(saEmpleado.desasignarActividad(asignacion.getEmpleado().getId(), asignacion.getActividad().getId()))
		{
		case 0: return new Contexto(Evento.desasignarActividadOK, datos);
		case -1: return new Contexto(Evento.errorDesasignarActividad_EmpleadoInexistente, datos);
		case -2: return new Contexto(Evento.errorDesasignarActividad_EmpleadoInactivo, datos);
		case -3: return new Contexto(Evento.errorDesasignarActividad_ActividadInexistente, datos);
		case -4: return new Contexto(Evento.errorDesasignarActividad_ActividadInactiva, datos);
		case -5: return new Contexto(Evento.errorDesasignarActividadOptimisticLockException, datos);
		case -6: return new Contexto(Evento.errorDesasignarActividad_AsignacionInexistente, datos);
		case -10: return new Contexto(Evento.errorConexionBBDD, datos);
		default: return null;
		}
	
	}
}