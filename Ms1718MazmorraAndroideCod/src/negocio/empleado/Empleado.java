package negocio.empleado;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Version;

import negocio.departamento.Departamento;
import javax.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.empleado.Empleado.findByid", query = "select obj from Empleado obj where obj.id = :id"),
		@NamedQuery(name = "negocio.empleado.Empleado.findByversion", query = "select obj from Empleado obj where obj.version = :version"),
		@NamedQuery(name = "negocio.empleado.Empleado.findBydni", query = "select obj from Empleado obj where obj.dni = :dni"),
		@NamedQuery(name = "negocio.empleado.Empleado.findBynombre", query = "select obj from Empleado obj where obj.nombre = :nombre"),
		@NamedQuery(name = "negocio.empleado.Empleado.findBytelefono", query = "select obj from Empleado obj where obj.telefono = :telefono"),
		@NamedQuery(name = "negocio.empleado.Empleado.findBydepartamento", query = "select obj from Empleado obj where obj.departamento = :departamento"),
		@NamedQuery(name = "negocio.empleado.Empleado.findByactivo", query = "select obj from Empleado obj where obj.activo = :activo"),
		@NamedQuery(name = "negocio.empleado.Empleado.findAllEmployees", query = "select obj from Empleado obj"),
		@NamedQuery(name = "negocio.empleado.Empleado.findByasignacionesActividad", query = "select obj from Empleado obj where obj.asignacionesActividad = :asignacionesActividad") })
public abstract class Empleado {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@ManyToOne
	protected Departamento departamento;

	@Version
	protected Integer version;
	protected String dni;
	protected String nombre;
	protected String telefono;
	protected Boolean activo;

	@OneToMany(mappedBy = "empleado")
	protected List<AsignacionActividad> asignacionesActividad;

	public Empleado() {
	}

	public Empleado(Integer id, String dni, String nombre, String telefono,
			Departamento departamento) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.departamento = departamento;
		this.activo = true;
		this.asignacionesActividad = new ArrayList<>();
	}

	public Empleado(String dni, String nombre, String telefono,
			Departamento departamento) {
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.departamento = departamento;
		this.activo = true;
		this.asignacionesActividad = new ArrayList<>();
	}

	public abstract Double calcularSueldo();

	public String getDNI() {
		return this.dni;
	}

	public void setDNI(String DNI) {
		this.dni = DNI;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Integer getId() {
		return this.id;
	}

	public void asignarActividad(AsignacionActividad asignacionActividad) {
		this.asignacionesActividad.add(asignacionActividad);
	}

	public void desasignarActividad(AsignacionActividad asignacionActividad) {
		this.asignacionesActividad.remove(asignacionActividad);
	}

	public List<AsignacionActividad> getAsignaciones() {
		return this.asignacionesActividad;
	}
}