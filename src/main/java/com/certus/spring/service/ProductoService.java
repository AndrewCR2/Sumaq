package com.certus.spring.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.certus.spring.config.MvcConfig;
import com.certus.spring.models.Producto;
import com.certus.spring.models.Response;
import com.certus.spring.repository.ProductoDAO;


@Component("servicioProducto")
public class ProductoService implements IProductoService {
	
	@Autowired
	ProductoDAO productoRepository;
	
	@Autowired
	MvcConfig config;

	@Override
	public Response<Producto> crearProducto(Producto p,  MultipartFile fileRecibido) {		
		
		Response<Producto> response = new Response<>();		
		try {
			
			if (!fileRecibido.isEmpty()) {
				 
				try {
					
					if (p.getUriImagen() != null) {
						
						Path enlaceGuardado = Paths.get(config.pathImage()+"\\"+p.getUriImagen());
						File fileEliminar = enlaceGuardado.toFile();
						
						if (fileEliminar.exists()) {
							fileEliminar.delete();
						}
					}
					
					String NewExtention = StringUtils.getFilenameExtension(fileRecibido.getOriginalFilename());
					String newName = UUID.randomUUID().toString() + "." + NewExtention;
					
					
					byte [] bytesFile = fileRecibido.getBytes();
					Path enlaceAGuardar = Paths.get(config.pathImage()+"//"+newName);
					Files.write(enlaceAGuardar, bytesFile);
					
					p.setUriImagen(newName);
					
				} catch (IOException e) {
					response.setEstado(false);
					response.setMensaje("Error al crear el productos "+p.getNombre());
					response.setMensajeError(e.getStackTrace().toString());	
					return response;
				}
				
			}
			
			
			
			Producto producto = productoRepository.save(p);			
			response.setEstado(true);
			response.setMensaje("El Producto "+producto.getNombre()+" ha sido creado correctamente");
			
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Error al crear el producto "+p.getNombre());
			response.setMensajeError(e.getStackTrace().toString());
		}		
		return response;
	}

	
	@Override
	public Response<Producto> editarProducto(Integer ID) {

		Response<Producto> response = new Response<>();
		
		try {
			Optional<Producto> p = productoRepository.findById(ID);
			response.setEstado(true);
			response.setData(p.get());
			
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensajeError(e.getStackTrace().toString());
		}
		
		return response;
	}
	
	
	@Override
	public Response<Producto> eliminarProducto(Integer ID) {
		
		Response<Producto> response = new Response<>();
		
		try {
			Optional<Producto> p = productoRepository.findById(ID);
			
			
			Path rutaElimarFile = Paths.get(config.pathImage()+"/"+p.get().getUriImagen());	
			File fileEliminar = rutaElimarFile.toFile();
			if (fileEliminar.exists()) {
				fileEliminar.delete();
			}
			
			productoRepository.deleteById(ID);
			response.setEstado(true);
			response.setMensaje("El producto "+p.get().getNombre()+" ha sido eliminado");
			
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Error al eliminar el producto");
			response.setMensajeError(e.getStackTrace().toString());
		}
				
		return response;
	}




	@Override
	public Response<Producto> listarProducto() {
		
		Response<Producto> response = new Response<>();
		
		try {
			
			response.setListData((List<Producto>) productoRepository.findAll());
			response.setEstado(true);
			response.setMensaje("Productos obtenidos correctamente");
			
		} catch (Exception e) {			
			response.setEstado(false);
			response.setMensaje("Error al obtener los productos");
			response.setMensajeError(e.getStackTrace().toString());
		}
		return response;
	}
}
