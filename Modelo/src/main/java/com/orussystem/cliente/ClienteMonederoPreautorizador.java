package com.orussystem.cliente;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.framework.client.ClienteAPIDB;
//import com.orussystem.cliente.facade.LoginCliente;
import com.orussystem.factory.FacadeFactory;

/**
 * Cliente para consumir los servicios Handler
 * @author ing Diego Barrera
 *
 */
/**
@Component
public class ClienteMonederoPreautorizador extends ClienteAPIDB {

	public ClienteMonederoPreautorizador() {}
	
	public ClienteMonederoPreautorizador(RestTemplate restTemplate, String url) {
		super(restTemplate, url);
	}

	public LoginCliente getLoginCliente() throws Exception{
		LoginCliente cliente=(LoginCliente) FacadeFactory.getFactory().getServices(LoginCliente.class);
		cliente.setGenericFacade(restTemplate, url);
		return cliente;
	}
}**/
