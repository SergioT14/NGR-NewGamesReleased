package com.newgamesreleased.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String contrasenya;
	
	public User() {
		
	}
	
	public User(String nombre, String contrasenya) {
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
	
	@Override
	public String toString() {
		return ("Nombre de usuario: " + nombre + " Contrasenya: " + contrasenya);
	}
	

}
