package com.certus.spring.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
 

@Entity
@Table(name = "producto")
public class Productos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;
	
	@NotEmpty(message = "Indicar la nombre")
	private String nombre;
	
	@NotEmpty(message = "Indicar la precio")
	private String precio;
	
	private String descripcion;
	
	private String uriImagen;
	
	

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUriImagen() {
		return uriImagen;
	}

	public void setUriImagen(String uriImagen) {
		this.uriImagen = uriImagen;
	}

}
