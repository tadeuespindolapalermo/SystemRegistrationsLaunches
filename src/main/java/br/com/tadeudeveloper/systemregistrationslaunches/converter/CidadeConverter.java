package br.com.tadeudeveloper.systemregistrationslaunches.converter;

import java.io.Serializable;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;

import br.com.tadeudeveloper.systemregistrationslaunches.entities.Cidades;
//import br.com.tadeudeveloper.systemregistrationslaunches.util.JPAUtil;

@FacesConverter(forClass = Cidades.class, value = "cidadeConverter")
public class CidadeConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 6320202225365269871L;	

	@Override /* Retorna objeto inteiro */
	public Object getAsObject(FacesContext context, UIComponent component, String codigoCidade) {	
		
		//EntityManager entityManager = JPAUtil.getEntityManager();
		EntityManager entityManager = CDI.current().select(EntityManager.class).get();
		//EntityTransaction entityTransaction = entityManager.getTransaction();
		//entityTransaction.begin();	
		
		Cidades cidade = (Cidades) entityManager.find(Cidades.class, Long.parseLong(codigoCidade));		
		
		return cidade;
	}

	@Override /* Retorna apenas o código em String */
	public String getAsString(FacesContext context, UIComponent component, Object cidade) {		
		
		if(cidade == null) {
			return null;
		}
		
		if (cidade instanceof Cidades) {
			return ((Cidades) cidade).getId().toString();
		} else {
			return cidade.toString();
		}			
	}

}
