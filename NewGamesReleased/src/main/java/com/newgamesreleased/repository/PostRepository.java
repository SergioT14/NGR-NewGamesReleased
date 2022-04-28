package com.newgamesreleased.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.newgamesreleased.model.Post;

@CacheConfig(cacheNames="posts")
public interface PostRepository extends JpaRepository<Post,Long>{

	@Cacheable
	@Query("select u from Post u where UPPER(u.titulo) LIKE UPPER(CONCAT ('%',?1,'%')) or UPPER(u.contenido) LIKE UPPER(CONCAT ('%',?1,'%'))")
	List<Post> findByTituloOrContenido(String texto);
	
}
