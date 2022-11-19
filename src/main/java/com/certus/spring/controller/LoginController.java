package com.certus.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("usuario/")
public class LoginController {
	@Value("${title.login}")
	private String titlePage;

	@GetMapping({ "Login", "login" })
	public String Registrese(Model model) {

		String login = "HOLA DE NUEVO!";
		String logeo = "INGRESAR";

		model.addAttribute("TituloPagina", titlePage);
		model.addAttribute("login", login);
		model.addAttribute("logeo", logeo);

		return "login";
	}

}
