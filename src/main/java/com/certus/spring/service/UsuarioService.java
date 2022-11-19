package com.certus.spring.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.certus.spring.models.Personaje;
import com.certus.spring.models.Response;
import com.certus.spring.models.Usuario;
import com.certus.spring.repository.IUsuario;


@Component("servicio1")
public class UsuarioService implements IUsuarioService {
	
	@Autowired
	IUsuario usuarioRepository;

	@Override
	public Response<Usuario> crearUsuario(Usuario usuarioRecibido) {
		
		Response<Usuario> response = new Response<>();
		
		try {
			Usuario usuario = usuarioRepository.save(usuarioRecibido);
			response.setEstado(true);
			response.setMensaje("El usuario "+usuario.getNombre()+" a sido creado correctamente");
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje(e.getMessage());
		}
		
		
		return response;
	}

	@Override
	public Response<Usuario> listarUsuario() {
		
		Response<Usuario> response= new Response<>();
		
		try {
			response.setMensaje("Usuarios obtenidos correctamente");
			response.setEstado(true);
			response.setListData((List<Usuario>) usuarioRepository.findAll());
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("No se pudo obtener los usuario");
			response.setMensaje(e.getStackTrace().toString());
		}
		
		return response;
	}
	
	@Override
	public Response<Usuario> editarUsuario(Integer ID) {
		Response<Usuario> response = new Response<>();
		try {
			Optional<Usuario> P = usuarioRepository.findById(ID);
			response.setEstado(true);
			response.setData(P.get());

		} catch (Exception e) {
			response.setEstado(false);
			response.setMensajeError(e.getStackTrace().toString());
		}

		return response;
	}
	
	@Override
	public Response<Usuario> eliminarUsuario(Integer ID) {
		
		Response<Usuario> response = new Response<>();
		try {
			Optional<Usuario> P = usuarioRepository.findById(ID);	
			usuarioRepository.deleteById(ID);
			response.setEstado(true);
			response.setMensaje("El Usuario "+P.get().getNombre()+"ha sido eliminado correctamente");

		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Error al eliminar el Usuario");
			response.setMensajeError(e.getStackTrace().toString());
		}
		
		return response;
	}
	
	
	
	

}
