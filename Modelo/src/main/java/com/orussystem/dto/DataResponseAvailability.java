package com.orussystem.dto;

import java.io.Serializable;
import org.springframework.stereotype.Component;

/**
 * Clase representativa al bean utilizado como dto para representar la data requerida por la respuesta de un 
 * servicio, contiene los atributos y los metodos set y get necesarios para persistir los datos
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */

@Component
public class DataResponseAvailability implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoRespuesta;
	
	private String mensaje;
	
	private String availability;
	
	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
