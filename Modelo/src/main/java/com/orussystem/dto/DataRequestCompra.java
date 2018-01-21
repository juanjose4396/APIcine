package com.orussystem.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DataRequestCompra implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idUsuario;
	private Long idPelicula;
	private List<Long> sillas;
	private String tipo;
	private Date fecha;
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdPelicula() {
		return idPelicula;
	}
	public void setIdPelicula(Long idPelicula) {
		this.idPelicula = idPelicula;
	}
	public List<Long> getSillas() {
		return sillas;
	}
	public void setSillas(List<Long> sillas) {
		this.sillas = sillas;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
