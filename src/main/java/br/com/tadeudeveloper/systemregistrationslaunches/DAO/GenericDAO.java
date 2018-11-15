package br.com.tadeudeveloper.systemregistrationslaunches.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.tadeudeveloper.systemregistrationslaunches.util.JPAUtil;

public class GenericDAO<T> {

	public void save(T entity) {
		EntityManager entityManager = JPAUtil.geEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		entityManager.persist(entity);
		entityTransaction.commit();
		entityManager.close();
	}
	
	public T merge(T entity) {
		EntityManager entityManager = JPAUtil.geEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		T returnEntity = entityManager.merge(entity);
		entityTransaction.commit();
		entityManager.close();
		return returnEntity;
	}
	
	public void delete(T entity) {
		EntityManager entityManager = JPAUtil.geEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		entityManager.remove(entity);
		entityTransaction.commit();
		entityManager.close();
	}
	
	public void deleteById(T entity) {
		EntityManager entityManager = JPAUtil.geEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Object id = JPAUtil.getPrimaryKey(entity);
		entityManager.createQuery("delete from " + entity.getClass().getName() + " where id = " + id).executeUpdate();
		entityTransaction.commit();
		entityManager.close();
	}
	
	public List<T> getListEntity(Class<T> entity) {
		EntityManager entityManager = JPAUtil.geEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		List<T> returnList = entityManager.createQuery("from " + entity.getName()).getResultList();		
		entityTransaction.commit();
		entityManager.close();		
		return returnList;
	}
	
}
