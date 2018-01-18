package com.framework.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

/**
 * Interfaz generica que expone los metodos basicos para el consumo de la base de datos
 * @author ing Diego Barrera
 * @param <T>
 * @param <PK>
 */
public interface GenericDAO<T, PK extends Serializable> {

	public void persist(T t) throws Exception;

	public T findById(Class<T> clazz, PK id) throws Exception;

	public boolean update(T t) throws Exception;

	public boolean delete(T t) throws Exception;

	public T merge(T t) throws Exception;

	public Session getSession() throws Exception;

	public List<T> findAll(Class<T> clazz) throws Exception;

	public List<T> findFilter(Class<T> clazz,T t) throws Exception;
	
	public List<T> findFilter(T t) throws Exception;
	
	public List<T> findAll(Class<T> clazz,T t, Integer _limit,Integer _page,String _sort,String _order, String _q) throws Exception;

	public boolean saveList(List<T> t) throws Exception;
	
	public Long maxFind(Class<T> clazz,T t, String _q) throws Exception;

}
