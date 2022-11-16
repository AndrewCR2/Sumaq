package com.certus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.certus.spring.models.Categoria;
import com.certus.spring.models.Personaje;
import com.certus.spring.models.Response;
import com.certus.spring.repository.CategoriaDAO;
import java.util.List;


@Component("ServicioCategoria")
public class CategoriaService implements ICategoriaService{

    @Autowired
    CategoriaDAO categoriaRepository;

    @Override
    public Response<Categoria> crearCategoria(Categoria p) {
        Response<Categoria> response = new Response<>();
		
        Categoria ctg = categoriaRepository.save(p);

		// validacion
		if (ctg != null) {
			
			response.setEstado(true);
			response.setMensaje("creado correctamente");
		} else {
			response.setEstado(false);
			response.setMensaje("se prdujco un error");
		}
		
		return response;
    }

    @Override
    public Response<Categoria> editarCategoria(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Response<Categoria> eliminarCategoria(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Response<Categoria> listarCategorias() {
        Response<Categoria> response = new Response<>();

		try {
			response.setEstado(true);
			response.setListData((List<Categoria>)categoriaRepository.findAll());
			response.setMensaje("Personaje obtenidos exitosamente");
			
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Error al obtener los personajes");
			response.setMensajeError(e.getStackTrace().toString());
		}
		
		return response;
    }

}
