package com.orussystem.services.interfaz;

import com.framework.services.GenericServices;
import com.orussystem.response.ResponseControllerPelicula;
import com.orussystem.response.ResponseControllerPeliculas;
import com.orussystem.response.ResponseControllerSillas;

/**
 * Interfaz que expone los metodos a implementar por el servicio correspondiente
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */
@SuppressWarnings("rawtypes")
public interface PeliculasService extends GenericServices{
	public ResponseControllerPeliculas listAll() throws Exception;
	
	public ResponseControllerPelicula getPelicula(Long id) throws Exception;
	
	public ResponseControllerSillas getSillas(Long id, String tipo) throws Exception;
	
}
