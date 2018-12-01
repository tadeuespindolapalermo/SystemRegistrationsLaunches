package br.com.tadeudeveloper.systemregistrationslaunches.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.constraints.br.TituloEleitoral;

@Entity
public class People implements Serializable {	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	@NotEmpty
	@Size(min=5, max=50, message = "Enter the name with a minimum of 5 and a maximum of 50 characters")
	private String name;	
	
	@NotEmpty(message = "Last Name is required")
	@NotNull(message = "Last Name is required")	
	private String lastName;		
	
	@DecimalMax(message = "Age must be less than 50", value="50")
	@DecimalMin(message = "Age must be greater than 10", value="10")
	private Integer age;
	
	@CPF(message = "CPF invalid!")
	private String cpf;
	
	@TituloEleitoral(message = "Electoral Title invalid!")
	private String electoralTitle;
	
	private String sex;		
	private String[] frameworks;
	private Integer[] languages;
	private Boolean active;
	private String login;
	private String password;
	private String profileUser;
	private String programmerLevel;
	
	@Temporal(TemporalType.DATE)
	private Date dateBirth;		
	
	public People() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setFrameworks(String[] frameworks) {
		this.frameworks = frameworks;
	}
	
	public String[] getFrameworks() {
		return frameworks;
	}		

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setProfileUser(String profileUser) {
		this.profileUser = profileUser;
	}
	
	public String getProfileUser() {
		return profileUser;
	}
	
	public void setProgrammerLevel(String programmerLevel) {
		this.programmerLevel = programmerLevel;
	}
	
	public String getProgrammerLevel() {
		return programmerLevel;
	}
	
	public void setLanguages(Integer[] languages) {
		this.languages = languages;
	}
	
	public Integer[] getLanguages() {
		return languages;
	}	

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getElectoralTitle() {
		return electoralTitle;
	}

	public void setElectoralTitle(String electoralTitle) {
		this.electoralTitle = electoralTitle;
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
		People other = (People) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}
