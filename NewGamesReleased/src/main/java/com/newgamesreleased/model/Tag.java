package com.example.practica.bd.entities;

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
		
		public void setTag(String name) {
			this.nombre = name;
		}

		public String getTag() {
			return this.nombre;
		}
		
		@Override
		public String toString() {
			return (this.nombre);
		}
		
}