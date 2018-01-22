package com.orussystem.repository.impl;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.framework.repository.GenericDAOImpl;
import com.orussystem.modelo.Boletas;
import com.orussystem.repository.dao.BoletasDAO;

/**
 * Clase que representa el DAO del modelo Boletas, implementa la interfaz correspondiente y los metodos
 * especificos de esta que extraen la informacion de la base de datos segun se requiere
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */

@Repository("BoletasDAO_Impl")
@Transactional
public class BoletasDAO_Impl extends GenericDAOImpl<Boletas, Serializable> implements BoletasDAO {

	private static final long serialVersionUID = 1L;
	/**
	 * Metodo generico de los DAOS
	 */
	@Override
	public List<Boletas> findFilter(Boletas t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Se encarga de generar la criteria para ejecutar la consulta deseada a la base de datos
	 */
	@Override
	public List<Boletas> findPelicula(Long id,String tipo) throws Exception {
		Criteria criteria = getSession().createCriteria(Boletas.class, "b");
		if(!criteria.list().isEmpty()) {
			criteria.createCriteria("pelicula_fk","p").add(Restrictions.eq("p.id",id))
			.add(Restrictions.eq("b.tipo", tipo));
			//criteria.add(Restrictions.eq("tipo", tipo));
			return criteria.list();
		}
		
		return criteria.list();
	}

	/**
	 * Se encarga de generar la criteria para ejecutar la consulta deseada a la base de datos
	 */
	@Override
	public List<Boletas> findIdPelicula(Long id) throws Exception {
		Criteria criteria = getSession().createCriteria(Boletas.class, "b");
		if(!criteria.list().isEmpty()) {
			criteria.createCriteria("pelicula_fk","p").add(Restrictions.eq("p.id",id))
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return criteria.list();
		}
		
		return criteria.list();
	}
	
}
