package com.orussystem.repository.dao;

import java.io.Serializable;
import java.util.List;

import com.framework.repository.GenericDAO;
import com.orussystem.modelo.Boletas;

public interface BoletasDAO extends GenericDAO<Boletas, Serializable> {
	public List<Boletas> findPelicula(Long id) throws Exception;
}
