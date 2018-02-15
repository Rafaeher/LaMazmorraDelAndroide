package negocio.actividad;

import java.util.List;

public interface SAActividad {

	public Integer altaActividad(Actividad actividad);

	public Integer bajaActividad(Integer id);

	public Integer actualizarActividad(Actividad actividad);

	public Actividad mostrarUnaActividad(Integer id) throws Exception;

	public List<Actividad> mostrarTodasActividades() throws Exception;
}