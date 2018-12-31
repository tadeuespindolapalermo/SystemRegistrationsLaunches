package br.com.tadeudeveloper.systemregistrationslaunches.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.com.tadeudeveloper.systemregistrationslaunches.entities.Estados;
import br.com.tadeudeveloper.systemregistrationslaunches.entities.People;
import br.com.tadeudeveloper.systemregistrationslaunches.util.JPAUtil;

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

	@Override
	public List<SelectItem> listarEstados() {
		List<SelectItem> selectItems = new ArrayList<>();
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		//List<Estados> estados = entityManager.createQuery("from Estados").getResultList();
		
		TypedQuery<Estados> estadosQuery = entityManager.createQuery("from Estados", Estados.class);
		List<Estados> estados = estadosQuery.getResultList();
		
		for (Estados estado : estados) {
			selectItems.add(new SelectItem(estado, estado.getNome()));
		}
		return selectItems;
	}

}
