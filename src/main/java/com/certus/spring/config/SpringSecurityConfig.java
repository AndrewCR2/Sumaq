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
						.antMatchers("/").hasAnyRole("SuperAdmin","User")
						.antMatchers("/productos").hasAnyRole("SuperAdmin","User")
						.antMatchers("/productos").hasAnyRole("SuperAdmin","User")
						.antMatchers("/usuario/crear").hasAnyRole("SuperAdmin","User")
						/*Usuarios*/
						.antMatchers("/usuario/listar").hasRole("SuperAdmin")
						.antMatchers("/usuario/Editar/**").hasRole("SuperAdmin")
						/*Categoria*/
						.antMatchers("/categoria/lista").hasRole("SuperAdmin")
						.antMatchers("/categoria/Editar/**").hasRole("SuperAdmin")
						.antMatchers("/categoria/crear/**").hasRole("SuperAdmin")
						/*Productos*/
						.antMatchers("/Productos/admin").hasRole("SuperAdmin")
						.antMatchers("/Editar/**").hasRole("SuperAdmin")
						.antMatchers("/crear/**").hasRole("SuperAdmin")
						.anyRequest().authenticated()
				).formLogin();
		
		return http.build();
	}
}
