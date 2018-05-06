package com.polify.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.polify.dao.DaoEmpresa_difusora;
import com.polify.entity.Empresa_difusora;

@ManagedBean
@RequestScoped
public class EmpresaDifusoraBean {

	Empresa_difusora empresa = new Empresa_difusora();
	List<Empresa_difusora> empresaList = new ArrayList<Empresa_difusora>();

	public String crearEmpresaForm() {
		DaoEmpresa_difusora daoEmpresa = new DaoEmpresa_difusora();

		if (daoEmpresa.save(empresa)) {
			empresaList = this.getEmpresaList();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Empresa creada exitosamente",
					"La empresa: " + empresa.getNombre_empresa() + " y con ID: "
							+ empresaList.get(empresaList.size() - 1).getId_empresa_difusora().toString()
							+ " ha sido creado de forma exitosa");
			FacesContext.getCurrentInstance().addMessage("empresaForm", msg);
		} else {
			empresaList = this.getEmpresaList();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al crear la empresa",
					"La empresa: " + empresa.getNombre_empresa()

							+ " No se ha podido crear");
			FacesContext.getCurrentInstance().addMessage("empresaForm", msg);
		}

		return "";

	}

	@PostConstruct
	public void init() {
		empresa = new Empresa_difusora();
		empresaList = this.getEmpresaList();

	}

	public Empresa_difusora getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa_difusora empresa) {
		this.empresa = empresa;
	}

	public List<Empresa_difusora> getEmpresaList() {

		DaoEmpresa_difusora daoEmpresa = new DaoEmpresa_difusora();
		empresaList = daoEmpresa.getAllEmpresas();

		return empresaList;
	}
	
	

	public void setEmpresaList(List<Empresa_difusora> empresaList) {
		this.empresaList = empresaList;
	}

	public void updateEmpresaForm() {
		DaoEmpresa_difusora daoEmpresa = new DaoEmpresa_difusora();
		daoEmpresa.update(empresa);
		this.empresaList = new ArrayList<Empresa_difusora>();

	}

	public void deleteEmpresaForm() {
		DaoEmpresa_difusora daoEmpresa = new DaoEmpresa_difusora();
		if (daoEmpresa.delete(empresa.getId_empresa_difusora())) {
			FacesMessage msg = new FacesMessage("Empresa Eliminada exitosamente",
					"La empresa: " + empresa.getNombre_empresa() + " y con ID: "
							+ empresa.getId_empresa_difusora().toString() + " ha sido eliminada de forma exitosa");
			FacesContext.getCurrentInstance().addMessage("empresaForm", msg);
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al eliminar la empresa",
					"La empresa: " + empresa.getNombre_empresa() + " y con ID: "
							+ empresa.getId_empresa_difusora().toString()
							+ " No ha sido elminada de forma exitosa, Existen registros enlazados esta empresa");
			FacesContext.getCurrentInstance().addMessage("empresaForm", msg);
		}
		this.empresaList = this.getEmpresaList();

	}

	public void onRowEdit(RowEditEvent event) {
		Empresa_difusora empresaEdicion = ((Empresa_difusora) event.getObject());

		DaoEmpresa_difusora empresaDao = new DaoEmpresa_difusora();
		empresaDao.update(empresaEdicion);

		empresaList = this.getEmpresaList();

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Empresa Editada exitosamente",
				"La empresa: " + empresaEdicion.getNombre_empresa() + " y con ID: "
						+ ((Empresa_difusora) event.getObject()).getId_empresa_difusora().toString()
						+ " ha sido actualizado de forma exitosa");
		FacesContext.getCurrentInstance().addMessage("empresaForm", msg);
	}

	public void onRowCancel(RowEditEvent event) {

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"La edición ha sido cancelada",
				((Empresa_difusora) event.getObject()).getId_empresa_difusora().toString());
		FacesContext.getCurrentInstance().addMessage("empresaForm", msg);
	}

}