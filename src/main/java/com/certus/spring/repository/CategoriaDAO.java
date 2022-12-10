package com.certus.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.certus.spring.models.Categoria;

@Repository
public interface CategoriaDAO extends CrudRepository<Categoria, Integer>{

    @Query(value = "SELECT * FROM categoria WHERE nombre LIKE %?1%", nativeQuery=true)
    List<Categoria> findByQuery(String query);
    
}
