
package negocio.factoriaNegocio;

import negocio.actividad.SAActividad;
import negocio.actividad.SAActividadImp;
import negocio.departamento.SADepartamento;
import negocio.departamento.SADepartamentoImp;
import negocio.empleado.SAEmpleado;
import negocio.empleado.SAEmpleadoImp;


public class FactoriaNegocioJPAImp extends FactoriaNegocioJPA {
	
	@Override
	public SAActividad generarSAActividad() {
		return new SAActividadImp();
	}
	
	@Override
	public SADepartamento generarSADepartamento() {
		return new SADepartamentoImp();
	}
	
	@Override
	public SAEmpleado generarSAEmpleado() {
		return new SAEmpleadoImp();
		
	}
}