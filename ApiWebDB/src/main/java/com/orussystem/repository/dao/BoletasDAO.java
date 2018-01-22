package com.orussystem.repository.dao;

import java.io.Serializable;
import java.util.List;
import com.framework.repository.GenericDAO;
import com.orussystem.modelo.Boletas;

/**
 * Interfaz que expone los metodos a implementar por el DAO de boletas
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */
public interface BoletasDAO extends GenericDAO<Boletas, Serializable> {
	/**
	 * Metodo que expone la funcionalidad para obtener las boletas de la base de datos en base a
	 * una pelicula y un tipo de funcion
	 * @param id representa el identificador de la pelicula
	 * @param tipo representa el tipo de funcion (2D o 3D)
	 * @return retorna las boletas correspondientes a la pelicula y tipo de funcion especificadas
	 * @throws Exception
	 */
	public List<Boletas> findPelicula(Long id,String tipo) throws Exception;
	/**
	 * Metodo que expone la funcionalidad para obtener las boletas por una pelicula deseada
	 * @param id representa el identificador de la pelicula
	 * @return retorna una lista de boletas correspondientes a la pelicula especificada
	 * @throws Exception
	 */
	public List<Boletas> findIdPelicula(Long id) throws Exception;
}
