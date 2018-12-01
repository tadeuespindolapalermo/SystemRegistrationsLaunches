package br.com.tadeudeveloper.systemregistrationslaunches.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.tadeudeveloper.systemregistrationslaunches.DAO.GenericDAO;
import br.com.tadeudeveloper.systemregistrationslaunches.entities.People;
import br.com.tadeudeveloper.systemregistrationslaunches.repository.InterfacePessoaDAO;
//import br.com.tadeudeveloper.systemregistrationslaunches.repository.InterfacePessoaDAOImpl;

///@ManagedBean(name = "peopleBeanJPA")
@Named(value = "peopleBeanJPA")
@ViewScoped
public class PeopleBeanJPA implements Serializable {		
	
	private static final long serialVersionUID = 1L;
	
	private People people = new People();	
	private List<People> peoples = new ArrayList<>();
	//private GenericDAO<People> genericDAO = new GenericDAO<>();	
	//private InterfacePessoaDAO interfacePessoaDAO = new InterfacePessoaDAOImpl();
	
	@Inject
	private GenericDAO<People> genericDAO;	
	
	@Inject
	private InterfacePessoaDAO interfacePessoaDAO;
	
	public String save() {
		try {
			people = genericDAO.merge(people);	
			if(people != null) {
				listPeople();
				showMessage("Registered successfully!");
			} else {
				showMessage("Could not include record!");
			}
		} catch (Exception e) {
			showMessage("Error adding registry!");
			e.printStackTrace();
		}		
		return "";
	}
	
	public void registerLog() {
		System.out.println("Test Log");
	}
	
	public void changeValue(ValueChangeEvent event) {
		System.out.println("Old value " + event.getOldValue());
		System.out.println("New value " + event.getNewValue());
	}
	
	public void changeValueLastName(ValueChangeEvent event) {
		System.out.println("Old value " + event.getOldValue());
		System.out.println("New value " + event.getNewValue());
	}
	
	private void showMessage(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(msg);
		context.addMessage(null, message);		
	}

	public List<People> getPeoples() {
		return peoples;
	}
	
	public String newPeople() {		
		people = new People();
		return "";
	}
	
	public String cleanForm() {		
		people = new People();
		return "";
	}
	
	public String remove() {
		try {
			Boolean result = genericDAO.deleteById(people);		
			if(result) {
				people = new People();
				listPeople();
				showMessage("Removed successfully");	
			} else {
				showMessage("Select a record to remove");
			}
		} catch (Exception e) {
			showMessage("Error deleting the registry");
			e.printStackTrace();
		}				
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
	
	public String access() {		
		People peopleSearch = interfacePessoaDAO.searchUser(people.getLogin(), people.getPassword());		
		if(peopleSearch != null) { // user OK
			// add user in session
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("userLogged", peopleSearch);
			
			// peopleSearch.getLogin() para ser usado somente com:
			// String userLogged = (String) session.getAttribute("userLogged"); // Filter
			//externalContext.getSessionMap().put("userLogged", peopleSearch.getLogin());
			return "first-pageJPA.jsf";
		}		
		return "index.jsf";
	}
	
	public Boolean allowAccess(String access) {		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		People peopleSearch = (People) externalContext.getSessionMap().get("userLogged");		
		return peopleSearch.getProfileUser().equals(access);
	}	
	
}
