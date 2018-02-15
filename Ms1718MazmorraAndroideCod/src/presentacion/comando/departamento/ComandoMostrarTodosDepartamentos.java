package presentacion.comando.departamento;

import java.util.List;

import negocio.departamento.Departamento;
import negocio.departamento.SADepartamento;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoMostrarTodosDepartamentos implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SADepartamento saDepartamento = FactoriaNegocioJPA.obtenerInstancia().generarSADepartamento();
		
		try {
			List<Departamento> departamentos = saDepartamento.mostrarTodosDepartamentos();
			
			if (departamentos.isEmpty()) {
				return new Contexto(Evento.errorMostrarTodosDepartamentosNoExisteNinguno, null);
			} else {
				return new Contexto(Evento.mostrarTodosDepartamentosOK, departamentos);
			}
		} catch (Exception e) {
			return new Contexto(Evento.errorConexionBBDD, null);
		}
		
		
		
	}

}
