package br.com.tadeudeveloper.systemregistrationslaunches.repository;

import java.util.List;

import br.com.tadeudeveloper.systemregistrationslaunches.entities.Release;

public interface InterfaceReleaseDAO {
	
	List<Release> search(Long idUser);

}
