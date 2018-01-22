package com.orussystem.repository.impl;

import java.io.Serializable;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.framework.repository.GenericDAOImpl;
import com.orussystem.modelo.Usuarios;
import com.orussystem.repository.dao.UsuariosDAO;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * Clase que representa el DAO del modelo Usuarios, implementa la interfaz correspondiente y los metodos
 * especificos de esta que extraen la informacion de la base de datos segun se requiere
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */

@Repository("UsuariosDAO_Impl")
@Transactional
public class UsuariosDAO_Impl extends GenericDAOImpl<Usuarios, Serializable> implements UsuariosDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Usuarios> findFilter(Usuarios t) throws Exception {
		return null;
	}

	/**
	 * Se encarga de generar la criteria para ejecutar la consulta deseada a la base de datos
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuarios> findEmailPassword(String email, String password) throws Exception {
		Criteria criteria = getSession().createCriteria(Usuarios.class, "u");
		if (email != null && !password.isEmpty()) {
			criteria.add(Restrictions.and(Restrictions.eq("u.password", password),
					Restrictions.eq("u.email", email)))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		}
		
		return criteria.list();
	}

	
}
