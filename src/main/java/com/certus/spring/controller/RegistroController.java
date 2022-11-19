package com.certus.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RegistroController {

    @Value("${title.registro}")
	private String titlePage;

    @GetMapping({"/registro","Registro"})
	public String Registro(Model model) {	
        String tituloImage = "UNETE A SUMAQ";
        String tituloForm = "Registrate ahora";
		model.addAttribute("TituloPagina",titlePage);
        model.addAttribute("tituloImage",tituloImage);
        model.addAttribute("tituloForm",tituloForm);
		return "registro";
	}
}
