package br.com.tadeudeveloper.systemregistrationslaunches.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAUtil {

	private static EntityManagerFactory factory;
	
	public JPAUtil() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("SystemRegistrationsLaunches");
		}	
	}	
	
	@Produces
	@RequestScoped
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	public Object getPrimaryKey(Object entity) {
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}
	
}
