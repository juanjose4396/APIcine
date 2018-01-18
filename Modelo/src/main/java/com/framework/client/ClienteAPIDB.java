package com.framework.client;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.framework.dto.RespuestaServicio;

/**
 * Cliente Generic para consumir los servicios de la API DB 
 * @author ing Diego Barrera
 *
 */
public abstract class ClienteAPIDB {

	protected RestTemplate restTemplate;
	
	protected Logger LOGGER = Logger.getLogger(ClienteAPIDB.class);
	
	protected String url;

	public ClienteAPIDB() {}
	
	public ClienteAPIDB(RestTemplate restTemplate, String url) {
		this.restTemplate=restTemplate;
		this.url =url;
	}
	
	/**
	 * Metodo generico para el consumo del API DB
	 * @param services url del servicio @ejemplo Tabla/operacion
	 * @param param parametros que se deben consumir.
	 * @return
	 */
	public RespuestaServicio getServices(String services, Object param) {
		String urlServices = url + "/" + services;
		LOGGER.info("Inicia consumo: " + urlServices);
		return restTemplate.postForObject(urlServices, param, RespuestaServicio.class);
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
