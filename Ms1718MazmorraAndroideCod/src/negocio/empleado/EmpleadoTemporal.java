
package negocio.empleado;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import negocio.departamento.Departamento;

import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.empleado.EmpleadoTemporal.findByhorasMes", query = "select obj from EmpleadoTemporal obj where obj.horasMes = :horasMes"),
		@NamedQuery(name = "negocio.empleado.EmpleadoTemporal.findBysalarioPorHora", query = "select obj from EmpleadoTemporal obj where obj.salarioPorHora = :salarioPorHora") })
public class EmpleadoTemporal extends Empleado {
	protected Integer horasMes;
	protected Double salarioPorHora;

	public EmpleadoTemporal() {
	}

	public EmpleadoTemporal(Integer id, String dni, String nombre,
			String telefono, Departamento departamento, Integer horasMes,
			Double salarioPorHora) {
		super(id, dni, nombre, telefono, departamento);
		this.horasMes = horasMes;
		this.salarioPorHora = salarioPorHora;
	}

	public EmpleadoTemporal(String dni, String nombre, String telefono,
			Departamento departamento, Integer horasMes, Double salarioPorHora) {
		super(dni, nombre, telefono, departamento);
		this.horasMes = horasMes;
		this.salarioPorHora = salarioPorHora;
	}
	
	public void setSalarioPorHora(Double salarioPorHora) {
		this.salarioPorHora = salarioPorHora;
	}

	
	public void setHorasMes(Integer horasMes) {
		this.horasMes = horasMes;
	}

	
	public Integer getHorasMes() {
		return this.horasMes;
	}


	public Double getSalarioPorHora() {
		return this.salarioPorHora;
	}

	public Double calcularSueldo() {
		return this.horasMes * this.salarioPorHora;
	}
}