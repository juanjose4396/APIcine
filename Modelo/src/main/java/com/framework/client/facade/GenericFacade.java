package com.framework.client.facade;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.dto.RespuestaServicio;
import com.framework.framework.util.CondigosRespuesta;

/**
 * Servicio abstract con lo metodos genericos para el consumo del API DB
 * @author ing Diego Barrera
 * @param <T>
 *
 */

public abstract class GenericFacade<T> {
	
	protected RestTemplate restTemplate;
	
	public Logger LOGGER = Logger.getLogger(GenericFacade.class);
	
	protected String urlHostApiDB;
	
	protected String service;
	
	protected Class<T> clazz;

	public GenericFacade(){}

	public abstract void setGenericFacade(RestTemplate restTemplate, String urlHostApiDB);

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	/**
	 * Metodo para el consumo Rest del API DB
	 * @param services
	 * @param param
	 * @return
	 */
	protected RespuestaServicio getPOST(String services, T param) throws Exception {
		try {
			
			String url = this.urlHostApiDB + this.service + "/" + services+".json";
			
			LOGGER.debug("Inicia consumo URL: " + url);
			
			MultiValueMap<String, Object> headers = new LinkedMultiValueMap();
			
	        headers.add("Accept", "application/json");
	        
	        headers.add("Content-Type", "application/json");
	        
	        HttpEntity<?> request = new HttpEntity(param, headers);
	        
			RespuestaServicio respuestaServicio = restTemplate.postForObject(url, request, RespuestaServicio.class);
			
			String ms = respuestaServicio.getMensaje() != null ? respuestaServicio.getMensaje() : "--";
			
			LOGGER.debug("Respuesta: [" + respuestaServicio.getCodigoRespuesta() + "] Mensaje: [" + ms + "]");
			
			return respuestaServicio;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw e;
		}
	}
	
	/**
	 * Metodo para el consumo Rest del API DB
	 * @param services
	 * @param param
	 * @return
	 */
	protected RespuestaServicio getGET(String services, T param) throws Exception {
		
		try {
			
			String url = this.urlHostApiDB + this.service + "/" + services+".json";
			
			LOGGER.debug("Inicia consumo URL: " + url);
			
			MultiValueMap<String, Object> headers = new LinkedMultiValueMap();
			
	        headers.add("Accept", "application/json");
	        
	        headers.add("Content-Type", "application/json");
	        
	        HttpEntity<?> request = new HttpEntity(param, headers);
	        
			RespuestaServicio respuestaServicio = restTemplate.getForObject(url, RespuestaServicio.class,request);
			
			String ms = respuestaServicio.getMensaje() != null ? respuestaServicio.getMensaje() : "--";
			
			LOGGER.debug("Respuesta: [" + respuestaServicio.getCodigoRespuesta() + "] Mensaje: [" + ms + "]");
			
			return respuestaServicio;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw e;
		}
	}
	
	/**
	 * Metodo para el consumo Rest del API DB
	 * @param services
	 * @param param
	 * @return
	 */
	protected RespuestaServicio getObject(String services, Object param) throws Exception {
		
		try {
			
			String url = this.urlHostApiDB + this.service + "/" + services+".json";
			
			LOGGER.debug("Inicia consumo URL: " + url);
			
			MultiValueMap<String, Object> headers = new LinkedMultiValueMap();
			
	        headers.add("Accept", "application/json");
	        
	        headers.add("Content-Type", "application/json");
	        
	        HttpEntity<?> request = new HttpEntity(param, headers);
	        
			RespuestaServicio respuestaServicio = restTemplate.postForObject(url, request, RespuestaServicio.class);
			
			String ms = respuestaServicio.getMensaje() != null ? respuestaServicio.getMensaje() : "--";
			
			LOGGER.debug("Respuesta: [" + respuestaServicio.getCodigoRespuesta() + "] Mensaje: [" + ms + "]");
			
			return respuestaServicio;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw e;
		}
	
	}
	
	/**
	 * Metodo para el consumo Rest del API DB
	 * @param services
	 * @param param
	 * @return
	 */
	protected RespuestaServicio getGeneric(String services, Object param, Class<?> clazz) throws Exception {
		
		String url = this.urlHostApiDB + "/" + services;
		
		LOGGER.debug("Inicia consumo URL: " + url);
		
		RespuestaServicio respuestaServicio = restTemplate.postForObject(url, param, RespuestaServicio.class);
		
		String ms = respuestaServicio.getMensaje() != null ? respuestaServicio.getMensaje() : "--";
		
		LOGGER.debug("Respuesta: [" + respuestaServicio.getCodigoRespuesta() + "] Mensaje: [" + ms + "]");
		
		return respuestaServicio;
	}

	
	/**
	 * Metodo que validad la respuesta del servicio
	 * @param respuestaServicio
	 * @return
	 * @throws Exception
	 */
	protected Object getRespuesta(RespuestaServicio respuestaServicio) throws Exception {
		
		if (respuestaServicio.getCodigoRespuesta().equals(CondigosRespuesta.EXITOSO)) {
			
			return respuestaServicio.getRespuesta();
			
		} else {
			
			throw new Exception(respuestaServicio.getMensaje());
		}
	}

	/**
	 * ListarTodo metodo que obtiene todos los registros de una tabla
	 * 
	 * @return
	 */
	public List<T> listarTodo() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<T> res = new ArrayList<T>();
		
		@SuppressWarnings("rawtypes")
		List lResult = mapper.convertValue(getRespuesta(getGET("listarTodo", null)), List.class);
		
		for (int i = 0; i < lResult.size(); i++) {
			T e = mapper.convertValue(lResult.get(i), this.clazz);
			res.add(e);
		}

		return res;
	}

	/**
	 * Metodo que buscar por el id de una tabla
	 * @param id
	 * @return
	 */
	public T buscarPorId(String id) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(getRespuesta(getPOST("buscarPorId/" + id, null)), this.clazz);
	}

	/**
	 * Metodo que persiste un objeto en la tabla
	 * @param object
	 * @return
	 */
	public boolean guardar(T object) throws Exception  {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(getRespuesta(getPOST("guardar", object)), boolean.class);
	}

	/**
	 * Metodo que realiza la actualizacion de objeto
	 * @param object
	 * @return
	 */
	public boolean actualizar(T object) throws Exception  {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(getRespuesta(getPOST("actualizar", object)), boolean.class);
	}

	/**
	 * Metodo que elimina un objeto de una tabla
	 * @param object
	 * @return
	 */
	public boolean eliminar(T object) throws Exception  {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(getRespuesta(getPOST("eliminar", object)), boolean.class);
	}
	
	/**
	 * ListarTodo metodo que obtiene todos los registros de una tabla
	 * @return
	 */
	public List<T> buscarFiltro(T object) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<T> res = new ArrayList<T>();
		
		@SuppressWarnings("rawtypes")
		List lResult = mapper.convertValue(getRespuesta(getPOST("buscarFiltro", object)), List.class);
		for (int i = 0; i < lResult.size(); i++) {
			T e = mapper.convertValue(lResult.get(i), this.clazz);
			res.add(e);
		}

		return res;
	}
	
}
