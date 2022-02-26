package com.newgamesreleased.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.newgamesreleased.model.Tag;

public interface TagRepository extends JpaRepository<Tag,Long>{
	
	@Query("select u from Tag u where UPPER(u.nombre) LIKE UPPER(CONCAT ('%',?1,'%'))")
	List<Tag> findByNombre(String texto);
	
	Tag getByNombre(String nombre);
}
