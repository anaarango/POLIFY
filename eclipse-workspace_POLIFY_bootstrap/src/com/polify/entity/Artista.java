package com.polify.entity;

public class Artista {

	private int id_artista;
	private String nombre_artista;
	private int id_empresa_difusora;
	private String email;
	private boolean canEdit;

	public int getId_artista() {
		return id_artista;
	}

	public void setId_artista(int id_artista) {
		this.id_artista = id_artista;
	}

	public String getNombre_artista() {
		return nombre_artista;
	}

	public void setNombre_artista(String nombre_artista) {
		this.nombre_artista = nombre_artista;
	}

	public int getId_empresa_difusora() {
		return id_empresa_difusora;
	}

	public void setId_empresa_difusora(int id_empresa_difusora) {
		this.id_empresa_difusora = id_empresa_difusora;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	public Artista(int id_artista, String nombre_artista, int id_empresa_difusora, String email) {
		super();
		this.id_artista = id_artista;
		this.nombre_artista = nombre_artista;
		this.id_empresa_difusora = id_empresa_difusora;
		this.email = email;
		
	}

	public Artista() {
		super();
	}

}
