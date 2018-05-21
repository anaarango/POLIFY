package com.polify.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

		if (validarCampos()) {

			if (!daoArtista.consultarArtistaEmail(artista)) {

				daoArtista.save(artista);
				artistasEmpresa = artistaController.consultarArtistasEmpresas();

				String idArtistaCreado = Integer
						.toString(artistasEmpresa.get(artistasEmpresa.size() - 1).getArtista().getId_artista());

				FacesContext facesContext = FacesContext.getCurrentInstance();
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Creacion de Artista",
						"La creación del artista ha sido exitosa y con ID:" + idArtistaCreado);
				facesContext.addMessage("artistaForm", facesMessage);
			} else {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Creacion de Artista",
						"El artista que esta intentando ingresar ya existe asociado al email:" + artista.getEmail());
				facesContext.addMessage("artistaForm", facesMessage);
			}

		}
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

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Artista Editado exitosamente",
				"El Artista con ID: " + +art.getId_artista() + " ha sido actualizado de forma exitosa");
		FacesContext.getCurrentInstance().addMessage("empresaForm", msg);
	}

	public void onRowCancel(RowEditEvent event) {

		FacesMessage msg = new FacesMessage("Edit Cancelada",
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

		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = new FacesMessage("Eliminación de Artista",
				"La eleminación del artista ha sido exitosa");
		facesContext.addMessage("artistaForm", facesMessage);

	}

	private boolean validarCampos() {

		if (artista.getNombre_artista() != null && artista.getNombre_artista().equals("")) {

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"El campo nombre artista no puede ser vacio ", " Error en el campo NOMBRE ARTISTA");
			FacesContext.getCurrentInstance().addMessage("artistaForm", msg);
			return false;

		}

		if (artista.getEmail() != null) {

			if (artista.getEmail().equals("")) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"El campo email artista no puede ser vacio ", " Error en el campo EMAIL ARTISTA");
				FacesContext.getCurrentInstance().addMessage("artistaForm", msg);
				return false;
			} else {
				String emailIngresado = artista.getEmail();

				Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
						Pattern.CASE_INSENSITIVE);

				Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailIngresado);
				if (!matcher.find()) {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email invalido",
							"El email: " + emailIngresado + " no es un email valido");
					FacesContext.getCurrentInstance().addMessage("artistaForm", msg);
					return false;
				}
			}

		}

		return true;
	}

}