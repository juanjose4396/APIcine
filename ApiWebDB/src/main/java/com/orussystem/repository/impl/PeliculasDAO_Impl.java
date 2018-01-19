package com.orussystem.repository.impl;

import java.io.Serializable;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.framework.repository.GenericDAOImpl;
import com.orussystem.modelo.Peliculas;
import com.orussystem.modelo.Usuarios;
import com.orussystem.repository.dao.PeliculasDAO;

@Repository("PeliculasDAO_Impl")
@Transactional
public class PeliculasDAO_Impl extends GenericDAOImpl<Peliculas, Serializable> implements PeliculasDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Peliculas> findFilter(Peliculas t) throws Exception {
		Criteria criteria = getSession().createCriteria(Peliculas.class, "p").addOrder(Order.asc("id"));
		return criteria.list();
	}

	
}
