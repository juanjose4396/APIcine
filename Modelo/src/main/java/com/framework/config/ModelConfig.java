package com.framework.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.client.RestTemplate;

//import com.orussystem.cliente.ClienteMonederoPreautorizador;

@Configurable
@PropertySources({
@PropertySource("classpath:/properties/config.properties"),
@PropertySource("file:${file.path.properties}") })
public class ModelConfig {

	@Value("${url.api.db}")
	private String url;
	
	@Bean("restTemplate")
	public RestTemplate RestTemplate(){
		return new RestTemplate();
	}
/**
	@Bean("clienteMonederoPreautorizador")
	public ClienteMonederoPreautorizador ClienteAPIDB(){
		return new ClienteMonederoPreautorizador(RestTemplate(),url);
	}**/
	
}
