package com.orussystem.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;
import com.orussystem.modelo.Sillas;

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