package presentacion.comando.departamento;

import negocio.departamento.Departamento;
import negocio.departamento.SADepartamento;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoActualizarDepartamento implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SADepartamento saDepartamento = FactoriaNegocioJPA.obtenerInstancia().generarSADepartamento();
		
		switch (saDepartamento.actualizarDepartamento((Departamento) datos)) {
			case 0:
				return new Contexto(Evento.actualizarDepartamentoOK, datos);
			case -1:
				return new Contexto(Evento.errorActualizarDepartamentoIdNoExistente, datos);
			case -2:
				return new Contexto(Evento.errorActualizarDepartamentoNombreYaExistente, datos);
			case -3:
				return new Contexto(Evento.errorActualizarDepartamentoReactivacion, datos);
			case -5:
				return new Contexto(Evento.errorActualizarDepartamentoOptimisticLockException, null);		
			case -10:
				return new Contexto(Evento.errorConexionBBDD, datos);
			default:
				return null;
		}
	}

}
