package com.orussystem.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.orussystem.modelo.Usuarios;

@Component
public class DataResponseLogin implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Usuarios usuario;
	
	private String codigoRespuesta;
	
	private String mensaje;

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
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
