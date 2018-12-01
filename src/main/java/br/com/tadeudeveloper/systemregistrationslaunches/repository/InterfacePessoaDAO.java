package br.com.tadeudeveloper.systemregistrationslaunches.repository;

import br.com.tadeudeveloper.systemregistrationslaunches.entities.People;

public interface InterfacePessoaDAO {
	
	People searchUser(String login, String password);
}
