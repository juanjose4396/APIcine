package com.orussystem.repository.dao;

import java.io.Serializable;
import java.util.List;

import com.framework.repository.GenericDAO;
import com.orussystem.modelo.Usuarios;

public interface UsuariosDAO extends GenericDAO<Usuarios, Serializable> {
	
	public List<Usuarios> findEmailPassword(String email,String password) throws Exception;
	
}
