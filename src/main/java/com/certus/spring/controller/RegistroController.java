package com.certus.spring.controller;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import com.certus.spring.models.Personaje;
import com.certus.spring.models.Response;
import com.certus.spring.models.Usuario;
import com.certus.spring.service.IUsuarioService;

@Controller
@RequestMapping("/")
public class RegistroController {

    @Value("${title.registro}")
	private String titlePage;
    
    @Autowired 
	@Qualifier("servicio1")
	private IUsuarioService InterfaceUsuario;

    @GetMapping({"/registro","Registro"})
	public String Registro(Model model) {	
        String tituloImage = "UNETE A SUMAQ";
        String tituloForm = "Registrate ahora";
		model.addAttribute("TituloPagina",titlePage);
        model.addAttribute("tituloImage",tituloImage);
        model.addAttribute("tituloForm",tituloForm);
        
        
		return "registro";
	}
    
    @GetMapping("/crear")
	public String Formulario(Model model) {
		
    	String tituloImage = "UNETE A SUMAQ";
        String tituloForm = "Registrate ahora";
		model.addAttribute("TituloPagina",titlePage);
        model.addAttribute("tituloImage",tituloImage);
        model.addAttribute("tituloForm",tituloForm);
		
		Usuario usuario = new Usuario();
		
		model.addAttribute("usuario", usuario);
		
		return "registro";
		
	}
    
    
    @PostMapping("/form")
    public String crearUsuario(@Valid Usuario user,
    						   Model model,
    						   BindingResult result,
    						   SessionStatus sStatus){
    	if(result.hasErrors()) {
    		return "registro";
    		
    	}
    	
    	
    	Response<Usuario> respuesta = InterfaceUsuario.crearUsuario(user);
    	
    	sStatus.setComplete();
    	if (respuesta.getEstado()) {
			
    		return "redicted:/login";
    		
		}else {
			model.addAttribute("mensaje", respuesta.getMensaje());
			model.addAttribute("mensajeError", respuesta.getMensaje());
			return "errores";
		}
    	
    	
    	
    }

    
}
