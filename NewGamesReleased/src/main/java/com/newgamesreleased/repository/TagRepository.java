package com.example.practica.bd.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.practica.bd.entities.*;

public interface TagRepository extends JpaRepository<Tag,Long>{
	
}