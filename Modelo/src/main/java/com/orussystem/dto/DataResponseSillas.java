package com.orussystem.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;
import com.orussystem.modelo.Sillas;

/**
 * Clase representativa al bean utilizado como dto para representar la data requerida por la respuesta de un 
 * servicio, contiene los atributos y los metodos set y get necesarios para persistir los datos
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */

@Component
public class DataResponseSillas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoRespuesta;
	
	private String mensaje;
	
	private List<Sillas> sillas;
	
	private List<Sillas> sillasOcupadas;
	
	public List<Sillas> getSillasOcupadas() {
		return sillasOcupadas;
	}

	public void setSillasOcupadas(List<Sillas> sillasOcupadas) {
		this.sillasOcupadas = sillasOcupadas;
	}

	public List<Sillas> getSillas() {
		return sillas;
	}

	public void setSillas(List<Sillas> sillas) {
		this.sillas = sillas;
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
