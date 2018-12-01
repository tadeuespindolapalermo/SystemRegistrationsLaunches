package br.com.tadeudeveloper.systemregistrationslaunches.DAO;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.tadeudeveloper.systemregistrationslaunches.util.JPAUtil;

@Named
public class GenericDAO<T> implements Serializable {	
	
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;
	
	@Inject
	private JPAUtil jpaUtil;

	public void save(T entity) {
		//EntityManager entityManager = JPAUtil.geEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		entityManager.persist(entity);
		entityTransaction.commit();
		//entityManager.close();
	}
	
	public T merge(T entity) {
		//EntityManager entityManager = JPAUtil.geEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		T returnEntity = entityManager.merge(entity);
		entityTransaction.commit();
		//entityManager.close();
		return returnEntity;
	}
	
	public void delete(T entity) {
		//EntityManager entityManager = JPAUtil.geEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		entityManager.remove(entity);
		entityTransaction.commit();
		//entityManager.close();
	}
	
	public Boolean deleteById(T entity) {
		try {
			//EntityManager entityManager = JPAUtil.geEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			//Object id = JPAUtil.getPrimaryKey(entity);
			Object id = jpaUtil.getPrimaryKey(entity);
			if(id != null) {
				entityManager.createQuery("delete from " + entity.getClass().getName() + " where id = " + id).executeUpdate();
				entityTransaction.commit();
				//entityManager.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;		
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getListEntity(Class<T> entity) {
		//EntityManager entityManager = JPAUtil.geEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		List<T> returnList = entityManager.createQuery("from " + entity.getName()).getResultList();		
		entityTransaction.commit();
		//entityManager.close();		
		return returnList;
	}
	
}
