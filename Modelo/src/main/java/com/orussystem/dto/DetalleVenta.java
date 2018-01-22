package com.orussystem.dto;

import java.io.Serializable;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * Clase representativa al bean utilizado como dto para representar la data requerida por la respuesta de un 
 * servicio, contiene los atributos y los metodos set y get necesarios para persistir los datos
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */

@Component
public class DetalleVenta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Date fecha;
	private int cantidad2D;
	private int cantidad3D;
	private Double total2D;
	private Double total3D;
	private Double total;
	
	public int getCantidad2D() {
		return cantidad2D;
	}
	public void setCantidad2D(int cantidad2d) {
		cantidad2D = cantidad2d;
	}
	public int getCantidad3D() {
		return cantidad3D;
	}
	public void setCantidad3D(int cantidad3d) {
		cantidad3D = cantidad3d;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getTotal2D() {
		return total2D;
	}
	public void setTotal2D(Double total2d) {
		total2D = total2d;
	}
	public Double getTotal3D() {
		return total3D;
	}
	public void setTotal3D(Double total3d) {
		total3D = total3d;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

}
