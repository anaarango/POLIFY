package com.polify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.polify.controller.utility.DBUtil;
import com.polify.entity.Informe;

public class DaoInforme {

	private Connection conexion;

	public DaoInforme() {
		conexion = DBUtil.getConexion();
	}

	public List<Informe> getAllInformes() throws SQLException {
		List<Informe> informeList = new LinkedList<>();

		Statement stmt = conexion.createStatement();
		String sql = "SELECT * " + " FROM INFORME ORDER BY ID_INFORME ASC ";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			int id_informe = rs.getInt("ID_INFORME");
			int id_usuario = rs.getInt("ID_USUARIO");
			String nombre_informe = rs.getString("NOMBRE_INFORME");
			Date fecha_creacion = rs.getDate("FECHA_CREACION");
			String ubicacion_archivo = rs.getString("UBICACION_ARCHIVO");

			Informe a = new Informe(id_informe, id_usuario, nombre_informe, fecha_creacion, ubicacion_archivo);
			informeList.add(a);
		}

		return informeList;
	}

	public boolean delete(int id_informe) {
		String sql = "DELETE FROM INFORME WHERE ID_INFORME='" + id_informe + "'";
		PreparedStatement ps;
		try {
			ps = conexion.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean save(Informe informe) throws SQLException {

		try {
			String sql = "INSERT INTO INFORME ( ID_INFORME,ID_USUARIO, NOMBRE_INFORME, FECHA_CREACION,UBICACION_ARCHIVO)"
					+ " VALUES(ARTISTA_ID_SEQUENCE.NEXTVAL," + informe.getId_usuario() + ",'"
					+ informe.getNombre_informe() + "',to_date('" + informe.getFecha_creacion() + "','dd/mm/yy'),'"
					+ informe.getUbicacion_archivo() + "')";

			PreparedStatement ps = conexion.prepareStatement(sql);

			ps.executeQuery(sql);
			System.out.println("Informe");

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// public boolean update(Informe informe) {
	// String sql = "UPDATE INFORME SET NOMBRE_ARTISTA ='" +
	// artista.getNombre_artista()+"', "
	// + "ID_EMPRESA_DIFUSORA = '" + artista.getId_empresa_difusora()+"', "
	// + "EMAIL = '" + artista.getEmail()+"' "
	// + " WHERE ID_ARTISTA = '" + artista.getId_artista() + "'";
	//
	// PreparedStatement ps;
	// try {
	// ps = conexion.prepareStatement(sql);
	// ps.executeUpdate();
	// return true;
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return false;
	// }
}
