package com.orussystem.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.framework.contex.SpringContex;
import com.orussystem.services.interfaz.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.framework.services.GenericServicesImpl;
import com.orussystem.dto.DataResponseLogin;
import com.orussystem.modelo.Usuarios;
import com.orussystem.repository.dao.UsuariosDAO;
import com.orussystem.request.RequestControllerLogin;
import com.orussystem.response.ResponseControllerLogin;

/**
 * Implementacion de la interfaz correspondiente al servicio de login, representa el servicio a utlizar por un controlador
 * y contiene la logica de los metodos que dicho servicio maneja
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */
@Service("LoginService_Impl")
public class LoginService_Impl extends GenericServicesImpl implements LoginService{
	
	private UsuariosDAO UsuariosDAO;
	/**
	 * Constructor vacio de la clase
	 */
	public LoginService_Impl() {}
	
	/**
	 * Constructor que carga el bean del dao a utilizar
	 * @param genericDAO DAO a utilizar
	 */
	@Autowired
	public LoginService_Impl( @Qualifier("UsuariosDAO_Impl") UsuariosDAO genericDAO) {
		super(genericDAO);
		this.UsuariosDAO = (UsuariosDAO) genericDAO;
	}
	/**
	 * Metodo encargado de hacer el login
	 */
	@Override
	@Transactional
	public ResponseControllerLogin login(RequestControllerLogin requestController) throws Exception {
		//se obtienen los datos del request
		String email = requestController.getData().getEmail();
		String password = requestController.getData().getPassword();
		
		//Se verifica si el usuario existe en la DB
		List<Usuarios> usuarios = UsuariosDAO.findEmailPassword(email, password);
		
		//Se response en base a si la lista tiene registros o no
		if(usuarios.isEmpty()) {
			throw new Exception("El usuario no existe");
		}
		
		//Se carga el bean del response y la data
		DataResponseLogin data = (DataResponseLogin)SpringContex.getApplicationContext().getBean(DataResponseLogin.class);
		data.setCodigoRespuesta("ok");
		data.setMensaje("Login exitoso");
		data.setUsuario(usuarios.get(0));
		
		
		ResponseControllerLogin responseControllerLogin = (ResponseControllerLogin)SpringContex.getApplicationContext().getBean(ResponseControllerLogin.class);
		responseControllerLogin.setData(data);
		
		return responseControllerLogin;
	}
}
