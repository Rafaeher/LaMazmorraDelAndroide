package negocio.actividad;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import negocio.empleado.AsignacionActividad;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.actividad.Actividad.findByid", query = "select obj from Actividad obj where obj.id = :id"),
		@NamedQuery(name = "negocio.actividad.Actividad.findByversion", query = "select obj from Actividad obj where obj.version = :version"),
		@NamedQuery(name = "negocio.actividad.Actividad.findBynombre", query = "select obj from Actividad obj where obj.nombre = :nombre"),
		@NamedQuery(name = "negocio.actividad.Actividad.findBylugar", query = "select obj from Actividad obj where obj.lugar = :lugar"),
		@NamedQuery(name = "negocio.actividad.Actividad.findByduracionEnHoras", query = "select obj from Actividad obj where obj.duracionEnHoras = :duracionEnHoras"),
		@NamedQuery(name = "negocio.actividad.Actividad.findByactivo", query = "select obj from Actividad obj where obj.activo = :activo"),
		@NamedQuery(name = "negocio.actividad.Actividad.findAllActivities", query = "select obj from Actividad obj"),
		@NamedQuery(name = "negocio.actividad.Actividad.findByasignacionesActividad", query = "select obj from Actividad obj where obj.asignacionesActividad = :asignacionesActividad") })
public class Actividad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Version
	private Integer version;
	private String nombre;
	private String lugar;
	private Integer duracionEnHoras;
	private Boolean activo;

	@OneToMany(mappedBy = "actividad")
	private List<AsignacionActividad> asignacionesActividad;

	public Actividad() {

	}

	public Actividad(Integer id, String nombre, String lugar, Integer duracion) {
		this.id = id;
		this.nombre = nombre;
		this.lugar = lugar;
		this.duracionEnHoras = duracion;
		this.asignacionesActividad = new ArrayList<AsignacionActividad>();
	}

	public Actividad(String nombre, Integer duracion, String lugar) {
		this.nombre = nombre;
		this.duracionEnHoras = duracion;
		this.lugar = lugar;
		this.activo = true;
		this.asignacionesActividad = new ArrayList<AsignacionActividad>();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLugar() {
		return this.lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Integer getDuracion() {
		return this.duracionEnHoras;
	}

	public void setDuracion(Integer duracion) {
		this.duracionEnHoras = duracion;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public Integer getNumeroDeAsignaciones() {
		return this.asignacionesActividad.size();
	}

	public List<AsignacionActividad> getAsignaciones() {
		return this.asignacionesActividad;
	}

	public void agregarAsignacion(AsignacionActividad asignacionActividad) {
		this.asignacionesActividad.add(asignacionActividad);
	}

	public void eliminarAsignacion(AsignacionActividad asignacionActividad) {
		this.asignacionesActividad.remove(asignacionActividad);
	}

	public Integer getDuracionDeLaMayorAsignacion() {
		int maxDuracion = 0;
		
		for (AsignacionActividad asignacionActividad : asignacionesActividad) {
			if (asignacionActividad.getHoras() > maxDuracion) {
				maxDuracion = asignacionActividad.getHoras();
			}
		}
		
		return maxDuracion;
	}
}