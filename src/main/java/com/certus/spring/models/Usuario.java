package com.certus.spring.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

@Entity //MAPEAR
@Table(name= "usuario")//ASIGNAR NOMBRE A LA TABLA
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE.IDENTITY)
	private int idUsuario;
	
	@NotEmpty(message = "No ingresaste tu nombre")
	@Size(max= 12, message ="El nombre es muy largo, prueba ingresando solo un nombre")
	private String nombre;
	
	@NotEmpty(message = "No ingresaste tus apellidos")
	private String apellidos;
	
	@Size(max=35,message="La dirección es muy larga")
	@NotEmpty(message = "Inresa una dirección")
	private String direccion;
	
	@Email(message ="El formato ingresado no es el correcto")
	@NotEmpty(message = "No ingresaste tu email")
	private String email;
	
	@NumberFormat()
	@Size(max=9,message="El número telefonico escede los 9 digitos")
	private String telefono;
	
	
	@Size(min=8, message = "Usa minimo 8 caracteres")
	@NotEmpty(message = "No ingresaste una contraseña")
	private String contraseña;
	
	@Size(min=8, message = "Usa minimo 8 caracteres")
	@NotEmpty(message = "No confirmaste tu contraseña")
	private String confirmarContraseña;

	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getConfirmarContraseña() {
		return confirmarContraseña;
	}

	public void setConfirmarContraseña(String confirmarContraseña) {
		this.confirmarContraseña = confirmarContraseña;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	
	
	

}
