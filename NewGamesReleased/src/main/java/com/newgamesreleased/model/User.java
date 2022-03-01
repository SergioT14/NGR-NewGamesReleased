package com.newgamesreleased.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String contrasenya;
	
	@ManyToMany
	List <Tag> suscripciones = new ArrayList<>();
	
	public User() {
		
	}
	
	public User(String nombre, String contrasenya) {
		super();
		this.nombre = nombre;
		this.contrasenya = contrasenya;
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

	@Override
	public String toString() {
		return ("Nombre de usuario: " + nombre + " Contrasenya: " + contrasenya);
	}
	

}
