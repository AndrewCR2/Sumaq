package com.certus.spring.service;


import com.certus.spring.models.Response;
import com.certus.spring.models.Usuario;

public interface IUsuarioService {
	
	public Response<Usuario> crearUsuario(Usuario u);
	
}
