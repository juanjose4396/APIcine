package com.orussystem.services.interfaz;

import java.util.List;

import com.framework.services.GenericServices;
import com.orussystem.modelo.Peliculas;
import com.orussystem.request.RequestControllerLogin;
import com.orussystem.response.ResponseControllerLogin;
import com.orussystem.response.ResponseControllerPeliculas;

@SuppressWarnings("rawtypes")
public interface PeliculasService extends GenericServices{
	public ResponseControllerPeliculas listAll() throws Exception;

}
