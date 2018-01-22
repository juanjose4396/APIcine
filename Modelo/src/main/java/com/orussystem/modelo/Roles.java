package com.orussystem.modelo;

import java.io.Serializable;
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
 * Entidad que representa la tabla roles de la base de datos, contiene los atributos representativos a las columnas
 * de la tabla y las anotaciones correspondientes para mapear dichos atributos y las relaciones de la tabla
 * @author: Juan Jose Perdomo Forero
 * @version: 21/01/2017
 * @see <a href = "https://github.com/juanjose4396/APIcine" /> Repositorio del proyecto </a>
 */

@Entity
@Table(name = "roles")
public class Roles implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private List<Boletas> usuario = new ArrayList<Boletas>(0);
	
	public Roles(){
		
	}
	public Roles(Long id){
		this.id=id;
	}
	
	public Roles(Long id, String nombre, List<Boletas> usuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.usuario = usuario;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_seq")
	@SequenceGenerator(name = "roles_id_seq", sequenceName = "roles_id_seq", initialValue = 1, allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@OneToMany(targetEntity=Boletas.class, mappedBy="usuario_fk", fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JsonIgnore
	public List<Boletas> getUsuario() {
		return usuario;
	}
	public void setUsuario(List<Boletas> usuario) {
		this.usuario = usuario;
	}
	
}
