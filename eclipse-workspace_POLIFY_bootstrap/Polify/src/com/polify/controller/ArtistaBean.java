package com.polify.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.polify.dao.DaoArtista;
import com.polify.entity.Artista;

@ManagedBean
@SessionScoped
public class ArtistaBean {

	Artista artista = new Artista();
	List<Artista> artistasList = new ArrayList();

	public String createArtistaForm() {
		DaoArtista daoArtista = new DaoArtista();
		try {
			daoArtista.save(artista);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "resultArtista";
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public List<Artista> getArtistasList() throws SQLException {
		if (artistasList.isEmpty()) {
			DaoArtista daoArtista = new DaoArtista();
			artistasList = daoArtista.getAllArtist();
		}
		
		return artistasList;
	}

	public void setArtistasList(List<Artista> artistasList) {
		this.artistasList = artistasList;
	}

	public String updateArtistaForm() throws SQLException {
		DaoArtista daoArtista = new DaoArtista();
		daoArtista.update(artista);
		this.artistasList  = new ArrayList();
		

		return "resultArtista";
	}

	public String deleteArtistaForm() throws SQLException {
		DaoArtista daoArtista = new DaoArtista();
		daoArtista.delete(artista.getId_artista());
		this.artistasList  = new ArrayList();

		return "resultArtista";
	}

	
}