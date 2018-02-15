package presentacion.comando.actividad;

import negocio.actividad.Actividad;
import negocio.actividad.SAActividad;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoActualizarActividad implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SAActividad saActividad = FactoriaNegocioJPA.obtenerInstancia().generarSAActividad();
		
		switch(saActividad.actualizarActividad((Actividad) datos)) {
			case 0:
				return new Contexto(Evento.actualizarActividadOK, datos);
			case -1:
				return new Contexto(Evento.errorActualizarActividadIdNoExistente, datos);
			case -2:
				return new Contexto(Evento.errorActualizarActividadNombreYaExistente, datos);
			case -3:
				return new Contexto(Evento.errorActualizarActividadAsignacionConMayorDuracion, datos);
			case -4:
				return new Contexto(Evento.errorActualizarActividadReactivacion, datos);
			case -5:
				return new Contexto(Evento.errorActualizarActividadOptimisticLockException, null);	
			case -10:
				return new Contexto(Evento.errorConexionBBDD, null);
			default:
				return null;
		}
	}

}
