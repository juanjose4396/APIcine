package com.orussystem.services.interfaz;

import java.util.Date;
import java.util.List;

import com.framework.services.GenericServices;
import com.orussystem.response.ResponseControllerAvailability;
import com.orussystem.response.ResponseControllerLogin;

@SuppressWarnings("rawtypes")
public interface BoletasService extends GenericServices{
	
	public ResponseControllerAvailability getAvailability(Long id,Long numeroBoletas,String tipo) throws Exception;
	public ResponseControllerLogin crearBoletas(Long idUsuario, Long idPelicula,List<Long> silla,String tipo,Date fecha) throws Exception;

}
