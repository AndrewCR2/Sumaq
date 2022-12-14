package com.certus.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.certus.spring.models.Producto;

@Repository
public interface ProductoDAO extends CrudRepository<Producto, Integer> {

    @Query(value = "SELECT * FROM producto WHERE nombre LIKE %?1%", nativeQuery=true)
    List<Producto> findByQuery(String query);

    @Query(value = "SELECT * FROM producto LIMIT ?1", nativeQuery=true)
    List<Producto> findLimit(Integer limit);
}
