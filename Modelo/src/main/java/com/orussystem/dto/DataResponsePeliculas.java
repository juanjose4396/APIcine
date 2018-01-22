package com.orussystem.dto;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Component;
import com.orussystem.modelo.Peliculas;

/**
 * Clase representativa al bean utilizado como dto para representar la data requerida por la respuesta de un 
 * servicio, contiene los atributos y los metodos set y get necesarios para persistir los datos
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */

@Component
public class DataResponsePeliculas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoRespuesta;
	
	private String mensaje;
	
	private List<Peliculas> peliculas;

	public List<Peliculas> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Peliculas> peliculas) {
		this.peliculas = peliculas;
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
