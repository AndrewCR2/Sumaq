package com.certus.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.certus.spring.models.Categoria;
import com.certus.spring.models.Response;
import com.certus.spring.service.ICategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	@Qualifier("ServicioCategoria")
	private ICategoriaService InterfaceCateoria;
	
	@Value("${title.generic}")
	private String titlePage;
	
	@GetMapping({"/","lista","LISTA"})
	public String listaCategoria(Model model) {	
		Response<Categoria> rspta = InterfaceCateoria.listarCategorias();
		
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
		
		return "Categoria/categoria_form";
	}

	@PostMapping("/crear")
	public String crearCategoria(Categoria categoria, BindingResult result, Model model, SessionStatus status){
		
		Response<Categoria> rspta = InterfaceCateoria.crearCategoria(categoria);
		
		if (rspta.getEstado()) {
			status.setComplete();
			return "redirect:/categoria/lista";
		} else {
			
			model.addAttribute("mensaje",rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());
			return "errores";
		}
	}

}
