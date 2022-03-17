package com.newgamesreleased.configs;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.newgamesreleased.model.User;
import com.newgamesreleased.repository.UserRepository;

@Component
public class DatabaseUsersLoader {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	@PostConstruct
	private void initDatabase() {
		
		userRepository.save(new User("admin",passEncoder.encode("0000"),"newgamesreleaseddad@gmail.com","USER","ADMIN"));
		userRepository.save(new User("user",passEncoder.encode("1234"),"soyuser@email.com","USER"));
	}
}