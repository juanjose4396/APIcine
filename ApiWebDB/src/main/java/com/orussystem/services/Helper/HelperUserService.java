package com.orussystem.services.Helper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.framework.repository.factory.RepositoryFactory;
import com.orussystem.helper.HelperAbstract;
//import com.orussystem.modelo.Usuarios;
import com.orussystem.repository.utils.RepositoryInstance;

@Component
public class HelperUserService extends HelperAbstract {
	/**
	 * Metodo para validar si el usuario existe
	 * 
	 * @param usuarios
	 * @return
	 * @throws Exception
	 */
/**
	
	public Usuarios getUser(Usuarios usuario) throws Exception {

		UsuariosDAO UsuariosDAO = (UsuariosDAO) RepositoryFactory.getFactory().get(RepositoryInstance.UsuariosDAO);

		List<Usuarios> usuarios = UsuariosDAO.findFilter(Usuarios.class, usuario);
		if (usuarios.isEmpty()) {
			throw new Exception("Error: El usuario no esta registrado");
		}

		return usuarios.get(0);
	}
	**/

}
