package br.com.tadeudeveloper.systemregistrationslaunches.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.tadeudeveloper.systemregistrationslaunches.entities.People;

@Named
public class InterfacePessoaDAOImpl implements InterfacePessoaDAO, Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;

	@Override
	public People searchUser(String login, String password) {		
		People people = null;		
		//EntityManager entityManager = JPAUtil.geEntityManager();		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		people = (People) entityManager.createQuery("select p from People p where p.login = '" + login + "' and p.password = '" + password + "'").getSingleResult();		
		entityTransaction.commit();
		//entityManager.close();		
		return people;
	}

}
