package com.certus.spring.models;

public class ProductoDTO {
    
    private int idProducto;
	private String nombre;
	private String precio;
	private String descripcion;
	private String uriImagen;
    private String nombreFileExtension;
	private String fileBase64;

    public String getUriImagen() {
		return uriImagen;
	}

	public void setUriImagen(String uriImagen) {
		this.uriImagen = uriImagen;
	}

    public String getNombreFileExtension() {
		return nombreFileExtension;
	}
	public void setNombreFileExtension(String nombreFileExtension) {
		this.nombreFileExtension = nombreFileExtension;
	}

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

	public String getFileBase64() {
		return fileBase64;
	}
	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}



}
