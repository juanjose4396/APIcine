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

@Repository("UsuariosDAO_Impl")
@Transactional
public class UsuariosDAO_Impl extends GenericDAOImpl<Usuarios, Serializable> implements UsuariosDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Usuarios> findFilter(Usuarios t) throws Exception {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuarios> findEmailPassword(String email, String password) throws Exception {
		Criteria criteria = getSession().createCriteria(Usuarios.class, "u");
		if (email != null && !password.isEmpty()) {
			criteria.add(Restrictions.and(Restrictions.eq("u.password", password),
					Restrictions.eq("u.email", email)));
		}
		
		return criteria.list();
	}

	
}
