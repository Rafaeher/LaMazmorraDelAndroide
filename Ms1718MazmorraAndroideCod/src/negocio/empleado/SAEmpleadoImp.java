package negocio.empleado;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import negocio.actividad.Actividad;
import negocio.departamento.Departamento;

public class SAEmpleadoImp implements SAEmpleado {
	
	@Override
	public Integer altaEmpleado(Empleado empleado) {
		int respuesta = 0;
		EntityManagerFactory entityManagerFactory;
		EntityManager entityManager;
		EntityTransaction transaction = null;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("Ms1718MazmorraAndroide");
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();

			transaction.begin();
			
			Query query = entityManager.createNamedQuery("negocio.empleado.Empleado.findBydni", Empleado.class);
			query.setParameter("dni", empleado.getDNI());

			
			Departamento departamento = entityManager.find(Departamento.class,empleado.getDepartamento().getId());
			Empleado empleadoConMismoDNI = null;
			
			List<Empleado> resultList = query.getResultList();
			if (!resultList.isEmpty()) {
				empleadoConMismoDNI = (Empleado) resultList.get(0);
			}
			
			if (departamento == null) {
				transaction.rollback();
				respuesta = -1;
			} else if (!departamento.getActivo()) {
				transaction.rollback();
				respuesta = -2;
			} else if (empleadoConMismoDNI == null) {
				departamento.asignarEmpleado(empleado);
				entityManager.persist(empleado);
				transaction.commit();
				respuesta = 0;
			} else if (empleado.getClass() != empleadoConMismoDNI.getClass()) {
				transaction.rollback();
				respuesta = -3;
			} else if (empleadoConMismoDNI.getActivo()) {
				transaction.rollback();
				respuesta = -4;
			} else {
				Departamento departamentoBBDD = empleadoConMismoDNI.getDepartamento();
				
				empleadoConMismoDNI.setNombre(empleado.getNombre());
				empleadoConMismoDNI.setTelefono(empleado.getTelefono());
				
				if (empleadoConMismoDNI instanceof EmpleadoFijo) {
					Double impuestos = ((EmpleadoFijo) empleado).getImpuestos();
					Double sueldoMensual = ((EmpleadoFijo) empleado).getSueldoMensual();
					
					((EmpleadoFijo) empleadoConMismoDNI).setImpuestos(impuestos);
					((EmpleadoFijo) empleadoConMismoDNI).setSueldoMensual(sueldoMensual);
				} else if (empleadoConMismoDNI instanceof EmpleadoTemporal) {
					Integer horasMes = ((EmpleadoTemporal) empleado).getHorasMes();
					Double salarioPorHora = ((EmpleadoTemporal) empleado).getSalarioPorHora();
					
					((EmpleadoTemporal) empleadoConMismoDNI).setSalarioPorHora(salarioPorHora);
					((EmpleadoTemporal) empleadoConMismoDNI).setHorasMes(horasMes);
				}

				if (departamento.getId() != departamentoBBDD.getId()) {
					departamento.asignarEmpleado(empleadoConMismoDNI);
					departamentoBBDD.desasignarEmpleado(empleadoConMismoDNI);
					empleadoConMismoDNI.setDepartamento(departamento);
				} else {
					departamentoBBDD.aumentarNumeroDeEmpleadosActivos();
				}
				
				empleadoConMismoDNI.setActivo(true);
				transaction.commit();
				respuesta = -6;
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
	public Integer bajaEmpleado(Integer id) {
		int respuesta = 0;
		EntityManagerFactory entityManagerFactory;
		EntityManager entityManager;
		EntityTransaction transaction = null;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("Ms1718MazmorraAndroide");
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();

			transaction.begin();
			
			Empleado empleado = entityManager.find(Empleado.class, id);
			
			if (empleado == null) {
				transaction.rollback();
				respuesta = -1;
			} else if (!empleado.getActivo()) {
				transaction.rollback();
				respuesta = -2;
			} else if (!empleado.getAsignaciones().isEmpty()) {
				transaction.rollback();
				respuesta = -3;
			} else {
				Departamento departamento = empleado.getDepartamento();
				departamento.reducirNumeroDeEmpleadosActivos();
				empleado.setActivo(false);
				
				transaction.commit();
				respuesta = 0;
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
	public Integer actualizarEmpleado(Empleado empleadoEntrada) {
		int respuesta = 0;
		EntityManagerFactory entityManagerFactory;
		EntityManager entityManager;
		EntityTransaction transaction = null;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("Ms1718MazmorraAndroide");
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();

			transaction.begin();
			
			Empleado empleadoConMismoDNI = null;
			Empleado empleado = entityManager.find(Empleado.class, empleadoEntrada.getId());
			
			Query query = entityManager.createNamedQuery("negocio.empleado.Empleado.findBydni", Empleado.class);
			query.setParameter("dni", empleadoEntrada.getDNI());
			List resultList = query.getResultList();
			
			if (!resultList.isEmpty()) {
				empleadoConMismoDNI = (Empleado) resultList.get(0);
			}
			
			Departamento departamento = entityManager.find(Departamento.class, empleadoEntrada.getDepartamento().getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			
			if (empleado == null) {
				transaction.rollback();
				respuesta = -1;
			} else if (empleadoConMismoDNI != null && empleadoConMismoDNI.getId() != empleado.getId()) {
				transaction.rollback();
				respuesta = -2;
			} else if (empleadoEntrada.getClass() != empleado.getClass()) {
				transaction.rollback();
				respuesta = -3;
			} else if (departamento == null) {
				transaction.rollback();
				respuesta = -4;
			} else if (!departamento.getActivo()) {
				transaction.rollback();
				respuesta = -6;
			} else {
				Departamento departamentoAntiguo = empleado.getDepartamento();
				
				if (departamento.getId() != departamentoAntiguo.getId()) {
					departamentoAntiguo.desasignarEmpleado(empleado);
					departamento.asignarEmpleado(empleado);
					empleado.setDepartamento(departamento);
				} else if (!empleado.getActivo()) {
					departamentoAntiguo.aumentarNumeroDeEmpleadosActivos();
				}
				
				if (!empleado.getActivo()) {
					empleado.setActivo(true);
					respuesta = 1;
				}
				
				empleado.setNombre(empleadoEntrada.getNombre());
				empleado.setTelefono(empleadoEntrada.getTelefono());
				empleado.setDNI(empleadoEntrada.getDNI());
				
				if (empleado instanceof EmpleadoFijo) {
					((EmpleadoFijo) empleado).setSueldoMensual(((EmpleadoFijo) empleadoEntrada).getSueldoMensual());
					((EmpleadoFijo) empleado).setImpuestos(((EmpleadoFijo) empleadoEntrada).getImpuestos());
				} else if (empleado instanceof EmpleadoTemporal) {
					((EmpleadoTemporal) empleado).setHorasMes(((EmpleadoTemporal) empleadoEntrada).getHorasMes());
					((EmpleadoTemporal) empleado).setSalarioPorHora((((EmpleadoTemporal) empleadoEntrada).getSalarioPorHora()));
				}
				
				transaction.commit();
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
	public Empleado mostrarUnEmpleado(Integer id) throws Exception {
		try {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Ms1718MazmorraAndroide");
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			
			Empleado empleado = entityManager.find(Empleado.class, id);
			
			entityManager.close();
			entityManagerFactory.close();
			
			return empleado;
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la BBDD");
		}
	}

	@Override
	public List<Empleado> mostrarTodosEmpleados() throws Exception {
		try {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Ms1718MazmorraAndroide");
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			Query query = entityManager.createNamedQuery("negocio.empleado.Empleado.findAllEmployees", Empleado.class);
			
			List<Empleado> empleados = query.getResultList();
			
			entityManager.close();
			entityManagerFactory.close();
			
			return empleados;
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la BBDD");
		}
	}

	@Override
	public Integer asignarActividad(Integer idEmpleado, Integer horas, Integer idActividad) {
		Integer respuesta = 0;
		EntityManagerFactory entityManagerFactory;
		EntityManager entityManager;
		EntityTransaction entityTransaction = null;
		
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("Ms1718MazmorraAndroide");
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			
			Empleado empleado = entityManager.find(Empleado.class, idEmpleado, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			Actividad actividad = entityManager.find(Actividad.class, idActividad, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			AsignacionActividad asignacionActividad = entityManager.find(
					AsignacionActividad.class, new AsignacionActividadId(idEmpleado, idActividad));
			
			if (empleado == null) {
				entityTransaction.rollback();
				respuesta = -1;
			} else if (actividad == null) {
				entityTransaction.rollback();
				respuesta = -2;
			} else if (!empleado.getActivo()) {
				entityTransaction.rollback();
				respuesta = -3;
			} else if (!actividad.getActivo()) {
				entityTransaction.rollback();
				respuesta = -4;
			} else if (horas > actividad.getDuracion()) {
				entityTransaction.rollback();
				respuesta = -6;
			} else if (asignacionActividad == null) {		
				asignacionActividad = new AsignacionActividad(empleado, actividad, horas);
				empleado.asignarActividad(asignacionActividad);
				actividad.agregarAsignacion(asignacionActividad);
				entityManager.persist(asignacionActividad);
				
				entityTransaction.commit();
				respuesta = 0;
			} else {
				asignacionActividad.setHoras(horas);
				entityTransaction.commit();
				respuesta = -7;
			}
			
			entityManager.close();
			entityManagerFactory.close();
			
		} catch(RollbackException e) {
			respuesta = -5;
		} catch(Exception e) {
			respuesta = -10;
		}
		
		return respuesta;
	}

	@Override
	public Integer desasignarActividad(Integer idEmpleado, Integer idActividad) {
		Integer respuesta = 0;
		EntityManagerFactory entityManagerFactory;
		EntityManager entityManager;
		EntityTransaction entityTransaction = null;
		
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("Ms1718MazmorraAndroide");
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();

			Empleado empleado = entityManager.find(Empleado.class, idEmpleado, LockModeType.OPTIMISTIC);
			Actividad actividad = entityManager.find(Actividad.class, idActividad, LockModeType.OPTIMISTIC);
			
			AsignacionActividad asignacionActividad = entityManager.find(
					AsignacionActividad.class, new AsignacionActividadId(idEmpleado, idActividad));
			
			if (empleado == null) {
				entityTransaction.rollback();
				respuesta = -1;
			} else if (!empleado.getActivo()) {
				entityTransaction.rollback();
				respuesta = -2;
			} else if (actividad == null) {
				entityTransaction.rollback();
				respuesta = -3;
			} else if (!actividad.getActivo()) {
				entityTransaction.rollback();
				respuesta = -4;
			} else if (asignacionActividad == null) {
				entityTransaction.rollback();
				respuesta = -6;
			} else {
				empleado.desasignarActividad(asignacionActividad);
				actividad.eliminarAsignacion(asignacionActividad);
				entityManager.remove(asignacionActividad);
				entityTransaction.commit();
				respuesta = 0;
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
}