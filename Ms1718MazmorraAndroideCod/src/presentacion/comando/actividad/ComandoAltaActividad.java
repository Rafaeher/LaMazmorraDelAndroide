package presentacion.comando.actividad;

import negocio.actividad.Actividad;
import negocio.actividad.SAActividad;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoAltaActividad implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SAActividad saActividad = FactoriaNegocioJPA.obtenerInstancia().generarSAActividad();
		
		switch(saActividad.altaActividad((Actividad) datos)) {
		case 0:
			return new Contexto(Evento.altaActividadOK, datos);
		case -1:
			return new Contexto(Evento.errorAltaActividadNombreYaExistente, datos);
		case -2:
			return new Contexto(Evento.errorAltaActividadReactivacion, datos);
		case -5:
			return new Contexto(Evento.errorAltaActividadOptimisticLockException, datos);		
		case -10:
			return new Contexto(Evento.errorConexionBBDD, null);
		default:
			return null;
		}
	}

}
