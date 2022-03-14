package com.internalService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.internalService.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("select u from User u where UPPER(u.nombre) LIKE UPPER(CONCAT ('%',?1,'%'))")
	List<User> findByUsuario(String texto);
	
	User getByNombre(String texto);
}
