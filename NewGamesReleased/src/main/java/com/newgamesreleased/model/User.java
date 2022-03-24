package com.newgamesreleased.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String contrasenya;
	private String email;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles = new ArrayList<>();
	
	@OneToMany(mappedBy ="usuario")
	private List<Rating> valoraciones = new ArrayList<>();
	
	@ManyToMany
	@JsonIgnore
	List <Tag> suscripciones = new ArrayList<>();
	
	public User() {
		
	}
	
	public User(String nombre, String contrasenya, String email, String... roles) {
		super();
		this.nombre = nombre;
		this.contrasenya = contrasenya;
		this.email = email;
		this.roles = List.of(roles);
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasenya() {
		return contrasenya;
	}
	public void setContrasenya(String contraseña) {
		this.contrasenya = contraseña;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public void addRol(String rol) {
		this.roles.add(rol);
	}
	
	public void removeRol(String rol) {
		this.roles.remove(rol);
	}

	public List<Tag> getSuscripciones() {
		return suscripciones;
	}

	public void setSuscripciones(List<Tag> suscripciones) {
		this.suscripciones = suscripciones;
	}
	
	public void addSuscripcion(Tag etiqueta) {
		suscripciones.add(etiqueta);
		etiqueta.getSuscritos().add(this);
	}
	
	public void removeSuscripcion(Tag etiqueta) {
		suscripciones.remove(etiqueta);
		etiqueta.getSuscritos().remove(this);
	}

	public List<Rating> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(List<Rating> valoraciones) {
		this.valoraciones = valoraciones;
	}

	public void addRating(Rating r) {
		valoraciones.add(r);
		r.setUsuario(this);
	}
	
	public void removeRating(Rating r) {
		valoraciones.remove(r);
		r.setUsuario(null);
	}
	
	//Sin la contraseña en el toString por razones obvias
	@Override
	public String toString() {
		return ("Nombre de usuario: " + nombre);
	}
	

}
