package br.com.tadeudeveloper.systemregistrationslaunches.repository;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.tadeudeveloper.systemregistrationslaunches.entities.People;

public interface InterfacePessoaDAO {
	
	People searchUser(String login, String password);
	
	List<SelectItem> listarEstados();
}
