package com.polify.entity;

import java.util.Date;

public class Operaciones {

	private int id_operaciones;
	private int id_usuario;
	private int id_artista;
	private int id_empresa_difusora;
	private int numero_operaciones;
	private Date fecha_inicial;
	private Date fecha_final;
	private boolean canEdit;

	//Datos para reporte por totales de artista
		private int total;
		private String nombre_artista;
		private String nombre_empresa;
		private int rankNumber;
	
	public int getId_operaciones() {
		return id_operaciones;
	}

	public void setId_operaciones(int id_operaciones) {
		this.id_operaciones = id_operaciones;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getId_artista() {
		return id_artista;
	}

	public void setId_artista(int id_artista) {
		this.id_artista = id_artista;
	}

	public int getId_empresa_difusora() {
		return id_empresa_difusora;
	}

	public void setId_empresa_difusora(int id_empresa_difusora) {
		this.id_empresa_difusora = id_empresa_difusora;
	}

	public int getNumero_operaciones() {
		return numero_operaciones;
	}

	public void setNumero_operaciones(int numero_operaciones) {
		this.numero_operaciones = numero_operaciones;
	}

	public Date getFecha_inicial() {
		return fecha_inicial;
	}

	public void setFecha_inicial(Date fecha_inicial) {
		this.fecha_inicial = fecha_inicial;
	}

	public Date getFecha_final() {
		return fecha_final;
	}

	public void setFecha_final(Date fecha_final) {
		this.fecha_final = fecha_final;
	}

	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	public Operaciones(int id_operaciones, int id_usuario, int id_artista, int id_empresa_difusora,
			int numero_operaciones, Date fecha_inicial, Date fecha_final) {
		super();
		this.id_operaciones = id_operaciones;
		this.id_usuario = id_usuario;
		this.id_artista = id_artista;
		this.id_empresa_difusora = id_empresa_difusora;
		this.numero_operaciones = numero_operaciones;
		this.fecha_inicial = fecha_inicial;
		this.fecha_final = fecha_final;
		this.canEdit = true;
	}

	public Operaciones() {
		super();
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
	public String getNombre_artista() {
		return nombre_artista;
	}

	public void setNombre_artista(String nombre_artista) {
		this.nombre_artista = nombre_artista;
	}

	public String getNombre_empresa() {
		return nombre_empresa;
	}

	public void setNombre_empresa(String nombre_empresa) {
		this.nombre_empresa = nombre_empresa;
	}

	
	public int getRankNumber() {
		return rankNumber;
	}

	public void setRankNumber(int rankNumber) {
		this.rankNumber = rankNumber;
	}

	public Operaciones(int id_artista, String nombre_artista,
			String nombre_empresa, int total) {
		super();
		this.id_artista = id_artista;
		this.nombre_artista = nombre_artista;
		this.nombre_empresa = nombre_empresa;
		this.total = total;
		this.canEdit = true;
	}

	public Operaciones(int id_empresa_difusora, String nombre_artista,String nombre_empresa, int numero_operaciones,int total) {
		super();
		this.id_empresa_difusora = id_empresa_difusora;
		this.nombre_artista = nombre_artista;
		this.nombre_empresa = nombre_empresa;
		this.numero_operaciones = numero_operaciones;
		this.total = total;
		this.canEdit = true;
	}
	
	public Operaciones(String nombre_artista,String nombre_empresa,int total,int rankingNumber) {
		super();
		this.nombre_artista = nombre_artista;
		this.nombre_empresa = nombre_empresa;
		this.total = total;
		this.rankNumber = rankingNumber;
		this.canEdit = true;
	}

}
