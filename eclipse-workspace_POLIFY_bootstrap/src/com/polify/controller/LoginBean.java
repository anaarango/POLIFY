package com.polify.controller;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.polify.dao.LoginDAO;
import com.polify.entity.SessionUtils;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	private String pwd;
	private String msg;
	private String user;
	private int role;
	private int userId;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	//validate login
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String validateUsernamePassword() throws SQLException {
		boolean valid = LoginDAO.validate(user, pwd);
		if (valid) {
			role = LoginDAO.getRole(user);
			userId = LoginDAO.getId(user);
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			session.setAttribute("role", role);
			session.setAttribute("userId", userId);
			System.out.println("load Index");
			return "index";
		} else {
			System.out.println("test");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			return "login";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
}