package br.com.tadeudeveloper.systemregistrationslaunches.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.faces.bean.ViewScoped;
//import javax.faces.bean.RequestScoped;
//import javax.faces.bean.NoneScoped;

//@RequestScoped // default
@ManagedBean(name = "peopleBean")
//@NoneScoped
//@ViewScoped 
//@SessionScoped
@ApplicationScoped
public class PeopleBean {
	
	private String name;
	private String lastName;
	private String fullName;
	
	private List<String> names = new ArrayList<>();
	
	public String addName() {
		names.add(name);
		return "";
	}
	
	public String removeAllNames() {
		names.removeAll(names);		
		return "";
	}
	
	public String removeLastName() {			
		if (names.size() > 0) {
			names.remove(names.size() - 1);
		} 
		return "";
	}
	
	public String clearFields() {
		name = "";
		lastName = "";
		return "";
	}
	
	public List<String> getNames() {
		return names;
	}
	
	public void setNames(List<String> names) {
		this.names = names;
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
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
}
