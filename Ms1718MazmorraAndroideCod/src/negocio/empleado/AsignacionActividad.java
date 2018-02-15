package negocio.empleado;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;

import negocio.actividad.Actividad;

import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.actividad.AsignacionActividad.findByhoras", query = "select obj from AsignacionActividad obj where obj.horas = :horas"),
		@NamedQuery(name = "negocio.actividad.AsignacionActividad.findByempleado", query = "select obj from AsignacionActividad obj where obj.empleado = :empleado"),
		@NamedQuery(name = "negocio.actividad.AsignacionActividad.findByactividad", query = "select obj from AsignacionActividad obj where obj.actividad = :actividad"),
		@NamedQuery(name = "negocio.actividad.AsignacionActividad.findByasignacionActividadId", query = "select obj from AsignacionActividad obj where obj.asignacionActividadId = :asignacionActividadId") })
public class AsignacionActividad {


	@EmbeddedId
	private AsignacionActividadId asignacionActividadId;

	@ManyToOne
	@MapsId("empleadoClave")
	private Empleado empleado;

	@ManyToOne
	@MapsId("actividadClave")
	private Actividad actividad;

	@Version
	private Integer version;
	private Integer horas;

	public AsignacionActividad() {
	}

	public AsignacionActividad(Empleado empleado, Actividad actividad,
			Integer horas) {
		this.empleado = empleado;
		this.actividad = actividad;
		this.horas = horas;
	}

	public AsignacionActividad(Empleado empleado, Actividad actividad) {
		this.empleado = empleado;
		this.actividad = actividad;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public Actividad getActividad() {
		return this.actividad;
	}

	public Integer getHoras() {
		return this.horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}
}