
package negocio.departamento;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;



public class SADepartamentoImp implements SADepartamento {
	
	@Override
	public Integer altaDepartamento(Departamento departamento) {
		int respuesta = 0;
		EntityManagerFactory entityManagerFactory;
		EntityManager entityManager;
		EntityTransaction transaction = null;
		
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("Ms1718MazmorraAndroide");
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			
			transaction.begin();
			
			Query query = entityManager.createNamedQuery("negocio.departamento.Departamento.findByNombre", Departamento.class);
			query.setParameter("nombre", departamento.getNombre());
			Departamento departamentoConMismoNombre = null;
			List<Departamento> resultList = query.getResultList();
			
			if (!resultList.isEmpty()) {
				departamentoConMismoNombre = (Departamento) resultList.get(0);
			}
			
			if (departamentoConMismoNombre == null) {
				entityManager.persist(departamento);
				transaction.commit();
				respuesta = 0;
			} else if (departamentoConMismoNombre.getActivo()) {
				transaction.rollback();
				respuesta = -1;
			} else {
				departamentoConMismoNombre.setActivo(true);
				departamentoConMismoNombre.setAbreviatura(departamento.getAbreviatura());
				departamentoConMismoNombre.setNombre(departamento.getNombre());
				departamentoConMismoNombre.setFechaCreacion(departamento.getFechaCreacion());
				transaction.commit();
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
	public Integer actualizarDepartamento(Departamento departamento) {
		Integer respuesta = 0;
		EntityManagerFactory entityManagerFactory; 
		EntityManager entityManager;
		EntityTransaction transaction = null;
		
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("Ms1718MazmorraAndroide");
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			
			transaction.begin();
			
			Query query = entityManager.createNamedQuery("negocio.departamento.Departamento.findByNombre", Departamento.class);
			query.setParameter("nombre", departamento.getNombre());
			
			Departamento departamentoBBDD = entityManager.find(Departamento.class, departamento.getId());
			Departamento departamentoConMismoNombre = null;
			
			List<Departamento> resultList = query.getResultList();
			
			if (!resultList.isEmpty()) {
				departamentoConMismoNombre = (Departamento) resultList.get(0);
			}
			
			if (departamentoBBDD == null) {
				transaction.rollback();
				respuesta = -1;
			} else if (departamentoConMismoNombre != null && departamentoConMismoNombre.getId() != departamento.getId()) {
				transaction.rollback();
				respuesta = -2;
			} else if (departamentoBBDD.getActivo()) {
				departamentoBBDD.setNombre(departamento.getNombre());
				departamentoBBDD.setAbreviatura(departamento.getAbreviatura());
				departamentoBBDD.setFechaCreacion(departamento.getFechaCreacion());
				transaction.commit();
				respuesta = 0;
			} else {
				departamentoBBDD.setActivo(true);
				departamentoBBDD.setNombre(departamento.getNombre());
				departamentoBBDD.setAbreviatura(departamento.getAbreviatura());
				departamentoBBDD.setFechaCreacion(departamento.getFechaCreacion());
				transaction.commit();
				respuesta = -3;
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
	public Integer bajaDepartamento(Integer id) {
		Integer respuesta = 0;
		EntityManagerFactory entityManagerFactory;
		EntityManager entityManager;
		EntityTransaction transaction = null;
		
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("Ms1718MazmorraAndroide");
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			Departamento departamento = entityManager.find(Departamento.class, id);
			
			if (departamento == null) {
				transaction.rollback();
				respuesta = -1;
			} else if (!departamento.getActivo()) {
				transaction.rollback();
				respuesta = -3;
			} else if (departamento.getNumeroDeEmpleadosActivos() == 0) {
				departamento.setActivo(false);
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
	public Departamento mostrarUnDepartamento(Integer id) throws Exception {
		Departamento departamento = null;
		try {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Ms1718MazmorraAndroide");
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			
			departamento = entityManager.find(Departamento.class, id);
			
			entityManager.close();
			entityManagerFactory.close();
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la BBDD");
		}
		
		return departamento;
	}

	@Override
	public List<Departamento> mostrarTodosDepartamentos() throws Exception {
		List<Departamento> departamentos = null;
		try {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Ms1718MazmorraAndroide");
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			Query query = entityManager.createNamedQuery("negocio.departamento.Departamento.findAllDepartments", Departamento.class);
			
			departamentos = query.getResultList();
			
			entityManager.close();
			entityManagerFactory.close();
		} catch (Exception e) {
			throw new Exception("Error en la conexion a la BBDD");
		}
		
		return departamentos;
	}

	@Override
	public Double calcularSueldoNetoDepartamento(Integer id) {
		Double sueldoNeto = 0.0;
		EntityManagerFactory entityManagerFactory;
		EntityManager entityManager;
		EntityTransaction transaction = null;
		
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("Ms1718MazmorraAndroide");
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			
			transaction.begin();
			Departamento departamento = entityManager.find(Departamento.class, id, LockModeType.OPTIMISTIC);
			
			if (departamento == null) {
				transaction.rollback();
				sueldoNeto = -1.0;
			} else if (!departamento.getActivo()) {
				transaction.rollback();
				sueldoNeto = -2.0;
			} else {
				sueldoNeto = departamento.calcularSueldoNeto();
				transaction.commit();
			}
			
			entityManager.close();
			entityManagerFactory.close();
		} catch (RollbackException e) {
			sueldoNeto = -5.0;
		} catch (Exception e) {
			sueldoNeto = -10.0;
		}
		
		return sueldoNeto;
	}
}