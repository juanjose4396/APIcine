package com.orussystem.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.hql.internal.classic.ClauseParser;
import org.springframework.core.annotation.Order;

import com.fasterxml.jackson.annotation.JsonIgnore;


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
