package com.polify.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.polify.dao.DaoEmpresa_difusora;
import com.polify.entity.Empresa_difusora;


@ManagedBean
@SessionScoped
public class EmpresaDifusoraBean {

	Empresa_difusora empresa = new Empresa_difusora();
	List<Empresa_difusora> empresaList = new ArrayList();

	public String crearEmpresaForm() {
		DaoEmpresa_difusora daoEmpresa = new DaoEmpresa_difusora();
		try {
			daoEmpresa.save(empresa);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "resultEmpresa";
	}

	public Empresa_difusora getEmpresa() {
		return empresa;
	}

	public void setEmpresa_difusora(Empresa_difusora empresa) {
		this.empresa = empresa;
	}

	public List<Empresa_difusora> getEmpresaList() throws SQLException {
		if (empresaList.isEmpty()) {
			DaoEmpresa_difusora daoEmpresa = new DaoEmpresa_difusora();
			empresaList = daoEmpresa.getAllEmpresas();
		}
		
		return empresaList;
	}

	public void setEmpresaList(List<Empresa_difusora> empresaList) {
		this.empresaList = empresaList;
	}

	public String updateEmpresaForm() throws SQLException {
		DaoEmpresa_difusora daoEmpresa = new DaoEmpresa_difusora();
		daoEmpresa.update(empresa);
		this.empresaList  = new ArrayList();
		

		return "resultEmpresa";
	}

	public String deleteEmpresaForm() throws SQLException {
		DaoEmpresa_difusora daoEmpresa = new DaoEmpresa_difusora();
		daoEmpresa.delete(empresa.getId_empresa_difusora());
		this.empresaList  = new ArrayList();

		return "resultEmpresa";
	}
	

	
}