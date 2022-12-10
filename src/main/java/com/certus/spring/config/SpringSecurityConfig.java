package com.certus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	
	@Bean
	InMemoryUserDetailsManager detailsManager() {
		
		User.UserBuilder usuarios = User.withDefaultPasswordEncoder();
		
		UserDetails UsuarioOne = usuarios
				.username("Admin")
				.password("12345")
				.roles("SuperAdmin")
				.build();
		return new InMemoryUserDetailsManager(UsuarioOne);
	}
	

}
