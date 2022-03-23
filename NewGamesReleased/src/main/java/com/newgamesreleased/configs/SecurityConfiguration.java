package com.newgamesreleased.configs;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	RepositoryUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Páginas de administrador
		http.authorizeRequests().antMatchers("/crear-post").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/postnuevo").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/editar-post/{id}").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("editar-post/cambiarpost/{id}").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/borrar-post/{id}").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/crear-tag").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/tagnueva").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/borrar-tags/{id}").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/editar-tag/{id}").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("editar-tag/cambiartag/{id}").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("post/borrar-valoracion/{idPost}/{idValoracion}").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/usuarios").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/usuarios/borrar-usuario/{id}").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/usuarios/editar-usuario/{id}").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/usuarios/editar-usuario/usuario-editado/{id}").hasAnyRole("ADMIN");

		// Páginas de usuarios registrados
		http.authorizeRequests().antMatchers("/post/crear-valoracion/{id}").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("post/crear-valoracion/valoracion-creada/{id}").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/suscribirse/{id}").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/desuscribirse/{id}").hasAnyRole("USER");

		// Private pages (all other pages)
		http.authorizeRequests().anyRequest().permitAll();

		// Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/loginerror");

		// Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");

		// Disable CSRF at the moment
		http.csrf().disable();
	}

}