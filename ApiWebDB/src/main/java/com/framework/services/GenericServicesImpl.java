package com.framework.services;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.framework.dto.RespuestaServicio;
import com.framework.framework.util.CondigosRespuesta;
import com.framework.repository.GenericDAO;

/**
 * Generic Services para la implementacion de servicios generic
 * @author ing Diego Barrera Sotelo
 * @param <T>
 * @param <PK>
 */
@Service
public abstract class GenericServicesImpl<T, PK extends Serializable> implements GenericServices<T, PK> {

	/**
	 * Inject Generic DAO
	 */
	private GenericDAO<T, Serializable> genericDAO;

	// Manejador de Logger
	public static final Logger LOGGER = Logger.getLogger(GenericServicesImpl.class);

	public GenericServicesImpl() {}

	/**
	 * Constructor de Generic Services
	 * @param genericDAO
	 */
	public GenericServicesImpl(GenericDAO<T, Serializable> genericDAO) {
		this.genericDAO = genericDAO;
	}

	/**
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public RespuestaServicio guardar(T t) throws Exception {
		this.genericDAO.persist(t);
		return new RespuestaServicio("", CondigosRespuesta.EXITOSO, true);
	}

	/**
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public RespuestaServicio buscarPorId(Class<T> clazz, Serializable id) throws Exception {
		T t = this.genericDAO.findById(clazz, id);
		return new RespuestaServicio("", CondigosRespuesta.EXITOSO, t);
	}

	@Override
	public RespuestaServicio actualizar(T t) throws Exception {
		this.genericDAO.update(t);
		return new RespuestaServicio("", CondigosRespuesta.EXITOSO, true);
	}

	@Override
	public RespuestaServicio eliminar(T t) throws Exception {
		this.genericDAO.delete(t);
		return new RespuestaServicio("", CondigosRespuesta.EXITOSO, true);
	}

	@Override
	public RespuestaServicio merge(T t) throws Exception {
		T e = this.genericDAO.merge(t);
		return new RespuestaServicio("", CondigosRespuesta.EXITOSO, e);
	}

	@Override
	@Transactional
	public RespuestaServicio listarTodo(Class<T> clazz, T t,Integer _limit, Integer _page,String _sort,String _order, String _q) throws Exception {
		Long maxResult = this.genericDAO.maxFind(clazz, t, _q);
		List<T> l = null;
		if(maxResult > 0){
			l = this.genericDAO.findAll(clazz, t,_limit, _page, _sort, _order,  _q);
		}
		
		return new RespuestaServicio(maxResult+"", CondigosRespuesta.EXITOSO, l);
	}


	@Override
	public RespuestaServicio buscarFiltro(T t) throws Exception {
		List<T> l = this.genericDAO.findFilter(t);
		return new RespuestaServicio("", CondigosRespuesta.EXITOSO, l);
	}

}
