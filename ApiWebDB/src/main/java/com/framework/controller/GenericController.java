package com.framework.controller;

import java.io.Serializable;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.framework.dto.RespuestaServicio;
import com.framework.framework.util.CondigosRespuesta;
import com.framework.services.GenericServices;

/**
 * Clase Controller generic
 * @author ing Diego Barrera Sotelo
 * @param <T>
 */
public abstract class GenericController<T> {

	// Manejador de Logger
	public static final Logger LOGGER = Logger.getLogger(GenericController.class);

	// Respuesta generica de los servicios
	private RespuestaServicio respuestaServicio = null;
	
	// Variable de class
	private Class<T> clazz = null;

	private GenericServices<T, Serializable> genericServices;

	public GenericController() { }

	/**
	 * Constructor
	 * @param genericServices
	 * @param clazz
	 */
	public GenericController(GenericServices<T, Serializable> genericServices, Class<T> clazz) {
		this.genericServices = genericServices;
		this.clazz = clazz;
	}

	/**
	 * Metodo que realiza la busqueda por id
	 */
	@RequestMapping(value = "/buscarPorId/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RespuestaServicio buscarPorId(@PathVariable Long id) {
		try {
			return genericServices.buscarPorId(clazz,id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			respuestaServicio = new RespuestaServicio(e.getMessage(), CondigosRespuesta.ERROR_BUSCANDO_DB, false);
		}
		return respuestaServicio;
	}
	
	/**
	 * Metodo encargado de persistir una entidad
	 * @param BufferConsulta Entidad que se desea persistir
	 * @return RespuestaServicio
	 */
	@RequestMapping(value = "/guardar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody RespuestaServicio guardar(@RequestBody T t) {
		try {
			return genericServices.guardar(t);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			respuestaServicio = new RespuestaServicio(e.getMessage(), CondigosRespuesta.ERROR_GUARDANDO_DB, false);
		}
		return respuestaServicio;
	}

	/**
	 * Metodo que realiza la actualizacion sobre la entidad
	 */
	@RequestMapping(value = "/actualizar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RespuestaServicio actualizar(@RequestBody T t) {
		try {
			return genericServices.actualizar(t);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			respuestaServicio = new RespuestaServicio(e.getMessage(), CondigosRespuesta.ERROR_ACTUALIZANDO_DB, false);
		}
		return respuestaServicio;
	}

	/**
	 * Metodo que realiza la eliminacion sobre la entidad
	 */
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RespuestaServicio eliminar(@RequestBody T t) {
		try {
			return genericServices.eliminar(t);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			respuestaServicio = new RespuestaServicio(null, CondigosRespuesta.ERROR_ELIMINANDO_DB, false);
		}
		return respuestaServicio;
	}

	/**
	 * Metodo qeu realiza merge sobre la entidad
	 */
	@RequestMapping(value = "/merge", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RespuestaServicio merge(@RequestBody T t) {
		try {
			return genericServices.merge(t);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			respuestaServicio = new RespuestaServicio(null, CondigosRespuesta.ERROR_MARGE_DB, false);
		}
		return respuestaServicio;
	}

	/**
	 * Metodo que busca todos los registros de una entidad
	 */
	@RequestMapping(value = "/listarTodo",params = {"_page", "_limit"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RespuestaServicio listarTodo(@RequestBody T t,
			@RequestParam(value = "_page") Integer _page,
			@RequestParam(value = "_limit") Integer _limit,
			@RequestParam("_sort") Optional<String> sort,
			@RequestParam("_order") Optional<String> order,
			@RequestParam("q") Optional<String> q) {
		try {
			String _q= q.isPresent() ? q.get() :"";
			String _sort = sort.isPresent() ? sort.get() :"id";
			String _order = order.isPresent() ? order.get() :"ASC";

			return genericServices.listarTodo(this.clazz, t,_limit,_page, _sort,_order, _q);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			respuestaServicio = new RespuestaServicio(e.getMessage(), CondigosRespuesta.ERROR_BUSCANDO_DB, false);
		}
		return respuestaServicio;
	}
	/**
	 * Metodo que busca todos los registros de una entidad
	 */
	@RequestMapping(value = "/buscarFiltro", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RespuestaServicio buscarFiltro(@RequestBody T t) {
		try {
			return genericServices.buscarFiltro(t);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			respuestaServicio = new RespuestaServicio(e.getMessage(), CondigosRespuesta.ERROR_BUSCANDO_DB, false);
		}
		return respuestaServicio;
	}
}