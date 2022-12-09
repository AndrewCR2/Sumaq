package com.certus.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.certus.spring.models.Categoria;
import com.certus.spring.models.Response;
import com.certus.spring.service.ICategoriaService;

@RestController
@RequestMapping("/api/categoria")
public class ApiCategoriaController {
    
    @Autowired
	@Qualifier("ServicioCategoria")
	private ICategoriaService InterfaceCategoria;

    @GetMapping("/listar")
    public Response<Categoria> listarCategorias () {
        Response<Categoria> rspta = InterfaceCategoria.listarCategorias();
        return rspta;
    }

    @GetMapping("/buscar")
    public Response<Categoria> buscarPorQuery (@RequestParam(value = "q") String query) {
        Response<Categoria> rspta = InterfaceCategoria.buscarPorQuery(query);
        return rspta;
    }

    @DeleteMapping("/eliminar/{id}")
	public Response<Categoria> eliminarPersonaje (@PathVariable int id){		
		Response<Categoria> rspta = InterfaceCategoria.eliminarCategoria(id);		
		return rspta;
	}

}
