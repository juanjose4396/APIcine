package com.orussystem.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "boletas")
public class Boletas {
	
	private Long id;
	private Date fecha;
	private String tipo;
	private Usuarios usuario_fk;
	private Sillas silla_fk;
	private Peliculas pelicula_fk;
		
		
	public Boletas() {}
		
	public Boletas(Long id) {
			this.id=id;
	}
	
	public Boletas(Long id, Date fecha, String tipo, Usuarios usuario_fk, Sillas silla_fk, Peliculas pelicula_fk) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.tipo = tipo;
		this.usuario_fk = usuario_fk;
		this.silla_fk = silla_fk;
		this.pelicula_fk = pelicula_fk;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boletas_id_seq")
	@SequenceGenerator(name = "boletas_id_seq", sequenceName = "boletas_id_seq", initialValue = 1, allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "tipo", length = 10)
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name="usuario_fk")
	public Usuarios getUsuario_fk() {
		return usuario_fk;
	}

	public void setUsuario_fk(Usuarios usuario_fk) {
		this.usuario_fk = usuario_fk;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name="silla_fk")
	public Sillas getSilla_fk() {
		return silla_fk;
	}

	public void setSilla_fk(Sillas silla_fk) {
		this.silla_fk = silla_fk;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name="pelicula_fk")
	public Peliculas getPelicula_fk() {
		return pelicula_fk;
	}

	public void setPelicula_fk(Peliculas pelicula_fk) {
		this.pelicula_fk = pelicula_fk;
	}
	
	
}
