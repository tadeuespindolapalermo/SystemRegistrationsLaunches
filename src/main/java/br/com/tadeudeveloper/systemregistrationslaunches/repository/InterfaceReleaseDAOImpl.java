package br.com.tadeudeveloper.systemregistrationslaunches.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.tadeudeveloper.systemregistrationslaunches.entities.Release;

@Named
public class InterfaceReleaseDAOImpl implements InterfaceReleaseDAO, Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Release> search(Long idUser) {
		List<Release> listReleases = null;		
		//EntityManager entityManager = JPAUtil.geEntityManager();		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		listReleases = entityManager.createQuery("from Release where user.id = " + idUser).getResultList();		
		transaction.commit();
		//entityManager.close();
		return listReleases;
	}

}
