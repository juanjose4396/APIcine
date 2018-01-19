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
import com.orussystem.dto.DataResponsePeliculas;
import com.orussystem.modelo.Peliculas;
import com.orussystem.request.RequestControllerLogin;
import com.orussystem.response.ResponseControllerLogin;
import com.orussystem.response.ResponseControllerPeliculas;
import com.orussystem.services.interfaz.PeliculasService;

@RestController
@RequestMapping("/Peliculas")
public class PeliculasController extends GenericController<Peliculas> {

	private PeliculasService PeliculasService;

	// Respuesta generica de los servicios
	private RespuestaServicio respuestaServicio = null;

	public PeliculasController() {}
	
	@Autowired
	public PeliculasController(@Qualifier("PeliculasService_Impl") PeliculasService generaricServices) {
		super(generaricServices, Peliculas.class);
		this.PeliculasService = generaricServices;
	}
	
	@RequestMapping(value = "/peliculas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseControllerPeliculas listAll() {
		try {
			return PeliculasService.listAll();
		}catch (Exception e) {
			
			DataResponsePeliculas data = (DataResponsePeliculas)SpringContex.getApplicationContext().getBean(DataResponsePeliculas.class);

			data.setCodigoRespuesta("error");
			data.setMensaje(e.getMessage());
			data.setPeliculas(null);
			
			ResponseControllerPeliculas responseControllerPeliculas = (ResponseControllerPeliculas)SpringContex.getApplicationContext().getBean(ResponseControllerPeliculas.class);
			responseControllerPeliculas.setData(data);
			return responseControllerPeliculas;
		}
	}	
	
}

