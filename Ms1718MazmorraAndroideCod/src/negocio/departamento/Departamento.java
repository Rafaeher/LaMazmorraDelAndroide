
package negocio.departamento;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Version;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import negocio.empleado.Empleado;
import javax.persistence.OneToMany;


@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.departamento.Departamento.findByid", query = "select obj from Departamento obj where obj.id = :id"),
		@NamedQuery(name = "negocio.departamento.Departamento.findByversion", query = "select obj from Departamento obj where obj.version = :version"),
		@NamedQuery(name = "negocio.departamento.Departamento.findByNombre", query = "select obj from Departamento obj where obj.nombre = :nombre"),
		@NamedQuery(name = "negocio.departamento.Departamento.findByabreviatura", query = "select obj from Departamento obj where obj.abreviatura = :abreviatura"),
		@NamedQuery(name = "negocio.departamento.Departamento.findByfechaCreacion", query = "select obj from Departamento obj where obj.fechaCreacion = :fechaCreacion"),
		@NamedQuery(name = "negocio.departamento.Departamento.findByempleados", query = "select obj from Departamento obj where obj.empleados = :empleados"), 
		@NamedQuery(name = "negocio.departamento.Departamento.findAllDepartments", query = "select obj from Departamento obj"), 
		@NamedQuery(name = "negocio.departamento.Departamento.findBynumeroDeEmpleadosActivos", query = "select obj from Departamento obj where obj.numeroDeEmpleadosActivos = :numeroDeEmpleadosActivos") })
public class Departamento {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Version
	private Integer version;
	private String nombre;
	private String abreviatura;
	private Boolean activo;
	private Date fechaCreacion;
	private Integer numeroDeEmpleadosActivos;
	
	@OneToMany(mappedBy = "departamento")
	private List<Empleado> empleados;
	
	
	public Departamento() {
		this.empleados = new ArrayList<>();
		this.fechaCreacion = new Date();
		this.numeroDeEmpleadosActivos = 0;
		this.activo = true;
	}
	
	public Departamento(String nombre, String abreviatura) {
		this.empleados = new ArrayList<>();
		this.fechaCreacion = new Date();
		this.numeroDeEmpleadosActivos = 0;
		this.nombre = nombre;
		this.abreviatura = abreviatura;
		this.activo = true;
	}
	
	public Departamento(Integer id, String nombre, String abreviatura, Date fechaCreacion) {
		this.id = id;
		this.empleados = new ArrayList<>();
		this.fechaCreacion = fechaCreacion;
		this.numeroDeEmpleadosActivos = 0;
		this.nombre = nombre;
		this.abreviatura = abreviatura;
		this.activo = true;
	}
	
	public Departamento(Integer id) {
		this.id = id;
	}

	public Double calcularSueldoNeto() {
		Double sueldoNeto = 0.0;
		
		for (Empleado empleado : empleados) {
			if (empleado.getActivo()) {
				sueldoNeto += empleado.calcularSueldo();
			}
		}
		
		return sueldoNeto;
	}
	
	public void asignarEmpleado(Empleado empleado) {
		this.empleados.add(empleado);
		this.aumentarNumeroDeEmpleadosActivos();
	}
	
	public void desasignarEmpleado(Empleado empleado) {
		if (empleado.getActivo()) {
			this.reducirNumeroDeEmpleadosActivos();
		}
		
		this.empleados.remove(empleado);
	}

	public void aumentarNumeroDeEmpleadosActivos() {
		++this.numeroDeEmpleadosActivos;
	}
	
	public void reducirNumeroDeEmpleadosActivos() {
		--this.numeroDeEmpleadosActivos;
	}
	
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public List<Empleado> getEmpleados() {
		return this.empleados;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public Integer getNumeroDeEmpleadosActivos() {
		return this.numeroDeEmpleadosActivos;
	}
}