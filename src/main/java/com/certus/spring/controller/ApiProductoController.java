package com.certus.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.certus.spring.models.Producto;
import com.certus.spring.models.ProductoDTO;
import com.certus.spring.models.Response;
import com.certus.spring.service.IProductoService;

@RestController
@RequestMapping("/api/producto")
public class ApiProductoController {
    
    @Autowired
	@Qualifier("servicioProducto")
	private IProductoService InterfaceProducto;

    @GetMapping("/listar")
    public Response<Producto> listarProducto () {
        Response<Producto> rspta = InterfaceProducto.listarProducto();
        return rspta;
    }

    @PutMapping("/editar/{id}")
	public Response<Producto> editarProducto(@RequestBody ProductoDTO pro,@PathVariable int id){
		Response<Producto> rspta= new Response<>();
		
		Response<Producto> rsptaAux = InterfaceProducto.editarProducto(id);
		
		if (rsptaAux.getEstado()) {
			
			pro.setIdProducto(rsptaAux.getData().getIdProducto());
			rspta = InterfaceProducto.crearProductoAPI(pro);
			
		}else {
			rspta = rsptaAux;
		}
		
		return rspta;
	}

    @PostMapping("/crear")
	public Response<Producto> crearProducto(@RequestBody ProductoDTO pro){
		
		Response<Producto> rspta= InterfaceProducto.crearProductoAPI(pro);
		return rspta;
	}

    @DeleteMapping("/eliminar/{id}")
	public Response<Producto> eliminarProducto (@PathVariable int id){		
		Response<Producto> rspta = InterfaceProducto.eliminarProducto(id);		
		return rspta;
	}

    @GetMapping("/{id}")
	public Response<Producto> productoPorId (@PathVariable int id){		
		Response<Producto> rspta = InterfaceProducto.productoPorId(id);		
		return rspta;
	}

	@GetMapping("/buscar")
    public Response<Producto> buscarPorQuery (@RequestParam(value = "q") String query) {
        Response<Producto> rspta = InterfaceProducto.buscarPorQuery(query);
        return rspta;
    }

}
