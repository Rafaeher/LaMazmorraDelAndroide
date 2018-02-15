/**
 * 
 */
package negocio.departamento;

import java.util.List;


public interface SADepartamento {
	
	public Integer altaDepartamento(Departamento departamento);
	public Integer actualizarDepartamento(Departamento departamento);
	public Integer bajaDepartamento(Integer id);
	public Departamento mostrarUnDepartamento(Integer id) throws Exception;
	public List<Departamento> mostrarTodosDepartamentos() throws Exception;
	public Double calcularSueldoNetoDepartamento(Integer id);
}