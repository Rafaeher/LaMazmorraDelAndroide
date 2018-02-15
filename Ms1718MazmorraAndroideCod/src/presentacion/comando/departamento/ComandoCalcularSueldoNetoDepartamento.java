package presentacion.comando.departamento;

import negocio.departamento.SADepartamento;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;

public class ComandoCalcularSueldoNetoDepartamento implements Comando {

	@Override
	public Contexto execute(Object datos) {
		SADepartamento saDepartamento = FactoriaNegocioJPA.obtenerInstancia().generarSADepartamento();
		
		Double sueldoNeto = saDepartamento.calcularSueldoNetoDepartamento((Integer) datos);
		if (sueldoNeto >= 0.0) {
			return new Contexto(Evento.calcularSueldoNetoDepartamentoOK, sueldoNeto);
		} else if (sueldoNeto == -1.0) {
			return new Contexto(Evento.errorCalcularSueldoNetoDepartamentoIdNoExistente, datos);
		} else if (sueldoNeto == -2.0) {
			return new Contexto(Evento.errorCalcularSueldoNetoDepartamentoDepartamentoInactivo, null);
		} else if (sueldoNeto == -5.0) {
			return new Contexto(Evento.errorCalcularSueldoNetoDepartamentoOptimisticLockException, null);
		} else if (sueldoNeto == -10.0) {
			return new Contexto(Evento.errorConexionBBDD, null);
		} else {
			return null;
		}
	}

}
