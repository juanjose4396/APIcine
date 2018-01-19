package com.orussystem.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.orussystem.modelo.Peliculas;
import com.orussystem.modelo.Usuarios;

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
