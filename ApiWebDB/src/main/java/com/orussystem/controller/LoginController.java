package com.orussystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.framework.contex.SpringContex;
import com.framework.controller.GenericController;
import com.framework.dto.RespuestaServicio;
import com.orussystem.dto.DataResponseLogin;
import com.orussystem.modelo.Usuarios;
import com.orussystem.request.RequestControllerLogin;
import com.orussystem.response.ResponseControllerLogin;
import com.orussystem.services.interfaz.LoginService;

@RestController
@RequestMapping("/Login")
public class LoginController extends GenericController<Usuarios> {

	private LoginService LoginService;

	// Respuesta generica de los servicios
	private RespuestaServicio respuestaServicio = null;

	public LoginController() {}
	
	@Autowired
	public LoginController(@Qualifier("LoginService_Impl") LoginService generaricServices) {
		super(generaricServices, Usuarios.class);
		this.LoginService = generaricServices;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseControllerLogin login(@RequestBody RequestControllerLogin requestController) {
		try {
			return LoginService.login(requestController);
		}catch (Exception e) {
			
			DataResponseLogin data = (DataResponseLogin)SpringContex.getApplicationContext().getBean(DataResponseLogin.class);
			data.setCodigoRespuesta("error");
			data.setMensaje(e.getMessage());
			
			ResponseControllerLogin responseControllerLogin = (ResponseControllerLogin)SpringContex.getApplicationContext().getBean(ResponseControllerLogin.class);
			responseControllerLogin.setData(data);
			return responseControllerLogin;
		}
	}	
}

