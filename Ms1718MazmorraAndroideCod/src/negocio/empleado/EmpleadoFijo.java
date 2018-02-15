
package negocio.empleado;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import negocio.departamento.Departamento;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.empleado.EmpleadoFijo.findBySueldoMensual", query = "select obj from EmpleadoFijo obj where obj.sueldoMensual = :sueldoMensual"),
		@NamedQuery(name = "negocio.empleado.EmpleadoFijo.findByImpuestos", query = "select obj from EmpleadoFijo obj where obj.impuestos = :impuestos") })
public class EmpleadoFijo extends Empleado {
	protected Double sueldoMensual;
	protected Double impuestos;

	public EmpleadoFijo() {
	}

	public EmpleadoFijo(Integer id, String dni, String nombre, String telefono,
			Departamento departamento, Double sueldoMensual, Double impuestos) {
		super(id, dni, nombre, telefono, departamento);
		this.sueldoMensual = sueldoMensual;
		this.impuestos = impuestos;
	}

	public EmpleadoFijo(String dni, String nombre, String telefono,
			Departamento departamento, Double sueldoMensual, Double impuestos) {
		super(dni, nombre, telefono, departamento);
		this.sueldoMensual = sueldoMensual;
		this.impuestos = impuestos;
	}

	public void setImpuestos(Double impuestos) {
		this.impuestos = impuestos;
	}
	
	public void setSueldoMensual(Double sueldoMensual) {
		this.sueldoMensual = sueldoMensual;
	}
	
	public Double getSueldoMensual() {
		return this.sueldoMensual;
	}
	
	public Double getImpuestos() {
		return this.impuestos;
	}

	public Double calcularSueldo() {
		return this.sueldoMensual * (1.0 - impuestos);
	}
}