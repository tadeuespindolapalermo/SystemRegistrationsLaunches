package br.com.tadeudeveloper.systemregistrationslaunches.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory factory;
	
	static {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("SystemRegistrationsLaunches");
		}			
	}
	
	public static EntityManager geEntityManager() {
		return factory.createEntityManager();
	}
	
	public static Object getPrimaryKey(Object entity) {
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}
	
}
