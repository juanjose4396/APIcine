package com.orussystem.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.orussystem.modelo.Peliculas;
import com.orussystem.modelo.Usuarios;

@Component
public class DataResponsePelicula implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoRespuesta;
	
	private String mensaje;
	
	private Peliculas pelicula;

	public Peliculas getPelicula() {
		return pelicula;
	}

	public void setPelicula(Peliculas pelicula) {
		this.pelicula = pelicula;
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
