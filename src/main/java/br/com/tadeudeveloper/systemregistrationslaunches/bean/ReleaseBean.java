package br.com.tadeudeveloper.systemregistrationslaunches.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.tadeudeveloper.systemregistrationslaunches.DAO.GenericDAO;
import br.com.tadeudeveloper.systemregistrationslaunches.entities.People;
import br.com.tadeudeveloper.systemregistrationslaunches.entities.Release;
import br.com.tadeudeveloper.systemregistrationslaunches.repository.InterfaceReleaseDAO;
//import br.com.tadeudeveloper.systemregistrationslaunches.repository.InterfaceReleaseDAOImpl;

@ViewScoped
//@ManagedBean(name = "releaseBean")
@Named(value = "releaseBean")
public class ReleaseBean implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	private Release release = new Release();
	private List<Release> releases = new ArrayList<>();	
	//private GenericDAO<Release> genericDAO = new GenericDAO<>();
	//private InterfaceReleaseDAO interfaceReleaseDAO = new InterfaceReleaseDAOImpl();
	
	@Inject
	private GenericDAO<Release> genericDAO;
	
	@Inject
	private InterfaceReleaseDAO interfaceReleaseDAO;
	
	public String save() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		People peopleSearch = (People) externalContext.getSessionMap().get("userLogged");		
		release.setUser(peopleSearch);
		release = genericDAO.merge(release);		
		listReleases();		
		return "";
	}
	
	@PostConstruct
	private void listReleases() {		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		People peopleSearch = (People) externalContext.getSessionMap().get("userLogged");
		releases = interfaceReleaseDAO.search(peopleSearch.getId());
	}

	public String newRelease() {
		release = new Release();
		return "";
	}
	
	public String remove() {
		try {
			Boolean result = genericDAO.deleteById(release);
			if(result) {
				release = new Release();
				listReleases();
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "";
	}
	
	public Release getRelease() {
		return release;
	}
	
	public void setRelease(Release release) {
		this.release = release;
	}
	
	public GenericDAO<Release> getGenericDAO() {
		return genericDAO;
	}
	
	public void setGenericDAO(GenericDAO<Release> genericDAO) {
		this.genericDAO = genericDAO;
	}
	
	public List<Release> getReleases() {
		return releases;
	}
	
	public void setReleases(List<Release> releases) {
		this.releases = releases;
	}	
	
}
