
package presentacion.comando.empleado;

import java.util.List;

import negocio.empleado.Empleado;
import negocio.empleado.SAEmpleado;
import negocio.factoriaNegocio.FactoriaNegocioJPA;
import presentacion.comando.Comando;
import presentacion.controlador.Contexto;
import presentacion.eventos.Evento;


public class ComandoMostrarTodosEmpleados implements Comando {
	
	@Override
	public Contexto execute(Object datos) {
		try {
			SAEmpleado saEmpleado = FactoriaNegocioJPA.obtenerInstancia().generarSAEmpleado();
			List<Empleado> empleados = saEmpleado.mostrarTodosEmpleados();
			
			if (empleados.isEmpty()) {
				return new Contexto(Evento.errorMostrarTodosEmpleadosNoExisteNinguno, null);
			} else {
				return new Contexto(Evento.mostrarTodosEmpleadosOK, empleados);
			}
		} catch (Exception e) {
			return new Contexto(Evento.errorConexionBBDD, null);
		}
	}
}