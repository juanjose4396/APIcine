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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entidad que representa la tabla peliculas de la base de datos, contiene los atributos representativos a las columnas
 * de la tabla y las anotaciones correspondientes para mapear dichos atributos y las relaciones de la tabla
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */
@Entity
@Table(name = "peliculas")
public class Peliculas {
	
	private String nombre;
	private String descripcion;
	private String url;
	private Long id;
	private Date fecha;
	private Long precio;
	private List<Boletas> boleta = new ArrayList<Boletas>(0);
	
	public Peliculas() {
		
	}
	
	public Peliculas(Long id) {
		this.id=id;
	}
	
	public Peliculas(String nombre, String descripcion, String url, Long id, Date fecha, List<Boletas> boleta) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
		this.id = id;
		this.fecha = fecha;
		this.boleta = boleta;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "peliculas_id_seq")
	@SequenceGenerator(name = "peliculas_id_seq", sequenceName = "peliculas_id_seq", initialValue = 1, allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "nombre", length = 200)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Lob
	@Column(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Lob
	@Column(name = "url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@OneToMany(targetEntity=Boletas.class, mappedBy="pelicula_fk", fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JsonIgnore
	public List<Boletas> getBoleta() {
		return boleta;
	}

	public void setBoleta(List<Boletas> boleta) {
		this.boleta = boleta;
	}

	@Column(name = "precio")
	public Long getPrecio() {
		return precio;
	}

	public void setPrecio(Long precio) {
		this.precio = precio;
	}
	
	
	
}
