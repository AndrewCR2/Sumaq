package com.certus.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CategoriaController {
	
	@Value("${title.generic}")
	private String titlePage;
	
	@GetMapping({"/categoria","/CATEGORIA"})
	public String ListaCategoria(Model model) {	
	     
	   model.addAttribute("TituloPagina",titlePage);
	 
		return "categoria_lista";
	}

}
