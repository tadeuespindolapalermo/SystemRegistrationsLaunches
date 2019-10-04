package br.com.tadeudeveloper.systemregistrationslaunches.bean;

import java.util.ArrayList;
import java.util.List;

//import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlCommandButton;
//import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
//import javax.faces.bean.RequestScoped;
//import javax.faces.bean.NoneScoped;

//@RequestScoped // default
@ManagedBean(name = "peopleBean")
//@NoneScoped
@ViewScoped 
//@SessionScoped
//@ApplicationScoped
public class PeopleBean {
	
	private String name;
	private String nameTwo;
	private String lastName;
	private String fullName;	
	private HtmlCommandButton commandButton;	
	private String password;
	private String text;
	
	private List<String> names = new ArrayList<>();
	
	public String addName() throws InterruptedException {
		if (!name.isEmpty()) {
			names.add(name);
		}		
		if (names.size() == 5) {
			commandButton.setDisabled(true);			
			return "navigated-page?faces-redirect=true";
		}		
		return "";
	}
	
	public String removeAllNames() {
		names.removeAll(names);	
		commandButton.setDisabled(false);
		return "";
	}
	
	public String removeLastName() {			
		if (names.size() > 0) {
			names.remove(names.size() - 1);		
			if (names.size() < 5) {
				commandButton.setDisabled(false);
			}
		} 		
		return "";
	}
	
	public String clearFields() {
		name = "";
		lastName = "";
		return "";
	}
	
	public HtmlCommandButton getCommandButton() {
		return commandButton;
	}
	
	public void setCommandButton(HtmlCommandButton commandButton) {
		this.commandButton = commandButton;
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
	
	public String getNameTwo() {
		return nameTwo;
	}
	
	public void setNameTwo(String nameTwo) {
		this.nameTwo = nameTwo;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}	
	
}
