package com.newgamesreleased.configs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.newgamesreleased.model.User;
import com.newgamesreleased.repository.UserRepository;

@Service
public class RepositoryUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		User u = userRepository.getByNombre(username);
		if( u == null)throw new UsernameNotFoundException("Usuario no encontrado");
		
		List<GrantedAuthority> roles = new ArrayList<>();
		for(String rol : u.getRoles()) {
			roles.add(new SimpleGrantedAuthority("ROLE_"+rol));
		}
		
		return new org.springframework.security.core.userdetails.User(u.getNombre(), u.getContrasenya(), roles);
	}

}