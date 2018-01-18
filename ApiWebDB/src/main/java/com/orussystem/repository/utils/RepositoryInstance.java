package com.orussystem.repository.utils;

/**
 * Enum para el control de instancias de DAOs
 * @author ing Diego Barrera
 *
 */
public enum RepositoryInstance {

	UsuariosDAO("UsuariosDAO_Impl");
	
	private String repositorioDAO;

	private RepositoryInstance(String repositorioDAO) {
		this.repositorioDAO = repositorioDAO;
	}

	public String getRepositorioDAO() {
		return repositorioDAO;
	}

}
