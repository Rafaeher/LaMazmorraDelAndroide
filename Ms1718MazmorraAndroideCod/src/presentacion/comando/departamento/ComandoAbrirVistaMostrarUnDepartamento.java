package presentacion.comando.departamento;

import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoAbrirVistaMostrarUnDepartamento implements Comando {

	@Override
	public Contexto execute(Object datos) {
		return new Contexto(Evento.abrirVistaMostrarUnDepartamento, null);
	}

}
