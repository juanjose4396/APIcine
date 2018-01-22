package com.orussystem.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Clase representativa al bean utilizado como dto para representar la data requerida por la respuesta de un 
 * servicio, contiene los atributos y los metodos set y get necesarios para persistir los datos
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */

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
