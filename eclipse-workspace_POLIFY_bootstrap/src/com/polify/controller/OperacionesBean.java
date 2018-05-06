package com.polify.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import com.polify.dao.DaoOperaciones;
import com.polify.entity.Operaciones;

@ManagedBean
@SessionScoped
public class OperacionesBean {

	Operaciones operaciones = new Operaciones();
	List<Operaciones> operacionesList = new ArrayList();

	public String crearOperacionesForm() {
		DaoOperaciones daoOperaciones = new DaoOperaciones();
		try {
			daoOperaciones.save(operaciones);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "resultOperaciones";
	}

	public Operaciones getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(Operaciones operaciones) {
		this.operaciones = operaciones;
	}

	public List<Operaciones> getOperacionesList() throws SQLException {
		if (operacionesList.isEmpty()) {
			DaoOperaciones daoOperaciones = new DaoOperaciones();
			operacionesList = daoOperaciones.getAllOperaciones();
		}
		
		return operacionesList;
	}

	public void setOperacionesList(List<Operaciones> operacionesList) {
		this.operacionesList = operacionesList;
	}

	public String updateOperacionesForm() throws SQLException {
		DaoOperaciones daoOperaciones = new DaoOperaciones();
		daoOperaciones.update(operaciones);
		this.operacionesList  = new ArrayList();
		

		return "resultOperaciones";
	}

	public String deleteOperacionesForm() throws SQLException {
		DaoOperaciones daoOperaciones = new DaoOperaciones();
		daoOperaciones.delete(operaciones.getId_operaciones());
		this.operacionesList  = new ArrayList();

		return "resultOperaciones";
	}

	
}