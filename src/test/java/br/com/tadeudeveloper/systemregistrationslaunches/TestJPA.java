package br.com.tadeudeveloper.systemregistrationslaunches;

import javax.persistence.Persistence;

public class TestJPA {

	public static void main(String[] args) {		
		Persistence.createEntityManagerFactory("SystemRegistrationsLaunches");	
	}
	
}
