
package negocio.factoriaNegocio;

import negocio.actividad.SAActividad;
import negocio.departamento.SADepartamento;
import negocio.empleado.SAEmpleado;

public abstract class FactoriaNegocioJPA {
	private static FactoriaNegocioJPA instancia;

	
	public static FactoriaNegocioJPA obtenerInstancia() {
		if (instancia == null) {
			instancia = new FactoriaNegocioJPAImp();
		}
		
		return instancia;
	}

	public abstract SADepartamento generarSADepartamento();
	public abstract SAActividad generarSAActividad();
	public abstract SAEmpleado generarSAEmpleado();
}