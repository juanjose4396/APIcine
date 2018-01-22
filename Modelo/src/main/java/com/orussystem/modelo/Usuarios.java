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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "usuarios")
public class Usuarios implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String password;
	private List<Boletas> boleta = new ArrayList<Boletas>(0);
	private Roles rol_fk;
	
	public Usuarios(){
		
	}
	public Usuarios(Long id){
		this.id=id;
	}
	
	public Usuarios(Long id, String email, String password, List<Boletas> boleta, Roles rol_fk) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.boleta = boleta;
		this.rol_fk = rol_fk;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_id_seq")
	@SequenceGenerator(name = "usuarios_id_seq", sequenceName = "usuarios_id_seq", initialValue = 1, allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "email", length = 150)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "password", length = 15)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@OneToMany(targetEntity=Boletas.class, mappedBy="usuario_fk", fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JsonIgnore
	public List<Boletas> getBoleta() {
		return boleta;
	}
	public void setBoleta(List<Boletas> boleta) {
		this.boleta = boleta;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name="rol_fk")
	public Roles getRol_fk() {
		return rol_fk;
	}
	public void setRol_fk(Roles rol_fk) {
		this.rol_fk = rol_fk;
	}
	
}
