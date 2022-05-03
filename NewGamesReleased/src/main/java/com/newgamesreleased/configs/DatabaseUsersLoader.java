package com.newgamesreleased.configs;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.newgamesreleased.model.User;
import com.newgamesreleased.repository.UserRepository;

@Component
public class DatabaseUsersLoader {
	
	@PostConstruct
	private void initDatabase() {
	}
}