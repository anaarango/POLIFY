package com.polify.entity;

public class Recompensa {

	private int id_recompensa;
	private String nombre_recompensa;
	private int valor_recompensa;
	private boolean canEdit;

	public int getId_recompensa() {
		return id_recompensa;
	}

	public void setId_recompensa(int id_recompensa) {
		this.id_recompensa = id_recompensa;
	}

	public String getNombre_recompensa() {
		return nombre_recompensa;
	}

	public void setNombre_recompensa(String nombre_recompensa) {
		this.nombre_recompensa = nombre_recompensa;
	}

	public int getValor_recompensa() {
		return valor_recompensa;
	}

	public void setValor_recompensa(int valor_recompensa) {
		this.valor_recompensa = valor_recompensa;
	}

	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	public Recompensa(int id_recompensa, String nombre_recompensa, int valor_recompensa) {
		super();
		this.id_recompensa = id_recompensa;
		this.nombre_recompensa = nombre_recompensa;
		this.valor_recompensa = valor_recompensa;
		this.canEdit = true;
	}

	public Recompensa() {
		super();
	}
}