package presentacion.comando.actividad;

import java.util.List;

import negocio.actividad.Actividad;
import negocio.actividad.SAActividad;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoMostrarTodasActividades implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SAActividad saActividad = FactoriaNegocioJPA.obtenerInstancia().generarSAActividad();
		try {
			List<Actividad> actividades = saActividad.mostrarTodasActividades();
			
			if (actividades.isEmpty()) {
				return new Contexto(Evento.errorMostrarTodasActividadesNoExisteNinguna, null);
			} else {
				return new Contexto(Evento.mostrarTodasActividadesOK, actividades);
			}
		} catch (Exception e) {
			return new Contexto(Evento.errorConexionBBDD, null);
		}
		
	}

}
