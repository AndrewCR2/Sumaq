package com.certus.spring.service;

import com.certus.spring.models.Categoria;
import com.certus.spring.models.Response;

public interface ICategoriaService {
    public Response<Categoria> crearCategoria(Categoria p);

	public Response<Categoria> editarCategoria(Integer id);

	public Response<Categoria> eliminarCategoria(Integer id);
	
	public Response<Categoria> listarCategorias();
}
