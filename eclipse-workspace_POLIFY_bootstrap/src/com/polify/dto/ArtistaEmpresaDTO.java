package com.polify.dto;

import com.polify.entity.Artista;
import com.polify.entity.Empresa_difusora;

public class ArtistaEmpresaDTO {
	
	private Artista artista = new Artista();
	private Empresa_difusora empresa = new Empresa_difusora();
	
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
	
	
}
