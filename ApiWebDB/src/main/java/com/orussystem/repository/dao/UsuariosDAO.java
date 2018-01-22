package com.orussystem.repository.dao;

import java.io.Serializable;
import java.util.List;

import com.framework.repository.GenericDAO;
import com.orussystem.modelo.Usuarios;

/**
 * Interfaz que expone los metodos a implementar por el DAO de Usuarios
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */
public interface UsuariosDAO extends GenericDAO<Usuarios, Serializable> {
	/**
	 * 
	 * @param email representa el email del usuario a buscar
	 * @param password representa la password del usuario a buscar
	 * @return retorna una lista vacia o con los usuarios que contiene el email y contraseña especificada
	 * @throws Exception
	 */
	public List<Usuarios> findEmailPassword(String email,String password) throws Exception;
	
}
