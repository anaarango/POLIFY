package com.polify.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.polify.dao.DaoArtista;
import com.polify.dao.DaoEmpresa_difusora;
import com.polify.dto.ArtistaEmpresaDTO;
import com.polify.entity.Artista;
import com.polify.entity.Empresa_difusora;

@ManagedBean
@SessionScoped
public class ArtistaBean {

	Artista artista = new Artista();
	List<Artista> artistasList = new ArrayList();
	List<ArtistaEmpresaDTO> artistaEmpresaList = new ArrayList();
	private Empresa_difusora empresaDifusoraSelected = new Empresa_difusora();
	private List<Empresa_difusora> empresaDifusoraList = new ArrayList<Empresa_difusora>();
	private boolean mostrarTabla = false;

	public void init() {
		DaoEmpresa_difusora daoEmpresa = new DaoEmpresa_difusora();
		try {
			empresaDifusoraList = daoEmpresa.getAllEmpresas();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public String createArtistaForm() {
		DaoArtista daoArtista = new DaoArtista();
		try {
			artista.setId_empresa_difusora(this.empresaDifusoraSelected.getId_empresa_difusora());
			daoArtista.save(artista);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.mostrarTabla = true;
		return "crearArtista";
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public List<Artista> getArtistasList() throws SQLException {
		DaoArtista daoArtista = new DaoArtista();
		artistasList = daoArtista.getAllArtist();

		return artistasList;
	}

	public void setArtistasList(List<Artista> artistasList) {
		this.artistasList = artistasList;
	}

	public String updateArtistaForm() throws SQLException {
		DaoArtista daoArtista = new DaoArtista();
		daoArtista.update(artista);
		this.artistasList = new ArrayList();

		return null;
	}

	public String deleteArtistaForm() throws SQLException {
		DaoArtista daoArtista = new DaoArtista();
		daoArtista.delete(artista.getId_artista());
		this.artistasList = new ArrayList();

		return null;
	}

	public Empresa_difusora getEmpresaDifusora(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("no id provided");
		}
		for (Empresa_difusora emp : empresaDifusoraList) {
			if (id.equals(emp.getId_empresa_difusora())) {
				return emp;
			}
		}
		return null;

	}

	public List<Empresa_difusora> getEmpresaDifusoraList() {
		return empresaDifusoraList;
	}

	public void setEmpresaDifusoraList(List<Empresa_difusora> empresaDifusoraList) {
		this.empresaDifusoraList = empresaDifusoraList;
	}

	public Empresa_difusora getEmpresaDifusoraSelected() {
		return empresaDifusoraSelected;
	}

	public void setEmpresaDifusoraSelected(Empresa_difusora empresaDifusoraSelected) {
		this.empresaDifusoraSelected = empresaDifusoraSelected;
	}

	public boolean isMostrarTabla() {
		return mostrarTabla;
	}

	public void setMostrarTabla(boolean mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}

	public List<ArtistaEmpresaDTO> getArtistaEmpresaList() throws SQLException {
		DaoArtista daoArtista = new DaoArtista();
		artistaEmpresaList = daoArtista.getAllArtistaEmpresas();

		return artistaEmpresaList;
	}

	public void setArtistaEmpresaList(List<ArtistaEmpresaDTO> artistaEmpresaList) {
		this.artistaEmpresaList = artistaEmpresaList;
	}

	public String editAction(Artista artista) {

		artista.setCanEdit(true);
		for(ArtistaEmpresaDTO artEmp : this.artistaEmpresaList){
			if(artEmp.getArtista().getId_artista() == artista.getId_artista()){
				artEmp.getArtista().setCanEdit(true);
				break;
			}
		}
		return null;
	}

}