package com.newgamesreleased.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tag {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		
		private String nombre;
		
		public Tag() {
		}
		
		public Tag(String name) {
			super();
			this.nombre = name;
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
		
		@Override
		public String toString() {
			return (this.nombre);
		}
		
}
