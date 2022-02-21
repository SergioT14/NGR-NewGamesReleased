package com.newgamesreleased.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.newgamesreleased.model.Rating;

public interface RatingRepository extends JpaRepository<Rating,Long>{
	
	
	@Query("select u from Rating u where UPPER(u.usuario) = UPPER(?1) Or UPPER(u.texto) = UPPER(?1)")
	List<Rating> findByUsuarioOrContenido(String texto);
}
