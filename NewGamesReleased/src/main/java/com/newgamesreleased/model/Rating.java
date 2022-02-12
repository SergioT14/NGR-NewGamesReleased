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
		
		public Rating() {
		}
		
		public Rating(String user, String txt) {
			super();
			this.usuario = user;
			this.texto = txt;
		}
		
		public void setUsuario(String user) {
			this.usuario = user;
		}
		
		public void setTexto(String txt) {
			this.texto = txt;
		}
		
		public String getUsuario() {
			return this.usuario;
		}
		
		public String getTexto() {
			return this.texto;
		}
		
		@Override
		public String toString() {
			return (this.usuario + "\n" + this.texto);
		}
		
}