package com.polify.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.polify.dao.DaoUsuario;
import com.polify.entity.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioBean {

	Usuario usuario;
	List<Usuario> usuariosList;
	DaoUsuario daoUsuario;
	
	@PostConstruct
	public void init() {

		usuariosList = new ArrayList<>();
		usuario = new Usuario();
		daoUsuario = new DaoUsuario();
		usuariosList = daoUsuario.getAllUsers();
		

	}
	
	public void createUsuarioForm() {
		DaoUsuario daoUsuario = new DaoUsuario();
		String idUsuarioCreado = "";

			daoUsuario.save(usuario);
			this.usuariosList = daoUsuario.getAllUsers();
		
			idUsuarioCreado = Integer.toString(usuariosList.get(usuariosList.size()-1).getId_usuario());
		
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = new FacesMessage("Creacion de Usuario", "La creación del usuario ha sido exitosa y con ID:" + idUsuarioCreado);
		facesContext.addMessage("usuarioForm", facesMessage);

	}
	

	public void onRowEdit(RowEditEvent event) {
		Usuario usuarioEdicion = ((Usuario) event.getObject());
		Usuario usu = new Usuario();
		usu = usuarioEdicion;
		usu.setId_rol(usuarioEdicion.getId_rol());
		DaoUsuario usuarioDao = new DaoUsuario();
		usuarioDao.update(usu);

		FacesMessage msg = new FacesMessage("Usuario Editado exitosamente");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {

		FacesMessage msg = new FacesMessage("Edit Cancelada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Usuario> getusuariosList() {
		return usuariosList;
	}

	public void deleteUsuarioForm() {
		DaoUsuario daoUsuario = new DaoUsuario();
		daoUsuario.delete(usuario.getId_usuario());
		usuariosList = daoUsuario.getAllUsers();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = new FacesMessage("Eliminacion de Usuario", "La eleminación del usuario ha sido exitosa");
		facesContext.addMessage("usuarioForm", facesMessage);

	}
}
