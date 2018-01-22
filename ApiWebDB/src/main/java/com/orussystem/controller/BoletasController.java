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
import com.orussystem.dto.DataResponseDetalleVentas;
import com.orussystem.dto.DataResponseLogin;
import com.orussystem.modelo.Boletas;
import com.orussystem.request.RequestControllerCompra;
import com.orussystem.response.ResponseControllerAvailability;
import com.orussystem.response.ResponseControllerDetalleVentas;
import com.orussystem.response.ResponseControllerLogin;
import com.orussystem.services.interfaz.BoletasService;

/**
 * Clase que representa el controlador para los servicios referentes a boletas, mapea e implementa las urls y los metodos que responsen 
 * a dichos servicios, se encarga de ejecutar los services correspondientes
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */
@RestController
@RequestMapping("/Boletas")
public class BoletasController extends GenericController<Boletas> {
	
	//Variables de la clase
	
	private BoletasService BoletasService;
	private RespuestaServicio respuestaServicio = null;

	/**
	 *Constructor vacio de la clase
	 */
	public BoletasController() {}
	
	/** 
	 *Constructor de la clase, se encarga de reemplazar el bean con la implementacion del servicio correspondiente
	 *@param BoletasService define el servicio que el controlador va a utilizar 
	 */
	@Autowired
	public BoletasController(@Qualifier("BoletasService_Impl") BoletasService generaricServices) {
		super(generaricServices, Boletas.class);
		this.BoletasService = generaricServices;
	}
	
	/** 
	 *Metodo que se encarga de recuperar los parametros enviados en el path y de ejecutar el 
	 *metodo deseado del servicio
	 *@param id representa el numero de la pelicula a relacionar dentro de la logica del servicio
	 *@param numeroBoletas representa el numero de boletas que el usuario desea comprar
	 *@param tipo representa el tipo de funcion (2D o 3D) de la pelicula 
	 *@return response correspondiente a la data que se desea mostrar, devuelve un data mostrando un error ocurrido
	 *o informando la disponibilidad de sillas para la pelicula y la funcion deseadas
	 */
	@RequestMapping(value = "/pelicula/{id}/funcion/{tipo}/numero/{numero}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseControllerAvailability getAvailability(@PathVariable("id") long id,@PathVariable("numero") long numeroBoletas,@PathVariable("tipo") String tipo) {
		try {
			return BoletasService.getAvailability(id, numeroBoletas,tipo);
		}catch (Exception e) {
			//En caso de error se carga el bean del response correspondiente
			DataResponseAvailability data = (DataResponseAvailability)SpringContex.getApplicationContext().getBean(DataResponseAvailability.class);
			
			//Se informa con el codigo de respuesta de error y se maneja el mensaje de error
			data.setCodigoRespuesta("error");
			data.setMensaje(e.getMessage());
			
			//Se carga y se retorna el bean de controlador de respuesta para ese servicio asignandole la data
			ResponseControllerAvailability responseControllerAvailability = (ResponseControllerAvailability)SpringContex.getApplicationContext().getBean(ResponseControllerAvailability.class);
			responseControllerAvailability.setData(data);
			return responseControllerAvailability;
		}
	}
	
	/** 
	 *Metodo que se encarga de recuperar los parametros enviados en el body del request y de ejecutar el 
	 *metodo deseado del servicio, representa la transaccion de compra de boletas
	 *@param requestControllerCompra Representa el dto que se encarga de mapear la informacion enviada en el body 
	 *de la peticion POST por el cliente
	 *@return response correspondiente a la data que se desea mostrar, devuelve un data mostrando un error ocurrido
	 *o informando el exito de la compra de boletas
	 */
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
			
			//En caso de error se carga el bean del response correspondiente
			DataResponseLogin data = (DataResponseLogin)SpringContex.getApplicationContext().getBean(DataResponseLogin.class);
			ResponseControllerLogin responseControllerLogin = (ResponseControllerLogin)SpringContex.getApplicationContext().getBean(ResponseControllerLogin.class);
			
			//Se informa con el codigo de respuesta de error y se maneja el mensaje de error
			data.setCodigoRespuesta("error");
			data.setMensaje(e.getMessage());
			
			//Se carga y se retorna el bean de controlador de respuesta para ese servicio asignandole la data
			responseControllerLogin.setData(data);;
			return responseControllerLogin;
		}
	}
	
	/** 
	 *Metodo que se encarga de recuperar los parametros enviados en el path y de ejecutar el 
	 *metodo deseado del servicio para responder con la informacion del detalle de las ventas realizadas
	 *en diferentes requerimientos
	 *@return response correspondiente a la data que se desea mostrar, devuelve un data mostrando un error ocurrido
	 *o informando con el detalle de las ventas
	 */
	@RequestMapping(value = "/detalle/ventas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseControllerDetalleVentas detalleVentas() {
		try {
			return BoletasService.detalleVentas();
		}catch (Exception e) {
			//En caso de error se carga el bean del response correspondiente
			DataResponseDetalleVentas data = (DataResponseDetalleVentas)SpringContex.getApplicationContext().getBean(DataResponseDetalleVentas.class);
			ResponseControllerDetalleVentas responseControllerDetalleVentas = (ResponseControllerDetalleVentas)SpringContex.getApplicationContext().getBean(ResponseControllerDetalleVentas.class);
			
			//Se informa con el codigo de respuesta de error y se maneja el mensaje de error
			data.setCodigoRespuesta("error");
			data.setMensaje(e.toString()+"");
			
			//Se carga y se retorna el bean de controlador de respuesta para ese servicio asignandole la data
			responseControllerDetalleVentas.setData(data);;
			return responseControllerDetalleVentas;
		}
	}
	
}

