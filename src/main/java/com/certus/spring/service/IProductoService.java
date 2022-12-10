package com.certus.spring.service;

import org.springframework.web.multipart.MultipartFile;

import com.certus.spring.models.Producto;
import com.certus.spring.models.ProductoDTO;
import com.certus.spring.models.Response;

public interface IProductoService {
	
	public Response<Producto> crearProducto(Producto p,  MultipartFile fileRecibido);

	public Response<Producto> crearProductoAPI(ProductoDTO P);
	
	public Response<Producto> editarProducto(Integer ID);	
	
	public Response<Producto> eliminarProducto(Integer ID);	
	
	public Response<Producto> listarProducto();

    public Response<Producto> buscarPorQuery(String q);

}
