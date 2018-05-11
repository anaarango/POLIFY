package com.polify.controller;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.polify.dao.DaoArtista;
import com.polify.dao.DaoEmpresa_difusora;
import com.polify.dao.DaoOperaciones;
import com.polify.dto.OperacionesArtistaEmpresaDTO;
import com.polify.entity.Artista;
import com.polify.entity.Empresa_difusora;
import com.polify.entity.Operaciones;

@ManagedBean(name = "operacionesArtistaEmpresaService")
@ApplicationScoped
public class ArtistaOperacionesController {

	private List<Operaciones> operacion;
	private List<Artista> artistas;
	private List<Empresa_difusora> empresa;
	List<OperacionesArtistaEmpresaDTO> operArtEmp;

	public List<OperacionesArtistaEmpresaDTO> consultarArtistasEmpresas() {
		DaoOperaciones daoOper = new DaoOperaciones();

		operArtEmp = daoOper.getAllOperacionesEmpresasArtistas();

		return operArtEmp;

	}

	public List<Artista> getArtistas() {
		DaoArtista artDao = new DaoArtista();
		artistas = artDao.getAllArtist();
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

	public List<Operaciones> getOperacion() {
		return operacion;
	}

	public void setOperacion(List<Operaciones> operacion) {
		this.operacion = operacion;
	}

}
