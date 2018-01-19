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

@Service("LoginService_Impl")
public class LoginService_Impl extends GenericServicesImpl implements LoginService{
	
	private UsuariosDAO UsuariosDAO;
	
	public LoginService_Impl() {}
	
	@Autowired
	public LoginService_Impl( @Qualifier("UsuariosDAO_Impl") UsuariosDAO genericDAO) {
		super(genericDAO);
		this.UsuariosDAO = (UsuariosDAO) genericDAO;
	}
	
	@Override
	@Transactional
	public ResponseControllerLogin login(RequestControllerLogin requestController) throws Exception {
		//se obtienen los datos del request
		String email = requestController.getData().getEmail();
		String password = requestController.getData().getPassword();
		
		List<Usuarios> usuarios = UsuariosDAO.findEmailPassword(email, password);
		
		if(usuarios.isEmpty()) {
			throw new Exception("El usuario no existe");
		}
		
		DataResponseLogin data = (DataResponseLogin)SpringContex.getApplicationContext().getBean(DataResponseLogin.class);
		data.setCodigoRespuesta("ok");
		data.setMensaje("Login exitoso");
		data.setUsuario(usuarios.get(0));
		
		
		ResponseControllerLogin responseControllerLogin = (ResponseControllerLogin)SpringContex.getApplicationContext().getBean(ResponseControllerLogin.class);
		responseControllerLogin.setData(data);
		
		return responseControllerLogin;
	}
}
