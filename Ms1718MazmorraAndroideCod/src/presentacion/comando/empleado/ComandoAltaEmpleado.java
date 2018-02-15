package presentacion.comando.empleado;

import negocio.empleado.Empleado;
import negocio.empleado.SAEmpleado;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;


public class ComandoAltaEmpleado implements Comando {
	
	@Override
	public Contexto execute(Object datos) {
		SAEmpleado saEmpleado = FactoriaNegocioJPA.obtenerInstancia().generarSAEmpleado();
		
		switch (saEmpleado.altaEmpleado((Empleado) datos)) {
			case 0:
				return new Contexto(Evento.altaEmpleadoOK, datos);
				
			case -1:
				return new Contexto(Evento.errorAltaEmpleadoDepartamentoNoExistente, datos);
				
			case -2:
				return new Contexto(Evento.errorAltaEmpleadoDepartamentoInactivo, datos);
				
			case -3:
				return new Contexto(Evento.errorAltaEmpleadoTiposEmpleadosNoCoinciden, datos);
				
			case -4:
				return new Contexto(Evento.errorAltaEmpleadoDNIYaExistente, datos);
				
			case -5:
				return new Contexto(Evento.errorAltaEmpleadoOptimisticLockException, datos);
				
			case -6:
				return new Contexto(Evento.errorAltaEmpleadoReactivacion, datos);
				
			case -10:
				return new Contexto(Evento.errorConexionBBDD, null);
				
			default:
				return null;
			
		}
	}
}