package com.orussystem.repository.impl;

import java.io.Serializable;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.framework.repository.GenericDAOImpl;
import com.orussystem.modelo.Peliculas;
import com.orussystem.modelo.Sillas;
import com.orussystem.repository.dao.SillasDAO;

@Repository("SillasDAO_Impl")
@Transactional
public class SillasDAO_Impl extends GenericDAOImpl<Sillas, Serializable> implements SillasDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Sillas> findFilter(Sillas t) throws Exception {
		Criteria criteria = getSession().createCriteria(Sillas.class, "s").addOrder(Order.asc("ubicacion"));
		return criteria.list();
	}

	
	
}
