package com.framework.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

/**
 * Clase que contiene la respuesta del los servicio
 * @author ing Diego Barrera
 *
 */
@Component
@XmlRootElement
public class RespuestaServicio implements Serializable {

	private static final long serialVersionUID = 225617866616892064L;

	private String codigoRespuesta = "";
	
	private Object respuesta = null;
	
	private String mensaje = "";
	
	public RespuestaServicio(){}

	public RespuestaServicio(String mensaje, String codigoRespuesta, Object respuesta) {
		super();
		this.codigoRespuesta = codigoRespuesta;
		this.respuesta = respuesta;
		this.mensaje = mensaje;
	}

	/**
	 * @return the codigoRespuesta
	 */
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	/**
	 * @param codigoRespuesta the codigoRespuesta to set
	 */
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	/**
	 * @return the respuesta
	 */
	public Object getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(Object respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
