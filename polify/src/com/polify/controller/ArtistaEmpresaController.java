package com.polify.controller;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.polify.dao.DaoArtista;
import com.polify.dao.DaoEmpresa_difusora;
import com.polify.dto.ArtistaEmpresaDTO;
import com.polify.entity.Artista;
import com.polify.entity.Empresa_difusora;

@ManagedBean(name = "artistaEmpresaService")
@ApplicationScoped
public class ArtistaEmpresaController {

	private List<Artista> artistas;
	private List<Empresa_difusora> empresa;
	List<ArtistaEmpresaDTO> artEmp;

	public List<ArtistaEmpresaDTO> consultarArtistasEmpresas() {
		DaoArtista daoArt = new DaoArtista();

		artEmp = daoArt.getAllArtistaEmpresas();

		return artEmp;

	}

	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}

	public List<Empresa_difusora> getEmpresa() {
		DaoEmpresa_difusora daoEmp = new DaoEmpresa_difusora();

		empresa = daoEmp.getAllEmpresas();

		return empresa;
	}

	public void setEmpresa(List<Empresa_difusora> empresa) {
		this.empresa = empresa;
	}

}
