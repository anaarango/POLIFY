package com.polify.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.polify.dao.DaoRol;
import com.polify.entity.Rol;


@ManagedBean
@SessionScoped
public class RolBean {

	Rol rol;
	List<Rol> rolList;
	DaoRol daoRol;
	
	@PostConstruct
	public void init() {

		rolList = new ArrayList<>();
		rol = new Rol();
		daoRol = new DaoRol();
		rolList = daoRol.getAllRoles();
		

	}
	
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	public List<Rol> getRolesList() {
		return rolList;
	}


}
