
package presentacion.comando.empleado;

import negocio.empleado.Empleado;
import negocio.empleado.SAEmpleado;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;


public class ComandoActualizarEmpleado implements Comando
{
	@Override
	public Contexto execute(Object datos)
	{
		SAEmpleado saEmpleado = FactoriaNegocioJPA.obtenerInstancia().generarSAEmpleado();
		
		switch(saEmpleado.actualizarEmpleado((Empleado) datos))
		{
		case 1: 
			return new Contexto(Evento.actualizarEmpleadoOKReactivado, datos);
		case 0: 
			return new Contexto(Evento.actualizarEmpleadoOK, datos);
		case -1: 
			return new Contexto(Evento.errorActualizarEmpleado_EmpleadoInexistente, datos);
		case -2: 
			return new Contexto(Evento.errorActualizarEmpleadoDNIRepetido, datos);
		case -3: 
			return new Contexto(Evento.errorActualizarEmpleadoTiposDistintos, datos);
		case -4: 
			return new Contexto(Evento.errorActualizarEmpleado_DepartamentoInexistente, datos);
		case -5:
			return new Contexto(Evento.errorActualizarEmpleadoOptimisticLockException, datos);
		case -6: 
			return new Contexto(Evento.errorActualizarEmpleado_DepartamentoInactivo, datos);
		case -10:
			return new Contexto(Evento.errorConexionBBDD, null);
		default: 
			return null;
		}
	}
}