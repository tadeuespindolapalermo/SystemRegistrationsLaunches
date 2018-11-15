package br.com.tadeudeveloper.systemregistrationslaunches.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tadeudeveloper.systemregistrationslaunches.DAO.GenericDAO;
import br.com.tadeudeveloper.systemregistrationslaunches.entities.People;

@ManagedBean(name = "peopleBeanJPA")
@ViewScoped 
public class PeopleBeanJPA {
	
	private People people = new People();	
	private GenericDAO<People> genericDAO = new GenericDAO<>();
	private List<People> peoples = new ArrayList<>();
	
	public String save() {
		people = genericDAO.merge(people);	
		listPeople();
		return "";
	}
	
	public List<People> getPeoples() {
		return peoples;
	}
	
	public String newPeople() {
		people = new People();
		return "";
	}
	
	public String remove() {
		genericDAO.deleteById(people);
		people = new People();
		listPeople();
		return "";
	}
	
	@PostConstruct
	public void listPeople() {
		peoples = genericDAO.getListEntity(People.class);
	}
	
	public void setPeople(People people) {
		this.people = people;
	}
	
	public People getPeople() {
		return people;
	}
	
}
