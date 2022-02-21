package com.newgamesreleased.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.newgamesreleased.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("select u from User u where UPPER(u.nombre) = UPPER(?1)")
	List<User> findByUsuario(String texto);
	
}
