
package presentacion.comando.empleado;

import negocio.empleado.AsignacionActividad;
import negocio.empleado.SAEmpleado;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;


public class ComandoAsignarActividad implements Comando {
	
	@Override
	public Contexto execute(Object datos) {
		SAEmpleado saEmpleado = FactoriaNegocioJPA.obtenerInstancia().generarSAEmpleado();
		
		AsignacionActividad asignacion = (AsignacionActividad) datos;
		
		switch(saEmpleado.asignarActividad(asignacion.getEmpleado().getId(), asignacion.getHoras(), asignacion.getActividad().getId()))
		{
		case 0: return new Contexto(Evento.asignarActividadOK, datos);
		case -1: return new Contexto(Evento.errorAsignarActividad_EmpleadoInexistente, datos);
		case -2: return new Contexto(Evento.errorAsignarActividad_ActividadInexistente, datos);
		case -3: return new Contexto(Evento.errorAsignarActividad_EmpleadoInactivo, datos);
		case -4: return new Contexto(Evento.errorAsignarActividad_ActividadInactiva, datos);
		case -5: return new Contexto(Evento.errorAsignarActividadOptimisticLockException, datos);
		case -6: return new Contexto(Evento.errorAsignarActividadHorasMayorQueDuracionActividad, asignacion.getHoras());
		case -7: return new Contexto(Evento.asignarActividadOKAsignacionModificada, datos);
		case -10: return new Contexto(Evento.errorConexionBBDD, datos);
		default: return null;
		}
	}
}