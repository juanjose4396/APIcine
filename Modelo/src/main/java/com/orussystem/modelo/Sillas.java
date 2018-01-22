package com.orussystem.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entidad que representa la tabla sillas de la base de datos, contiene los atributos representativos a las columnas
 * de la tabla y las anotaciones correspondientes para mapear dichos atributos y las relaciones de la tabla
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */

@Entity
@Table(name = "sillas")
public class Sillas {
	
	private Long id;
	private String ubicacion;
	private List<Boletas> boleta = new ArrayList<Boletas>(0);
		
	public Sillas() {}
		
	public Sillas(Long id) {
			this.id=id;
	}

	public Sillas(Long id, String ubicacion, List<Boletas> boleta) {
		super();
		this.id = id;
		this.ubicacion = ubicacion;
		this.boleta = boleta;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sillas_id_seq")
	@SequenceGenerator(name = "sillas_id_seq", sequenceName = "sillas_id_seq", initialValue = 1, allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "ubicacion", length= 10)
	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@OneToMany(targetEntity=Boletas.class, mappedBy="silla_fk", fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JsonIgnore
	public List<Boletas> getBoleta() {
		return boleta;
	}

	public void setBoleta(List<Boletas> boleta) {
		this.boleta = boleta;
	}

}
