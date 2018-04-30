package com.polify.entity;

public class Empresa_difusora {
	
	private Integer id_empresa_difusora;
	private String nombre_empresa;
	private String email;
	private int valor_x_operacion;
	private boolean canEdit;
	
	
	public String getNombre_empresa() {
		return nombre_empresa;
	}
	public void setNombre_empresa(String nombre_empresa) {
		this.nombre_empresa = nombre_empresa;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getValor_x_operacion() {
		return valor_x_operacion;
	}
	public void setValor_x_operacion(int valor_x_operacion) {
		this.valor_x_operacion = valor_x_operacion;
	}
	public boolean isCanEdit() {
		return canEdit;
	}
	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}
	public Empresa_difusora(int id_empresa_difusora, String nombre_empresa, String email, int valor_x_operacion) {
		super();
		this.id_empresa_difusora = id_empresa_difusora;
		this.nombre_empresa = nombre_empresa;
		this.email = email;
		this.valor_x_operacion = valor_x_operacion;
		this.canEdit = true;
	}
	public Empresa_difusora() {
		super();
	}
	public Integer getId_empresa_difusora() {
		return id_empresa_difusora;
	}
	public void setId_empresa_difusora(Integer id_empresa_difusora) {
		this.id_empresa_difusora = id_empresa_difusora;
	}
	
	
	

}
