package presentacion.comando.actividad;

import negocio.actividad.Actividad;
import negocio.actividad.SAActividad;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoMostrarUnaActividad implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SAActividad saActividad = FactoriaNegocioJPA.obtenerInstancia().generarSAActividad();
		try {
			Actividad actividad = saActividad.mostrarUnaActividad((Integer) datos);
			
			if (actividad == null) {
				return new Contexto(Evento.errorMostrarUnaActividadIdNoExistente, datos);
			} else {
				return new Contexto(Evento.mostrarUnaActividadOK, actividad);
			}
		} catch (Exception e) {
			return new Contexto(Evento.errorConexionBBDD, null);
		}
		
	}

}
