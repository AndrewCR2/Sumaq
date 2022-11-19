package com.certus.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.certus.spring.models.Usuario;

public interface IUsuario extends CrudRepository<Usuario, Integer>{
	
}
