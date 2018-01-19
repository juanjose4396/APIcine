package com.orussystem.services.interfaz;

import com.framework.services.GenericServices;
import com.orussystem.response.ResponseControllerPelicula;
import com.orussystem.response.ResponseControllerPeliculas;

@SuppressWarnings("rawtypes")
public interface PeliculasService extends GenericServices{
	public ResponseControllerPeliculas listAll() throws Exception;
	
	public ResponseControllerPelicula getPelicula(Long id) throws Exception;

}
