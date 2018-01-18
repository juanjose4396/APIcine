package com.orussystem.services.interfaz;

import com.framework.services.GenericServices;
import com.orussystem.request.RequestControllerLogin;
import com.orussystem.response.ResponseControllerLogin;

@SuppressWarnings("rawtypes")
public interface LoginService extends GenericServices{
	public ResponseControllerLogin login(RequestControllerLogin requestController) throws Exception;
}
