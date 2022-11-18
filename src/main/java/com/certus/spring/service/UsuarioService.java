package com.certus.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.certus.spring.models.Personaje;
import com.certus.spring.models.Response;
import com.certus.spring.models.Usuario;
import com.certus.spring.repository.IUsuario;

@Service
@Component("servicio1")
@Primary
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

	
	
	
	

}
