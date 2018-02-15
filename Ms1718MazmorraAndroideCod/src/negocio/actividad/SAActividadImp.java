package negocio.actividad;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;

public class SAActividadImp implements SAActividad {

	@Override
	public Integer altaActividad(Actividad actividad) {
		int respuesta = 0;
		EntityManagerFactory entityManagerFactory;
		EntityManager entityManager;
		EntityTransaction transaction = null;

		try {
			entityManagerFactory = Persistence
					.createEntityManagerFactory("Ms1718MazmorraAndroide");
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();

			transaction.begin();
			Query query = entityManager
					.createNamedQuery(
							"negocio.actividad.Actividad.findBynombre",
							Actividad.class);
			query.setParameter("nombre", actividad.getNombre());
			Actividad actividadConMismoNombre = null;

			List<Actividad> resultList = query.getResultList();
			if (!resultList.isEmpty()) {
				actividadConMismoNombre = (Actividad) resultList.get(0);
			}

			if (actividadConMismoNombre == null) {
				entityManager.persist(actividad);
				transaction.commit();
				respuesta = 0;
			} else if (actividadConMismoNombre.getActivo()) {
				transaction.rollback();
				respuesta = -1;
			} else {
				actividadConMismoNombre.setActivo(true);
				actividadConMismoNombre.setNombre(actividad.getNombre());
				actividadConMismoNombre.setDuracion(actividad.getDuracion());
				actividadConMismoNombre.setLugar(actividad.getLugar());
				transaction.commit();
				respuesta = -2;
			}

			entityManager.close();
			entityManagerFactory.close();
		} catch (RollbackException e) {
			transaction.rollback();
			respuesta = -5;
		} catch (Exception e) {
			respuesta = -10;
		}

		return respuesta;
	}

	@Override
	public Integer bajaActividad(Integer id) {
		Integer respuesta = 0;
		EntityManagerFactory entityManagerFactory;
		EntityManager entityManager;
		EntityTransaction transaction = null;

		try {
			entityManagerFactory = Persistence
					.createEntityManagerFactory("Ms1718MazmorraAndroide");
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();

			transaction.begin();
			Actividad actividad = entityManager.find(Actividad.class, id);

			if (actividad == null) {
				transaction.rollback();
				respuesta = -1;
			} else if (!actividad.getActivo()) {
				transaction.rollback();
				respuesta = -3;
			} else if (actividad.getNumeroDeAsignaciones() == 0) {
				actividad.setActivo(false);
				transaction.commit();
				respuesta = 0;
			} else {
				transaction.rollback();
				respuesta = -2;
			}

			entityManager.close();
			entityManagerFactory.close();
		} catch (RollbackException e) {
			respuesta = -5;
		} catch (Exception e) {
			respuesta = -10;
		}

		return respuesta;
	}

	@Override
	public Integer actualizarActividad(Actividad actividad) {
		Integer respuesta;
		EntityManagerFactory entityManagerFactory;
		EntityManager entityManager;
		EntityTransaction transaction = null;

		try {
			entityManagerFactory = Persistence
					.createEntityManagerFactory("Ms1718MazmorraAndroide");
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();

			transaction.begin();

			Actividad actividadBBDD = entityManager.find(Actividad.class,
					actividad.getId());

			Query query = entityManager
					.createNamedQuery(
							"negocio.actividad.Actividad.findBynombre",
							Actividad.class);
			query.setParameter("nombre", actividad.getNombre());

			Actividad actividadconMismoNombre = null;
			List<Actividad> resultList = query.getResultList();

			if (!resultList.isEmpty()) {
				actividadconMismoNombre = (Actividad) resultList.get(0);
			}

			if (actividadBBDD == null) {
				transaction.rollback();
				respuesta = -1;
			} else if (actividadconMismoNombre != null
					&& actividadconMismoNombre.getId() != actividad.getId()) {
				transaction.rollback();
				respuesta = -2;
			} else if (actividadBBDD.getDuracionDeLaMayorAsignacion() > actividad.getDuracion()) {
				transaction.rollback();
				respuesta = -3;
			} else if (actividadBBDD.getActivo()) {
				actividadBBDD.setNombre(actividad.getNombre());
				actividadBBDD.setDuracion(actividad.getDuracion());
				actividadBBDD.setLugar(actividad.getLugar());
				transaction.commit();
				respuesta = 0;
			} else {
				actividadBBDD.setActivo(true);
				actividadBBDD.setNombre(actividad.getNombre());
				actividadBBDD.setDuracion(actividad.getDuracion());
				actividadBBDD.setLugar(actividad.getLugar());
				transaction.commit();
				respuesta = -4;
			}

			entityManager.close();
			entityManagerFactory.close();
		} catch (RollbackException e) {
			respuesta = -5;
		} catch (Exception e) {
			respuesta = -10;
		}

		return respuesta;
	}

	@Override
	public Actividad mostrarUnaActividad(Integer id) throws Exception {
		Actividad actividad = null;
		try {
			EntityManagerFactory entityManagerFactory = Persistence
					.createEntityManagerFactory("Ms1718MazmorraAndroide");
			EntityManager entityManager = entityManagerFactory
					.createEntityManager();

			actividad = entityManager.find(Actividad.class, id);

			entityManager.close();
			entityManagerFactory.close();
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la BBDD");
		}

		return actividad;
	}

	@Override
	public List<Actividad> mostrarTodasActividades() throws Exception {
		List<Actividad> actividades;
		try {
			EntityManagerFactory entityManagerFactory = Persistence
					.createEntityManagerFactory("Ms1718MazmorraAndroide");
			EntityManager entityManager = entityManagerFactory
					.createEntityManager();
			Query query = entityManager.createNamedQuery(
					"negocio.actividad.Actividad.findAllActivities",
					Actividad.class);

			actividades = query.getResultList();

			entityManager.close();
			entityManagerFactory.close();
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la BBDD");
		}

		return actividades;
	}
}