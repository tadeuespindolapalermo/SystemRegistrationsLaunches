package br.com.tadeudeveloper.systemregistrationslaunches.bean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlSelectOneMenu;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import br.com.tadeudeveloper.systemregistrationslaunches.DAO.GenericDAO;
import br.com.tadeudeveloper.systemregistrationslaunches.entities.Cidades;
import br.com.tadeudeveloper.systemregistrationslaunches.entities.Estados;
import br.com.tadeudeveloper.systemregistrationslaunches.entities.People;
import br.com.tadeudeveloper.systemregistrationslaunches.repository.InterfacePessoaDAO;
//import br.com.tadeudeveloper.systemregistrationslaunches.repository.InterfacePessoaDAOImpl;
import br.com.tadeudeveloper.systemregistrationslaunches.util.JPAUtil;

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
	
	private List<SelectItem> estados;
	
	private List<SelectItem> cidades;
	
	public void searchCep(AjaxBehaviorEvent event) {
		try {
			URL url = new URL("https://viacep.com.br/ws/" + people.getCep() + "/json/");
			URLConnection connection = url.openConnection();
			InputStream inputStream = connection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String cep = "";
			StringBuilder jsonCep = new StringBuilder();
			while((cep = bufferedReader.readLine()) != null) {
				jsonCep.append(cep);
			}
			
			People gsonAux = new Gson().fromJson(jsonCep.toString(), People.class);			
			
			people.setCep(gsonAux.getCep());
			people.setLogradouro(gsonAux.getLogradouro());
			people.setComplemento(gsonAux.getComplemento());
			people.setBairro(gsonAux.getBairro());
			people.setLocalidade(gsonAux.getLocalidade());
			people.setUf(gsonAux.getUf());
			people.setUnidade(gsonAux.getUnidade());
			people.setIbge(gsonAux.getIbge());
			people.setGia(gsonAux.getGia());
			
		} catch (Exception e) {			
			e.printStackTrace();
			showMessage("Erro ao consultar o CEP!");
		}
	}
	
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
	
	public List<SelectItem> getEstados() {
		estados = interfacePessoaDAO.listarEstados();
		return estados;
	}
	
	public List<SelectItem> getCidades() {
		return cidades;
	}
	
	public void setCidades(List<SelectItem> cidades) {
		this.cidades = cidades;
	}
	
	/*public void carregarCidades(AjaxBehaviorEvent event) {
		
		String codigoEstado = (String) event.getComponent().getAttributes().get("submittedValue");		
		
		if(codigoEstado != null) {
			Estados estado = jpaUtil.getEntityManager().find(Estados.class, Long.parseLong(codigoEstado));
			
			if(estado != null) {
				people.setEstados(estado);
				
				List<Cidades> cidades = jpaUtil.getEntityManager()
						.createQuery("from Cidades where estados.id = " 
						+ codigoEstado).getResultList();
				
				List<SelectItem> selectItemsCidade = new ArrayList<>();
				for (Cidades cidade : cidades) {
					selectItemsCidade.add(new SelectItem(cidade, cidade.getNome()));
				}
				setCidades(selectItemsCidade);
			}
		}
	}*/
	
	public void carregarCidades(AjaxBehaviorEvent event) {		
		
		Estados estado = (Estados) ((HtmlSelectOneMenu)event.getSource()).getValue();	
			
		if(estado != null) {
			people.setEstados(estado);
			
			/*List<Cidades> cidades = JPAUtil.getEntityManager()
					.createQuery("from Cidades where estados.id = " 
					+ estado.getId()).getResultList();*/
			
			TypedQuery<Cidades> cidadesQuery = JPAUtil.getEntityManager()
					.createQuery("from Cidades where estados.id = " 
					+ estado.getId(), Cidades.class);
			
			List<Cidades> cidades = cidadesQuery.getResultList();
			
			List<SelectItem> selectItemsCidade = new ArrayList<>();
			for (Cidades cidade : cidades) {
				selectItemsCidade.add(new SelectItem(cidade, cidade.getNome()));
			}
			setCidades(selectItemsCidade);
		}
	}
	
	public void editar() {
		if (people.getCidades() != null) {
			
			Estados estado = people.getCidades().getEstados();
			people.setEstados(estado);
			
			/*List<Cidades> cidades = JPAUtil.getEntityManager()
					.createQuery("from Cidades where estados.id = " 
					+ estado.getId()).getResultList();*/
			
			TypedQuery<Cidades> cidadesQuery = JPAUtil.getEntityManager()
					.createQuery("from Cidades where estados.id = " 
					+ estado.getId(), Cidades.class);
			
			List<Cidades> cidades = cidadesQuery.getResultList();
			
			List<SelectItem> selectItemsCidade = new ArrayList<>();
			for (Cidades cidade : cidades) {
				selectItemsCidade.add(new SelectItem(cidade, cidade.getNome()));
			}
			setCidades(selectItemsCidade);
			
		}
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
	
	public String dislodge() {		
		// remove user in session
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().remove("userLogged");	
		
		// Obtém o controle da sessão do usuário
		HttpServletRequest httpServletRequest = (HttpServletRequest) 
				context.getExternalContext().getRequest();
		
		// invalida a sessão do usuário
		httpServletRequest.getSession().invalidate();
		
		return "index.jsf";
	}
	
	public Boolean allowAccess(String access) {		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		People peopleSearch = (People) externalContext.getSessionMap().get("userLogged");		
		return peopleSearch.getProfileUser().equals(access);
	}	
	
}
