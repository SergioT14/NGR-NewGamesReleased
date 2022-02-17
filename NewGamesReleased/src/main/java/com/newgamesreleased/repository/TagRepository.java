package com.newgamesreleased.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.newgamesreleased.model.Tag;

public interface TagRepository extends JpaRepository<Tag,Long>{
	
}
