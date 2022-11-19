package com.certus.spring.service;


import com.certus.spring.models.Response;
import com.certus.spring.models.Usuario;

public interface IUsuarioService {
	
	public Response<Usuario> crearUsuario(Usuario u);
	public Response<Usuario> listarUsuario();
	public Response<Usuario> editarUsuario(Integer ID);
	public Response<Usuario> eliminarUsuario(Integer ID);
	
}
