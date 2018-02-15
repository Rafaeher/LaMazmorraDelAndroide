package presentacion.comando.departamento;

import negocio.departamento.Departamento;
import negocio.departamento.SADepartamento;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoMostrarUnDepartamento implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SADepartamento saDepartamento = FactoriaNegocioJPA.obtenerInstancia().generarSADepartamento();
		
		try {
			Departamento departamento = saDepartamento.mostrarUnDepartamento((Integer) datos);
			
			if (departamento == null) {
				return new Contexto(Evento.errorMostrarUnDepartamentoIdNoExistente, datos);
			} else {
				return new Contexto(Evento.mostrarUnDepartamentoOK, departamento);
			}
		} catch (Exception e) {
			return new Contexto(Evento.errorConexionBBDD, null);
		}
		
	}

}
