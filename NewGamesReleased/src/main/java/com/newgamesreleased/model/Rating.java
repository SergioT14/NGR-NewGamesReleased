package com.newgamesreleased.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rating {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		
		private String usuario;
		private String texto;
		private int puntuacion;
		
		public Rating() {
		}
		
		public Rating(String user, String txt, int puntuacion) {
			super();
			this.usuario = user;
			this.texto = txt;
			this.puntuacion = puntuacion;
		}
		
		public void setUsuario(String user) {
			this.usuario = user;
		}
		
		public void setTexto(String txt) {
			this.texto = txt;
		}
		
		public void setPuntuacion(int puntuacion) {
			this.puntuacion = puntuacion;
		}
		
		public String getUsuario() {
			return this.usuario;
		}
		
		public String getTexto() {
			return this.texto;
		}
		
		public int getPuntuacion() {
			return this.puntuacion;
		}
		
		@Override
		public String toString() {
			return (this.usuario + "\n" + this.texto + "\n" 
		+ this.puntuacion + " estrella(s)");
		}
		
}