package com.newgamesreleased.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newgamesreleased.model.Rating;

public interface RatingRepository extends JpaRepository<Rating,Long>{
	
}
