package com.certus.spring.service;

import org.springframework.web.multipart.MultipartFile;
import com.certus.spring.models.Productos;
import com.certus.spring.models.Response;

public interface IProductoService {
	public Response<Productos> crearProducto(Productos p, MultipartFile fileRecibido);

	public Response<Productos> editarProducto(Integer ID);

	public Response<Productos> eliminarProducto(Integer ID);

	public Response<Productos> listarProducto();
}
