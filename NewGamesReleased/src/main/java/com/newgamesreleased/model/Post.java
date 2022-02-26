package com.newgamesreleased.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Post {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		
		private String titulo;
		private String contenido;
		
		@OneToMany(cascade=CascadeType.ALL)
		private List<Rating> valoraciones = new ArrayList<>();
		
		@ManyToOne
		private Tag etiqueta;
		
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
		
		public void setValoraciones(List<Rating> valoraciones) {
			this.valoraciones = valoraciones;
		}
		
		public String getTitulo() {
			return this.titulo;
		}
		
		public String getContenido() {
			return this.contenido;
		}
		
		public List<Rating> getValoraciones(){
			return this.valoraciones;
		}

		public Tag getEtiqueta() {
			return etiqueta;
		}

		public void setEtiqueta(Tag etiqueta) {
			this.etiqueta = etiqueta;
		}

		@Override
		public String toString() {
			return (this.titulo + "\n" + this.contenido);
		}
		
}
