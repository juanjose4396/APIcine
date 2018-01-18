package com.framework.services;

import java.io.Serializable;

import com.framework.dto.RespuestaServicio;

/**
 * Interfaz de los servicios genericos
 * @author ing Diego Barrera Sotelo
 * @param <T>
 * @param <PK>
 */
public interface GenericServices <T, PK extends Serializable> {

	public RespuestaServicio guardar(T t) throws Exception;
	
	public RespuestaServicio buscarPorId( Class<T> clazz, Serializable id) throws Exception;
	
	public RespuestaServicio actualizar(T t) throws Exception;

	public RespuestaServicio eliminar(T t) throws Exception;

	public RespuestaServicio merge(T t) throws Exception;

	public RespuestaServicio listarTodo(Class<T> clazz, T t,Integer _limit,Integer _page,String _sort,String _order, String _q) throws Exception;

	public RespuestaServicio buscarFiltro(T t) throws Exception;

}
