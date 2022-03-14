package com.internalService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.internalService.model.Post;

public interface PostRepository extends JpaRepository<Post,Long>{

	@Query("select u from Post u where UPPER(u.titulo) LIKE UPPER(CONCAT ('%',?1,'%')) or UPPER(u.contenido) LIKE UPPER(CONCAT ('%',?1,'%'))")
	List<Post> findByTituloOrContenido(String texto);
	
}