package com.orussystem.repository.impl;

import java.io.Serializable;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.framework.repository.GenericDAOImpl;
import com.orussystem.modelo.Peliculas;
import com.orussystem.repository.dao.PeliculasDAO;

/**
 * Clase que representa el DAO del modelo Peliculas, implementa la interfaz correspondiente y los metodos
 * especificos de esta que extraen la informacion de la base de datos segun se requiere
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */
@Repository("PeliculasDAO_Impl")
@Transactional
public class PeliculasDAO_Impl extends GenericDAOImpl<Peliculas, Serializable> implements PeliculasDAO {

	private static final long serialVersionUID = 1L;

	/**
	 * Se encarga de generar la criteria para ejecutar la consulta deseada a la base de datos
	 */
	@Override
	public List<Peliculas> findFilter(Peliculas t) throws Exception {
		Criteria criteria = getSession().createCriteria(Peliculas.class, "p")
				.addOrder(Order.asc("id"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	
}
