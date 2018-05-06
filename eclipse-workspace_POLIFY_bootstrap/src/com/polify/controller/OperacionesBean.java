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

import com.polify.dao.DaoOperaciones;
import com.polify.dto.OperacionesArtistaEmpresaDTO;
import com.polify.entity.Operaciones;

@ManagedBean
@RequestScoped
public class OperacionesBean {

	Operaciones operaciones = new Operaciones();
	List<OperacionesArtistaEmpresaDTO> operacionesList = new ArrayList<OperacionesArtistaEmpresaDTO>();

	@ManagedProperty("#{operacionesArtistaEmpresa}")
	private ArtistaOperacionesController operacionesController = new ArtistaOperacionesController();

	@PostConstruct
	public void init() {
		operacionesController = new ArtistaOperacionesController();
		operaciones = new Operaciones();
		operacionesList = operacionesController.consultarArtistasEmpresas();

	}

	public void crearOperacionesForm() {
		DaoOperaciones daoOperaciones = new DaoOperaciones();

		operaciones.setId_usuario(12);

		if (daoOperaciones.save(operaciones)) {
			operacionesList = operacionesController.consultarArtistasEmpresas();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Operación creada exitosamente",
					"La operacion " + "  con ID: "
							+ operacionesList.get(operacionesList.size()-1).getOperaciones().getId_operaciones()
							+ " ha sido creado de forma exitosa");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			operacionesList = operacionesController.consultarArtistasEmpresas();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear la operación",
					" No se ha podido crear la operacion");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		

	}

	public Operaciones getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(Operaciones operaciones) {
		this.operaciones = operaciones;
	}

	public List<OperacionesArtistaEmpresaDTO> getOperacionesList() {

		return operacionesList;
	}

	public void setOperacionesList(List<OperacionesArtistaEmpresaDTO> operacionesList) {
		this.operacionesList = operacionesList;
	}

	public void updateOperacionesForm() {
		DaoOperaciones daoOperaciones = new DaoOperaciones();
		daoOperaciones.update(operaciones);
		this.operacionesList = this.operacionesController.consultarArtistasEmpresas();

	}

	public void deleteOperacionesForm() {
		DaoOperaciones daoOperaciones = new DaoOperaciones();
		if (daoOperaciones.delete(operaciones.getId_operaciones())) {
			FacesMessage msg = new FacesMessage("Operación Eliminada exitosamente",
					"La operacióncon ID: "
							+ operaciones.getId_operaciones() + " ha sido eliminada de forma exitosa");
			FacesContext.getCurrentInstance().addMessage("empresaForm", msg);
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar la empresa",
					"La opeacion con ID: "
							+ operaciones.getId_operaciones()
							+ " No ha sido elminada de forma exitosa, Existen registros enlazados esta operación");
			FacesContext.getCurrentInstance().addMessage("empresaForm", msg);
		}

		this.operacionesList = this.operacionesController.consultarArtistasEmpresas();

	}

	public void onRowEdit(RowEditEvent event) {
		OperacionesArtistaEmpresaDTO operacionEdicion = ((OperacionesArtistaEmpresaDTO) event.getObject());
		Operaciones oper = new Operaciones();
		oper = operacionEdicion.getOperaciones();
		oper.setId_empresa_difusora(operacionEdicion.getEmpresa().getId_empresa_difusora());
		oper.setId_artista(operacionEdicion.getArtista().getId_artista());
		DaoOperaciones daoOper = new DaoOperaciones();
		daoOper.update(oper);

		this.operacionesList = this.operacionesController.consultarArtistasEmpresas();

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Operacion Editada exitosamente",
				"La operación con ID: "
						+ + oper.getId_operaciones()
						+ " ha sido actualizado de forma exitosa");				
		FacesContext.getCurrentInstance().addMessage("empresaForm", msg);
	}

	public void onRowCancel(RowEditEvent event) {
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"La edición ha sido cancelada",
				((OperacionesArtistaEmpresaDTO) event.getObject()).getEmpresa().getId_empresa_difusora().toString());
		FacesContext.getCurrentInstance().addMessage("empresaForm", msg);
	}

	public ArtistaOperacionesController getOperacionesController() {
		return operacionesController;
	}

	public void setOperacionesController(ArtistaOperacionesController operacionesController) {
		this.operacionesController = operacionesController;
	}

}