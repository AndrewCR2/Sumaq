package com.certus.spring.models;

import java.util.List;

public class Response<T> {
	
	private boolean estado;
	private String mensaje;
	private String mensajeError;
	private T data; //conjunto de data
	private List<T> ListData; //lista de data
	
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public List<T> getListData() {
		return ListData;
	}
	public void setListData(List<T> listData) {
		ListData = listData;
	}
	public String getMensajeError() {
		return mensajeError;
	}
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	

}
