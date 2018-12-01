package br.com.tadeudeveloper.systemregistrationslaunches.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Release implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String invoiceNumber;
	private String companyOrigin;
	private String destinationCompany;
	
	@SuppressWarnings("deprecation")
	@ManyToOne(optional = false)
	@org.hibernate.annotations.ForeignKey(name = "user_fk")
	private People user;	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getCompanyOrigin() {
		return companyOrigin;
	}

	public void setCompanyOrigin(String companyOrigin) {
		this.companyOrigin = companyOrigin;
	}

	public String getDestinationCompany() {
		return destinationCompany;
	}

	public void setDestinationCompany(String destinationCompany) {
		this.destinationCompany = destinationCompany;
	}

	public People getUser() {
		return user;
	}

	public void setUser(People user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Release other = (Release) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
