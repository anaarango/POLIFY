package com.polify.dto;

import com.polify.entity.Artista;
import com.polify.entity.Empresa_difusora;
import com.polify.entity.Operaciones;


public class OperacionesArtistaEmpresaDTO {
	
	private Artista artista = new Artista();
	private Empresa_difusora empresa = new Empresa_difusora();
	private Operaciones operaciones = new Operaciones();
	
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	public Empresa_difusora getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa_difusora empresa) {
		this.empresa = empresa;
	}
	public Operaciones getOperaciones() {
		return operaciones;
	}
	public void setOperaciones(Operaciones operaciones) {
		this.operaciones = operaciones;
	}
	
	
}
