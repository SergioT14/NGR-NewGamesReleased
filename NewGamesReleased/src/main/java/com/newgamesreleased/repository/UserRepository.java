package com.newgamesreleased.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newgamesreleased.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
