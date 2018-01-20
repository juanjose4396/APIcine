package com.orussystem.services.interfaz;

import java.util.List;

import com.framework.services.GenericServices;
import com.orussystem.response.ResponseControllerAvailability;
import com.orussystem.response.ResponseControllerLogin;

@SuppressWarnings("rawtypes")
public interface BoletasService extends GenericServices{
	
	public ResponseControllerAvailability getAvailability(Long id,Long numeroBoletas) throws Exception;
	public ResponseControllerLogin crearBoletas(Long idUsuario, Long idPelicula,List<Long> silla) throws Exception;

}
