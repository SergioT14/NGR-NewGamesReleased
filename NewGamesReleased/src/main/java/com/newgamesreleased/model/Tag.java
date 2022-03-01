package com.newgamesreleased.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Tag {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		
		private String nombre;
		
		@OneToMany(mappedBy ="etiqueta")
		private List<Post> posts = new ArrayList<>();
		
		@ManyToMany(mappedBy = "suscripciones")
		private List<User> suscritos = new ArrayList<>();
		
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
		
		public List<Post> getPosts() {
			return posts;
		}

		public void setPosts(List<Post> posts) {
			this.posts = posts;
		}
		
		public void addPost(Post post) {
			posts.add(post);
			post.setEtiqueta(this);
		}
		
		public void removePost(Post post) {
			posts.remove(post);
			post.setEtiqueta(null);
		}

		public List<User> getSuscritos() {
			return suscritos;
		}

		public void setSuscritos(List<User> suscritos) {
			this.suscritos = suscritos;
		}

		@Override
		public String toString() {
			return (this.nombre);
		}
		
}
