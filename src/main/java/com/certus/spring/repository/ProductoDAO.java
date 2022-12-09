package com.certus.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.certus.spring.models.Producto;

@Repository
public interface ProductoDAO extends CrudRepository<Producto, Integer> {
	
}
