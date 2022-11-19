package com.certus.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.certus.spring.models.Categoria;
import com.certus.spring.models.Response;
import com.certus.spring.service.ICategoriaService;

@Controller
@RequestMapping("/categoria")
@SessionAttributes("categoria")
public class CategoriaController {

	@Autowired
	@Qualifier("ServicioCategoria")
	private ICategoriaService InterfaceCategoria;
	
	@Value("${title.generic}")
	private String titlePage;
	
	@GetMapping({"","lista","LISTA"})
	public String listaCategoria(Model model) {	
		Response<Categoria> rspta = InterfaceCategoria.listarCategorias();
		
		model.addAttribute("TituloPagina", titlePage);
		model.addAttribute("titulo", "Sección J98 - Demo listado");
		
		if(rspta.getEstado()) {
			model.addAttribute("Mensaje", rspta.getMensaje());
			model.addAttribute("listita", rspta.getListData());
			
			return "Categoria/categoria_lista";
		}else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());
			return "errores";
		}

	}
	
	@GetMapping("/form")
	public String formCategoria(Model model) {
		
		Categoria categoria = new Categoria();

		model.addAttribute("TituloPagina", titlePage);
		model.addAttribute("titulo", "Crear categoria");
		model.addAttribute("parrafo","Agrega una nueva categoria y registra nuevos productos");
		model.addAttribute("btnForm", "Enviar");
		model.addAttribute("categoria", categoria);
		
		
		return "Categoria/categoria_form";
	}

	@PostMapping("/crear")
	public String crearCategoria(
		@Valid Categoria categoria, 
		BindingResult result, 
		Model model, 
		SessionStatus sStatus){
			
			if (result.hasErrors()) {
			model.addAttribute("TituloPagina", titlePage);
			model.addAttribute("titulo", "Crear categoria");
			model.addAttribute("parrafo","Agrega una nueva categoria y registra nuevos productos");
			model.addAttribute("btnForm", "Enviar");
			return "Categoria/categoria_form";
		}

		Response<Categoria> rspta = InterfaceCategoria.crearCategoria(categoria);
		
		if (rspta.getEstado()) {
			sStatus.setComplete();
			return "redirect:/categoria/lista";
		} else {
			
			model.addAttribute("mensaje",rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());
			return "errores";
		}
	}
	
	@GetMapping("/Editar/{idCategoria}")
	public String EditarCategoria(@PathVariable int idCategoria, Model model) {

		Response<Categoria> rspta = InterfaceCategoria.editarCategoria(idCategoria);
		
		if(!rspta.getEstado()){
			return "redirect/categoria/lista";
		}else{
			model.addAttribute("TituloPagina", titlePage);
			model.addAttribute("titulo", "Editando la categoria " +"'"+ rspta.getData().getNombre()+"'");
			model.addAttribute("parrafo", "Edita la categoria y registra nuevamente los productos" );
			model.addAttribute("categoria", rspta.getData());
			model.addAttribute("btnForm", "Guardar cambios");
		}

		return "Categoria/categoria_form";
	}
	
	@GetMapping("/Eliminar/{idCategoria}")
	public String EliminarCategoria(@PathVariable int idCategoria, Model model) {
		

		Response<Categoria> rspta = InterfaceCategoria.eliminarCategoria(idCategoria);
		
		if (rspta.getEstado()) {
			return "redirect:/categoria/lista";
		} else {
		
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());
			return "Categoria/errores";
		}
	
	}

}
