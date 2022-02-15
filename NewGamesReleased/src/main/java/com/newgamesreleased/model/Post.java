package com.newgamesreleased.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		
		private String titulo;
		private String contenido;

		public Post() {
		}
		
		public Post(String titulo, String contenido) {
			super();
			this.titulo = titulo;
			this.contenido = contenido;
		}
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
		
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		
		public void setContenido(String contenido) {
			this.contenido = contenido;
		}
		
		public String getTitulo() {
			return this.titulo;
		}
		
		public String getContenido() {
			return this.contenido;
		}
		
		@Override
		public String toString() {
			return (this.titulo + "\n" + this.contenido);
		}
		
}
