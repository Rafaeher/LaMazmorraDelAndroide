package negocio.empleado;

import java.util.List;

public interface SAEmpleado {
	public Integer altaEmpleado(Empleado empleadoEntrada);

	public Integer bajaEmpleado(Integer id);

	public Integer actualizarEmpleado(Empleado empleadoEntrada);

	public Empleado mostrarUnEmpleado(Integer id) throws Exception;

	public List<Empleado> mostrarTodosEmpleados() throws Exception;

	public Integer asignarActividad(Integer idEmpleado, Integer horas,
			Integer idActividad);

	public Integer desasignarActividad(Integer idEmpleado, Integer idActividad);
}