package br.com.tadeudeveloper.systemregistrationslaunches.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
	
	//Address
	private String cep;	
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String unidade;
	private String ibge;
	private String gia;
	
	private String sex;		
	private String[] frameworks;
	private Integer[] languages;
	private Boolean active;
	private String login;
	private String password;
	private String profileUser;
	private String programmerLevel;
	
	@Transient
	private Estados estados;
	
	/*Tipo text grava arquivos em base 64*/
	@Column(columnDefinition = "text")
	private String fotoIconBase64;
	
	/*Extensões de arquivo*/
	private String extensao;
	
	@Lob /*Gravar arquivos no banco de dados*/
	@Basic(fetch = FetchType.LAZY)
	private byte[] fotoIconBase64Original;
	
	@ManyToOne
	private Cidades cidades;

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
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}	
	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}
	
	public void setEstados(Estados estados) {
		this.estados = estados;
	}
	
	public Estados getEstados() {
		return estados;
	}
	
	public void setCidades(Cidades cidades) {
		this.cidades = cidades;
	}
	
	public Cidades getCidades() {
		return cidades;
	}	

	public String getFotoIconBase64() {
		return fotoIconBase64;
	}

	public void setFotoIconBase64(String fotoIconBase64) {
		this.fotoIconBase64 = fotoIconBase64;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public byte[] getFotoIconBase64Original() {
		return fotoIconBase64Original;
	}

	public void setFotoIconBase64Original(byte[] fotoIconBase64Original) {
		this.fotoIconBase64Original = fotoIconBase64Original;
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
