package com.orussystem.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.framework.contex.SpringContex;
import com.framework.controller.GenericController;
import com.framework.dto.RespuestaServicio;
import com.orussystem.dto.DataResponseAvailability;
import com.orussystem.dto.DataResponseLogin;
import com.orussystem.dto.DataResponsePelicula;
import com.orussystem.dto.DataResponsePeliculas;
import com.orussystem.modelo.Boletas;
import com.orussystem.modelo.Peliculas;
import com.orussystem.request.RequestControllerCompra;
import com.orussystem.request.RequestControllerLogin;
import com.orussystem.response.ResponseControllerAvailability;
import com.orussystem.response.ResponseControllerLogin;
import com.orussystem.response.ResponseControllerPelicula;
import com.orussystem.response.ResponseControllerPeliculas;
import com.orussystem.services.interfaz.BoletasService;
import com.orussystem.services.interfaz.PeliculasService;

@RestController
@RequestMapping("/Boletas")
public class BoletasController extends GenericController<Boletas> {

	private BoletasService BoletasService;

	// Respuesta generica de los servicios
	private RespuestaServicio respuestaServicio = null;

	public BoletasController() {}
	
	@Autowired
	public BoletasController(@Qualifier("BoletasService_Impl") BoletasService generaricServices) {
		super(generaricServices, Boletas.class);
		this.BoletasService = generaricServices;
	}
	
	@RequestMapping(value = "/pelicula/{id}/funcion/{tipo}/numero/{numero}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseControllerAvailability getAvailability(@PathVariable("id") long id,@PathVariable("numero") long numeroBoletas,@PathVariable("tipo") String tipo) {
		try {
			return BoletasService.getAvailability(id, numeroBoletas,tipo);
		}catch (Exception e) {
			
			DataResponseAvailability data = (DataResponseAvailability)SpringContex.getApplicationContext().getBean(DataResponseAvailability.class);

			data.setCodigoRespuesta("error");
			data.setMensaje(e.getMessage());
			
			ResponseControllerAvailability responseControllerAvailability = (ResponseControllerAvailability)SpringContex.getApplicationContext().getBean(ResponseControllerAvailability.class);
			responseControllerAvailability.setData(data);
			return responseControllerAvailability;
		}
	}
	
	@RequestMapping(value = "/compra", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseControllerLogin crearBoletas(@RequestBody RequestControllerCompra requestControllerCompra) {
		try {
			Long idUsuario = requestControllerCompra.getData().getIdUsuario();
			Long idPelicula = requestControllerCompra.getData().getIdPelicula();
			List<Long> sillas = requestControllerCompra.getData().getSillas();
			String tipo = requestControllerCompra.getData().getTipo();
			Date fecha = requestControllerCompra.getData().getFecha();
			return BoletasService.crearBoletas(idUsuario,idPelicula,sillas,tipo,fecha);
		}catch (Exception e) {
			
			DataResponseLogin data = (DataResponseLogin)SpringContex.getApplicationContext().getBean(DataResponseLogin.class);
			ResponseControllerLogin responseControllerLogin = (ResponseControllerLogin)SpringContex.getApplicationContext().getBean(ResponseControllerLogin.class);
			
			data.setCodigoRespuesta("error");
			data.setMensaje(e.getMessage());
			
			responseControllerLogin.setData(data);;
			return responseControllerLogin;
		}
	}
	
}

