package br.com.tadeudeveloper.systemregistrationslaunches.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.tadeudeveloper.systemregistrationslaunches.entities.Estados;
import br.com.tadeudeveloper.systemregistrationslaunches.util.JPAUtil;

@FacesConverter(forClass = Estados.class, value = "estadoConverter")
public class EstadoConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 6320202225365269871L;
	
	@Override /* Retorna objeto inteiro */
	public Object getAsObject(FacesContext context, UIComponent component, String codigoEstado) {	
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();			
		
		Estados estado = (Estados) entityManager.find(Estados.class, Long.parseLong(codigoEstado));		
		
		return estado;
	}

	@Override /* Retorna apenas o código em String */
	public String getAsString(FacesContext context, UIComponent component, Object estado) {	
		
		if(estado == null) {
			return null;
		}
		
		if (estado instanceof Estados) {
			return ((Estados) estado).getId().toString();
		} else {
			return estado.toString();
		}		
	}

}
