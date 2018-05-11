package com.polify.entity;

import java.util.Date;

public class Informe {

	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getToDate() {
		return toDate;
	}



	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}



	private int id_informe;
	private int id_usuario;
	private String nombre_informe;
	private Date fecha_creacion;
	private String ubicacion_archivo;
	private Date startDate;
	private Date toDate;
	private String OptionSelected;
	
	public String getOptionSelected() {
		return OptionSelected;
	}



	public void setOptionSelected(String optionSelected) {
		OptionSelected = optionSelected;
	}



	public Informe(int id_informe, int id_usuario, String nombre_informe, Date fecha_creacion, String ubicacion_archivo) {
		super();
		this.id_informe = id_informe;
		this.id_usuario = id_usuario;
		this.nombre_informe = nombre_informe;
		this.fecha_creacion = fecha_creacion;
		this.ubicacion_archivo = ubicacion_archivo;
	}



	public Informe() {
		super();
	}



	public int getId_informe() {
		return id_informe;
	}



	public void setId_informe(int id_informe) {
		this.id_informe = id_informe;
	}



	public int getId_usuario() {
		return id_usuario;
	}



	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}



	public String getNombre_informe() {
		return nombre_informe;
	}



	public void setNombre_informe(String nombre_informe) {
		this.nombre_informe = nombre_informe;
	}



	public Date getFecha_creacion() {
		return fecha_creacion;
	}



	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}



	public String getUbicacion_archivo() {
		return ubicacion_archivo;
	}



	public void setUbicacion_archivo(String ubicacion_archivo) {
		this.ubicacion_archivo = ubicacion_archivo;
	}

}
