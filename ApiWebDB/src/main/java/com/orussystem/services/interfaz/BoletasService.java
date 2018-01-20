package com.orussystem.services.interfaz;

import com.framework.services.GenericServices;
import com.orussystem.response.ResponseControllerAvailability;

@SuppressWarnings("rawtypes")
public interface BoletasService extends GenericServices{
	
	public ResponseControllerAvailability getAvailability(Long id,Long numeroBoletas) throws Exception;

}
