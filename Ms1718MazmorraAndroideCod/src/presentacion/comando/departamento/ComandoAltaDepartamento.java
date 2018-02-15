package presentacion.comando.departamento;

import negocio.departamento.Departamento;
import negocio.departamento.SADepartamento;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoAltaDepartamento implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SADepartamento saDepartamento = FactoriaNegocioJPA.obtenerInstancia().generarSADepartamento();
		
		switch (saDepartamento.altaDepartamento((Departamento) datos)) {
			case 0:
				return new Contexto(Evento.altaDepartamentoOK, datos);
			case -1:
				return new Contexto(Evento.errorAltaDepartamentoNombreYaExistente, datos);
			case -2:
				return new Contexto(Evento.errorAltaDepartamentoReactivacion, datos);
			case -5:
				return new Contexto(Evento.errorAltaDepartamentoOptimisticLockException, datos);
			case -10:
				return new Contexto(Evento.errorConexionBBDD, datos);
			default:
				return null;
		}
	}
}
