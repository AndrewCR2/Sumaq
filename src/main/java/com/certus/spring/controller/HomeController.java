package com.certus.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.certus.spring.models.Productos;
import com.certus.spring.models.Response;
import com.certus.spring.service.IProductoService;

@Controller
@RequestMapping("/")
@SessionAttributes("producto")
public class HomeController {

	@Value("${title.generic}")
	private String titlePage;

	@Autowired
	@Qualifier("servicio1")
	private IProductoService InterfaceProducto;

	@GetMapping({ "/home", "inicio", "/", "Home", "Inicio" })
	public String HolaMundo(Model model) {
		model.addAttribute("TituloPagina", titlePage);

		return "index";
	}

	@GetMapping("/Productos")
	public String ListarProducto(Model model) {

		model.addAttribute("TituloPagina", titlePage);
		model.addAttribute("titulo", "Productos");
		Response<Productos> rspta = InterfaceProducto.listarProducto();

		if (rspta.getEstado()) {
			model.addAttribute("Mensaje", rspta.getMensaje());
			model.addAttribute("listita", rspta.getListData());
			return "productos";
		} else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());
			return "errores";
		}
	}

	@GetMapping("/crear")
	public String Formulario(Model model) {
		Productos producto = new Productos();

		model.addAttribute("TituloPagina", titlePage);
		model.addAttribute("titulo", " - Crear Producto");
		model.addAttribute("producto", producto);

		return "form-producto";
	}

	@GetMapping("/Editar/{idProducto}")
	public String EditarProducto(@PathVariable int idProducto, Model model) {

		model.addAttribute("TituloPagina", titlePage);

		Response<Productos> rspta = InterfaceProducto.editarProducto(idProducto);

		model.addAttribute("titulo", "Editando el producto " + rspta.getData().getNombre());

		model.addAttribute("producto", rspta.getData());

		return "form-producto";
	}

	@GetMapping("/Elimnar/{idProducto}")
	public String ElimnarProducto(@PathVariable int idProducto, Model model) {

		Response<Productos> rspta = InterfaceProducto.eliminarProducto(idProducto);

		if (rspta.getEstado()) {
			return "redirect:/Productos";
		} else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());

			return "errores";
		}
	}

	@PostMapping("/form_pro")
	public String creaProducto(@Valid Productos Mermelada, BindingResult result, Model model,
			@RequestParam("ImagenDelFormulario") MultipartFile fileRecibido, SessionStatus sStatus) {

		if (result.hasErrors()) {
			return "form-producto";
		}

		Response<Productos> rspta = InterfaceProducto.crearProducto(Mermelada, fileRecibido);

		if (rspta.getEstado()) {

			sStatus.setComplete();
			return "redirect:/Productos";

		} else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());
			return "errores";
		}
	}
}