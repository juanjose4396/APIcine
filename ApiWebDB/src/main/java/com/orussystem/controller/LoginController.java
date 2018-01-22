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

/**
 * Clase que representa el controlador para el servicio de login, mapea e implementa la urls y los metodos que responsen 
 * a dicho servicio, se encarga de ejecutar los services correspondientes
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */

@RestController
@RequestMapping("/Login")
public class LoginController extends GenericController<Usuarios> {

	//Variables de la clase
	private LoginService LoginService;
	private RespuestaServicio respuestaServicio = null;

	/**
	 *Constructor vacio de la clase
	 */
	public LoginController() {}
	
	/** 
	 *Constructor de la clase, se encarga de reemplazar el bean con la implementacion del servicio correspondiente
	 *@param LoginService define el servicio que el controlador va a utilizar 
	 */
	@Autowired
	public LoginController(@Qualifier("LoginService_Impl") LoginService generaricServices) {
		super(generaricServices, Usuarios.class);
		this.LoginService = generaricServices;
	}

	/** 
	 *Metodo que se encarga de recuperar los parametros enviados en el body del request y de ejecutar el 
	 *metodo deseado del servicio, se encarga de llamar al servicio que verifica que el usuario este registrado
	 *en el sistema
	 *@param requestControllerLogin Representa el dto que se encarga de mapear la informacion enviada en el body 
	 *de la peticion POST por el cliente, contiene el email y el password 
	 *@return response correspondiente a la data que se desea mostrar, devuelve un data mostrando un error ocurrido
	 *o informando sobre el exito del login y la informacion del usuario logeado
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseControllerLogin login(@RequestBody RequestControllerLogin requestController) {
		try {
			return LoginService.login(requestController);
		}catch (Exception e) {
			
			//En caso de error se carga el bean del response correspondiente y se d manejo al error
			DataResponseLogin data = (DataResponseLogin)SpringContex.getApplicationContext().getBean(DataResponseLogin.class);
			data.setCodigoRespuesta("error");
			data.setMensaje(e.getMessage());
			
			//Se carga y se retorna el bean de controlador de respuesta para ese servicio asignandole la data
			ResponseControllerLogin responseControllerLogin = (ResponseControllerLogin)SpringContex.getApplicationContext().getBean(ResponseControllerLogin.class);
			responseControllerLogin.setData(data);
			return responseControllerLogin;
		}
	}	
}

