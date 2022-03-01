package com.newgamesreleased.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String contrasenya;
	
	@OneToMany(mappedBy ="valoracion", cascade = CascadeType.MERGE)
	private List<Rating> valoraciones = new ArrayList<>();
	
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

	@Override
	public String toString() {
		return ("Nombre de usuario: " + nombre + " Contrasenya: " + contrasenya);
	}
	

}
