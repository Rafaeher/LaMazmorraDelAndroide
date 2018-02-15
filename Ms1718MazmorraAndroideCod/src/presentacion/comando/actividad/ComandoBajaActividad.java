package presentacion.comando.actividad;

import negocio.actividad.SAActividad;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoBajaActividad implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SAActividad saActividad = FactoriaNegocioJPA.obtenerInstancia().generarSAActividad();
		
		switch(saActividad.bajaActividad((Integer) datos)) {
		case 0:
			return new Contexto(Evento.bajaActividadOK, datos);
			
		case -1:
			return new Contexto(Evento.errorBajaActividadIdNoExistente, datos);
			
		case -2:
			return new Contexto(Evento.errorBajaActividadEmpleadosActivos, datos);
			
		case -3:
			return new Contexto(Evento.errorBajaActividadYaDadoDeBaja, datos);
			
		case -5:
			return new Contexto(Evento.errorBajaActividadOptimisticLockException, datos);
			
		case -10:
			return new Contexto(Evento.errorConexionBBDD, null);
		
		default:
			return null;
		}
	}

}
