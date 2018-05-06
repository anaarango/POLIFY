package com.polify.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.polify.dao.DaoArtista;
import com.polify.dto.ArtistaEmpresaDTO;
import com.polify.entity.Artista;

@ManagedBean
@RequestScoped
public class ArtistaBean {

	private List<ArtistaEmpresaDTO> artistasEmpresa;
	
	private Artista artista;

	@ManagedProperty("#{artistaEmpresa}")
	private ArtistaEmpresaController artistaController = new ArtistaEmpresaController();

	
	@PostConstruct
	public void init() {

		artistasEmpresa = new ArrayList<>();
		artistaController = new ArtistaEmpresaController();
		artista = new Artista();
		artistasEmpresa = artistaController.consultarArtistasEmpresas();
		

	}

	

	public void createArtistaForm() {
		DaoArtista daoArtista = new DaoArtista();

		daoArtista.save(artista);
		artistasEmpresa = artistaController.consultarArtistasEmpresas();

	}

	public List<ArtistaEmpresaDTO> getArtistasEmpresa() {
		return artistasEmpresa;
	}

	public void setArtistasEmpresa(List<ArtistaEmpresaDTO> artistasEmpresa) {
		this.artistasEmpresa = artistasEmpresa;
	}

	public ArtistaEmpresaController getArtistaController() {
		
		return artistaController;
	}

	public void setArtistaController(ArtistaEmpresaController artistaController) {
		this.artistaController = artistaController;
	}

	public void onRowEdit(RowEditEvent event) {
		ArtistaEmpresaDTO artistaEdicion = ((ArtistaEmpresaDTO) event.getObject());
		Artista art = new Artista();
		art = artistaEdicion.getArtista();
		art.setId_empresa_difusora(artistaEdicion.getEmpresa().getId_empresa_difusora());
		DaoArtista artistaDao = new DaoArtista();
		artistaDao.update(art);

		artistasEmpresa = artistaController.consultarArtistasEmpresas();

		FacesMessage msg = new FacesMessage("Artista Editado",
				((ArtistaEmpresaDTO) event.getObject()).getEmpresa().getId_empresa_difusora().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {

		FacesMessage msg = new FacesMessage("Edit Cancelled",
				((ArtistaEmpresaDTO) event.getObject()).getEmpresa().getId_empresa_difusora().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public void deleteArtistaForm() {
		DaoArtista daoArtista = new DaoArtista();
		daoArtista.delete(artista.getId_artista());
		artistasEmpresa = artistaController.consultarArtistasEmpresas();

	}

	

}