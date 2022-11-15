package com.certus.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Value("${title.generic}")
	private String titlePage;
	
	@GetMapping({"/","lista","LISTA"})
	public String listaCategoria(Model model) {	
	     
	   model.addAttribute("TituloPagina",titlePage);
	 
		return "Categoria/categoria_lista";
	}
	
	@GetMapping("/form")
	public String formCategoria(Model model) {
		
		return "Categoria/categoria_form";
	}

}
