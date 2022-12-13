package com.certus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
		
		UserDetails Usuario = usuarios
				.username("Invitado")
				.password("12345")
				.roles("User")
				.build();
		return new InMemoryUserDetailsManager(UsuarioOne, Usuario);
	}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeRequests(
				(req) -> req
						.antMatchers("/home","/bootstrap/**","/CSS/**","/img/**","/JS/**").permitAll()
						.antMatchers("/usuario/listar").hasRole("User")
						.antMatchers("/usuario/Editar/**").hasRole("SuperAdmin")
						.antMatchers("/categoria/listar").hasRole("User")
						.antMatchers("/categoria/Editar/**").hasRole("SuperAdmin")
						.antMatchers("/home/Productos").hasRole("User")
						.antMatchers("/home/Editar/**").hasRole("SuperAdmin")
						.anyRequest().authenticated()
				).formLogin();
		
		return http.build();
	}

}
