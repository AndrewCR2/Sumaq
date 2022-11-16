package com.certus.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Value("${title.generic}")
	private String titlePage;
	
	@GetMapping({"/home","inicio","/","Home","Inicio"})
	public String HolaMundo(Model model) {	
		
		//Personaje personaje1  =new  Personaje();
		
		/*personaje1.setNombre("Luffy");
		personaje1.setAlias(",Luffy Alias");
		personaje1.setTipoFruta("Luffy Tipo Fruta");
		personaje1.setHabilidad("Luffy Habilidad");	
		personaje1.setTripulacion("Luffy Tripulacion");
		personaje1.setRecompensa("13245");*/
		
		
		
	/*	List<Personaje> lista = new ArrayList<>();
		lista.add(personaje1);*/
	     
		List<String> menu = new ArrayList<>();
		
		 menu.add("Nosotros");
		 menu.add("Ofecemos");
		 menu.add("Productos");
		 menu.add("Contactenos");
		 menu.add("Pedir Aqui");
		 menu.add("Login");
		
	   model.addAttribute("TituloPagina",titlePage);
		//model.addAttribute("titulo","Seccion J98");
	   model.addAttribute("menu",menu);
		
		
		return "index";
	}

}
