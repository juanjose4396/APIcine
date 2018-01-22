package com.orussystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.framework.contex.SpringContex;
import com.framework.controller.GenericController;
import com.framework.dto.RespuestaServicio;
import com.orussystem.dto.DataResponsePelicula;
import com.orussystem.dto.DataResponsePeliculas;
import com.orussystem.dto.DataResponseSillas;
import com.orussystem.modelo.Peliculas;
import com.orussystem.response.ResponseControllerPelicula;
import com.orussystem.response.ResponseControllerPeliculas;
import com.orussystem.response.ResponseControllerSillas;
import com.orussystem.services.interfaz.PeliculasService;

/**
 * Clase que representa el controlador para los servicios referentes a las peliculas, mapea e implementa las urls y los metodos que responsen 
 * a dichos servicios, se encarga de ejecutar los services correspondientes
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */
@RestController
@RequestMapping("/Peliculas")
public class PeliculasController extends GenericController<Peliculas> {

	//Variables de clase
	private PeliculasService PeliculasService;
	private RespuestaServicio respuestaServicio = null;

	/**
	 *Constructor vacio de la clase
	 */
	public PeliculasController() {}
	
	/** 
	 *Constructor de la clase, se encarga de reemplazar el bean con la implementacion del servicio correspondiente
	 *@param PeliculasService define el servicio que el controlador va a utilizar 
	 */
	@Autowired
	public PeliculasController(@Qualifier("PeliculasService_Impl") PeliculasService generaricServices) {
		super(generaricServices, Peliculas.class);
		this.PeliculasService = generaricServices;
	}
	
	/** 
	 *Metodo que se encarga de recuperar los parametros enviados en el path y de ejecutar el metodo deseado 
	 *del servicio, se encarga de llamar al servicio con el objetivo de listar todas las peliculas de la base de datos
	 *@return response correspondiente a la data que se desea mostrar, devuelve un data mostrando un error ocurrido
	 *o informando un estado exitoso y listando las peliculas y su informacion
	 */
	@RequestMapping(value = "/peliculas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseControllerPeliculas listAll() {
		try {
			return PeliculasService.listAll();
		}catch (Exception e) {
			//En caso de error se carga el bean correspondiente al dto para el response del servicio
			DataResponsePeliculas data = (DataResponsePeliculas)SpringContex.getApplicationContext().getBean(DataResponsePeliculas.class);

			//se da manejo al mensaje de error
			data.setCodigoRespuesta("error");
			data.setMensaje(e.getMessage());
			data.setPeliculas(null);
			
			//se carga el bean del controlador de respuesta asignandole la data
			ResponseControllerPeliculas responseControllerPeliculas = (ResponseControllerPeliculas)SpringContex.getApplicationContext().getBean(ResponseControllerPeliculas.class);
			responseControllerPeliculas.setData(data);
			return responseControllerPeliculas;
		}
	}	
	
	/** 
	 *Metodo que se encarga de recuperar los parametros enviados en el path y de ejecutar el metodo deseado 
	 *del servicio, se encarga de llamar al servicio con el objetivo de listar todas las peliculas de la base de datos
	 *@param id representa el identificador de la pelicula solicitada por el cliente
	 *@return response correspondiente a la data que se desea mostrar, devuelve un data mostrando un error ocurrido
	 *o informando un estado exitoso y listando la informacion de la pelicula deseada
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseControllerPelicula getPelicula(@PathVariable("id") long id) {
		try {
			return PeliculasService.getPelicula(id);
		}catch (Exception e) {
			//En caso de error se carga el bean correspondiente al dto para el response del servicio
			DataResponsePelicula data = (DataResponsePelicula)SpringContex.getApplicationContext().getBean(DataResponsePelicula.class);

			//se da manejo al mensaje de error
			data.setCodigoRespuesta("error");
			data.setMensaje(e.getMessage());
			data.setPelicula(null);
			
			//se carga el bean del controlador de respuesta asignandole la data
			ResponseControllerPelicula responseControllerPelicula = (ResponseControllerPelicula)SpringContex.getApplicationContext().getBean(ResponseControllerPelicula.class);
			responseControllerPelicula.setData(data);
			return responseControllerPelicula;
		}
	}
	
	/** 
	 *Metodo que se encarga de recuperar los parametros enviados en el path y de ejecutar el metodo deseado 
	 *del servicio, se encarga de llamar al servicio con el objetivo de listar las sillas disponibles y no 
	 *disponibles para la pelicula y la funcion deseada
	 *@param id representa el identificador de la pelicula solicitada por el cliente
	 *@param tipo representa el tipo de funcion (2D o 3D) de la pelicula
	 *@return response correspondiente a la data que se desea mostrar, devuelve un data mostrando un error ocurrido
	 *o informando un estado exitoso y listando las sillas de pelicula y funcion deseadas
	 */
	@RequestMapping(value = "/{id}/funcion/{tipo}/sillas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseControllerSillas getSillas(@PathVariable("id") long id,@PathVariable("tipo") String tipo) {
		try {
			return PeliculasService.getSillas(id,tipo);
		}catch (Exception e) {
			//En caso de error se carga el bean correspondiente al dto para el response del servicio
			DataResponseSillas data = (DataResponseSillas)SpringContex.getApplicationContext().getBean(DataResponseSillas.class);
			
			//se da manejo al mensaje de error
			data.setCodigoRespuesta("error");
			data.setMensaje(e.getMessage());
			
			//se carga el bean del controlador de respuesta asignandole la data
			ResponseControllerSillas responseControllerSillas = (ResponseControllerSillas)SpringContex.getApplicationContext().getBean(ResponseControllerSillas.class);
			responseControllerSillas.setData(data);
			
			return responseControllerSillas;
		}
	}
	
}

