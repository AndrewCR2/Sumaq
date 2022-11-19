package com.certus.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.certus.spring.models.Categoria;

@Repository
public interface CategoriaDAO extends CrudRepository<Categoria, Integer>{
    
}
