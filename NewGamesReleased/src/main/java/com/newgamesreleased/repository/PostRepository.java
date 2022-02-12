package com.newgamesreleased.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newgamesreleased.model.*;

public interface PostRepository extends JpaRepository<Post,Long>{
	
}
