package com.polify.entity;

import java.sql.Date;

public class Usuario {

	private int id_usuario;
	private int id_rol;
	private String nombre_usuario;
	private String email;
	private Date fecha_creacion;
	private boolean canEdit;
	
	public int getId_usuario() {
		return id_usuario;
	}
	
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public int getId_rol() {
		return id_rol;
	}
	
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}
	
	public Usuario(int id_usuario, int id_rol, String nombre_usuario, String email, Date fecha_creacion) {
		super();
		this.id_usuario = id_usuario;
		this.id_rol = id_rol;
		this.nombre_usuario = nombre_usuario;
		this.email = email;
		this.fecha_creacion = fecha_creacion;
		this.canEdit = true;
	}
	
	public Usuario() {
		super();
	}
	
}
