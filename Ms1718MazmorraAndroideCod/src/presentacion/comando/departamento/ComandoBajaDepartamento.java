package presentacion.comando.departamento;

import negocio.departamento.SADepartamento;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoBajaDepartamento implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SADepartamento saDepartamento = FactoriaNegocioJPA.obtenerInstancia().generarSADepartamento();
		
		switch (saDepartamento.bajaDepartamento((Integer) datos)) {
		case 0:
			return new Contexto(Evento.bajaDepartamentoOK, datos);
			
		case -1:
			return new Contexto(Evento.errorBajaDepartamentoIdNoExistente, datos);
			
		case -2:
			return new Contexto(Evento.errorBajaDepartamentoEmpleadosActivos, datos);
			
		case -3:
			return new Contexto(Evento.errorBajaDepartamentoYaDadoDeBaja, datos);
			
		case -5:
			return new Contexto(Evento.errorBajaDepartamentoOptimisticLockException, datos);
			
		case -10:
			return new Contexto(Evento.errorConexionBBDD, datos);
		
		default:
			return null;
		}
	}

}
