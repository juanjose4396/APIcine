package com.orussystem.cliente.facade;

import org.springframework.web.client.RestTemplate;

import com.framework.client.facade.GenericFacade;
//import com.orussystem.modelo.Login;
/**
public class LoginCliente extends GenericFacade<Login> {


	private static String SERVICE = "Login";
	
	private Class<Login> clazzTem = Login.class;

	@Override
	public void setGenericFacade(RestTemplate restTemplate, String urlHostApiDB) {
		this.restTemplate = restTemplate;
		this.urlHostApiDB = urlHostApiDB;
		this.service = SERVICE;
		this.clazz = clazzTem;
	}
	
}
**/