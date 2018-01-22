package com.orussystem.services.interfaz;

import com.framework.services.GenericServices;
import com.orussystem.request.RequestControllerLogin;
import com.orussystem.response.ResponseControllerLogin;

/**
 * Interfaz que expone los metodos a implementar por el servicio correspondiente
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */
@SuppressWarnings("rawtypes")
public interface LoginService extends GenericServices{
	public ResponseControllerLogin login(RequestControllerLogin requestController) throws Exception;
}
