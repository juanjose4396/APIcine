package com.orussystem.repository.impl;

import java.io.Serializable;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.framework.repository.GenericDAOImpl;
import com.orussystem.modelo.Boletas;
import com.orussystem.modelo.Peliculas;
import com.orussystem.repository.dao.BoletasDAO;

@Repository("BoletasDAO_Impl")
@Transactional
public class BoletasDAO_Impl extends GenericDAOImpl<Boletas, Serializable> implements BoletasDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Boletas> findFilter(Boletas t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Boletas> findPelicula(Long id) throws Exception {
		Criteria criteria = getSession().createCriteria(Boletas.class, "b");
		if(!criteria.list().isEmpty()) {
			criteria.createCriteria("pelicula_fk","p").add(Restrictions.eq("p.id",id));
			return criteria.list();
		}
		return criteria.list();
	}

	

	
}
