
package com.framework.repository;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author ing Diego Barrera
 * @param <T>
 * @param <PK>
 */

@Repository
@Transactional
@SuppressWarnings("unchecked")
public abstract class GenericDAOImpl<T, PK extends Serializable> implements GenericDAO<T, PK>, Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(GenericDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void persist(T t) throws Exception {
		sessionFactory.getCurrentSession().persist(t);

	}

	public T findById(Class<T> clazz, PK id) throws Exception {
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	public boolean update(T t) throws Exception {
		sessionFactory.getCurrentSession().update(t);
		return true;
	}

	public boolean delete(T t) throws Exception {
		sessionFactory.getCurrentSession().delete(t);
		return true;

	}

	public T merge(T t) throws Exception {
		T resul = (T) sessionFactory.getCurrentSession().merge(t);
		return resul;
	}

	/**
	 * Maneja la session para los Querys de base de datos
	 * @return La sesion que se utilzia para los querys de base de datos
	 */
	@Transactional
	public Session getSession() throws Exception {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * Metodo que realiza la consulta de los primeros 1000 registros como maximo. Si
	 * desea realizar una consulta de mas registro es conveniente que utilice el
	 * matodo de paginacian.
	 * @author ing Diego Barrera
	 * @param Class <T> ejemplo Entidad.getClass clase de la entidad que se desea consultar.
	 * @return List<T> Retorna listado de entidad consultada.
	 */
	@Transactional
	public List<T> findAll(Class<T> clazz,T t, Integer _limit,Integer _page,String _sort,String _order, String _q) throws Exception {

		LOGGER.info("Obteniendo datos de:" + clazz.getName());
		if (null != clazz) {
			Criteria criteria = this.getSession().createCriteria(clazz);
			//Se realiza la paginacion
			criteria.setFirstResult((_page - 1) * _limit)
					.setMaxResults(_limit);
			
			//Se realiza el Order by
			criteria.addOrder(_order.equalsIgnoreCase("ASC")? Order.asc(_sort):Order.desc(_sort));
			
			//Se realiza el filtro
			criteria.add(Example.create(t).ignoreCase().enableLike(MatchMode.ANYWHERE));
			
			List<T> list_w = criteria.list();
			return list_w;
		}
		
		return null;
	}
	
	/**
	 * Metodo que optiene el maximo de resultados
	 */
	public Long maxFind(Class<T> clazz,T t, String _q) throws Exception{
		LOGGER.info("Obteniendo datos de:" + clazz.getName());
		if (null != clazz) {
			Criteria criteria = this.getSession().createCriteria(clazz);

			//Se realiza el filtro
			criteria.add(Example.create(t).ignoreCase().enableLike(MatchMode.ANYWHERE));
			return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		}
		return null;
	}
	
	/**
	 * Metodo que realiza la consulta de los primeros 1000 registros como maximo. Si
	 * desea realizar una consulta de mas registro es conveniente que utilice el
	 * matodo de paginacian.
	 * @author ing Diego Barrera
	 * @param Class <T> ejemplo Entidad.getClass clase de la entidad que se desea consultar.
	 * @return List<T> Retorna listado de entidad consultada.
	 */
	@Transactional
	public List<T> findAll(Class<T> clazz) throws Exception {

		List<T> listEntity = null;
		LOGGER.info("Obteniendo datos de:" + clazz.getName());
		if (null != clazz) {
			Criteria criteria = this.getSession().createCriteria(clazz);
			criteria.addOrder(Order.asc("id"));
			criteria.setMaxResults(1000);
			listEntity = criteria.list();
		}
		return listEntity;
	}


	@Transactional
	public boolean saveList(List<T> t) throws Exception {
		Session session = getSession();

		int i = 0;
		for (T ts : t) {
			session.save(ts);
			// generar commit
			if (++i % 10 == 0) {
				session.flush();
			}
		}

		session.flush();
		return true;
	}
	
	@Override
	public List<T> findFilter(Class<T> clazz,T t) throws Exception {

		Criteria criteria = this.getSession().createCriteria(clazz);
		//Se realiza el filtro
		criteria.add(Example.create(t).ignoreCase().enableLike(MatchMode.ANYWHERE));
		return criteria.list();
	}
}
