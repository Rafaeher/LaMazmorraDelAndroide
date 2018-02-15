
package presentacion.comando.empleado;

import negocio.empleado.SAEmpleado;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;


public class ComandoBajaEmpleado implements Comando {
	
	@Override
	public Contexto execute(Object datos)
	{
		
		SAEmpleado saEmpleado = FactoriaNegocioJPA.obtenerInstancia().generarSAEmpleado();
		
		switch(saEmpleado.bajaEmpleado((Integer) datos))
		{
		case 0: 
			return new Contexto(Evento.bajaEmpleadoOK, datos);
		case -1: 
			return new Contexto(Evento.errorBajaEmpleado_EmpleadoInexistente, datos);
		case -2: 
			return new Contexto(Evento.errorBajaEmpleado_EmpleadoInactivo, datos);
		case -3: 
			return new Contexto(Evento.errorBajaEmpleado_ActividadesAsignadas, datos);
		case -5: 
			return new Contexto(Evento.errorBajaEmpleadoOptimisticLockException, datos);
		case -10: 
			return new Contexto(Evento.errorConexionBBDD, datos);
		default: 
			return null;
		}
	}
}