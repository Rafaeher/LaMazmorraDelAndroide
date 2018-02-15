package negocio.empleado;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AsignacionActividadId implements Serializable {

	private static final long serialVersionUID = 0;
	private Integer empleadoClave;
	private Integer actividadClave;

	public AsignacionActividadId() {
	}

	public AsignacionActividadId(Integer empleadoClave, Integer actividadClave) {
		this.empleadoClave = empleadoClave;
		this.actividadClave = actividadClave;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof AsignacionActividadId))
			return false;
		AsignacionActividadId pk = (AsignacionActividadId) obj;
		if ((empleadoClave == null && pk.empleadoClave != null)
				|| (empleadoClave != null && !empleadoClave
						.equals(pk.empleadoClave)))
			return false;
		if ((actividadClave == null && pk.actividadClave != null)
				|| (actividadClave != null && !actividadClave
						.equals(pk.actividadClave)))
			return false;
		return true;
	}

	public int hashCode() {
		int hashcode = 0;
		if (empleadoClave != null) {
			hashcode += empleadoClave.hashCode();
		}
		if (actividadClave != null) {
			hashcode += actividadClave.hashCode();
		}
		return hashcode;
	}
}