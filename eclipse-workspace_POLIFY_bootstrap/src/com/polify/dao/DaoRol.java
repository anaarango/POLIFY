package com.polify.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.polify.controller.utility.DBUtil;
import com.polify.entity.Rol;

public class DaoRol {

	private Connection conexion;
	
	public DaoRol() {
		conexion = DBUtil.getConexion();
	}
	
	public List<Rol> getAllRoles() {
		
		List<Rol> rolList = new LinkedList<>();
		
		Statement stmt = null;
		ResultSet rs = null;
		try {
		stmt = conexion.createStatement();
		String sql = "SELECT * " + " FROM ROL ORDER BY ID_ROL ASC ";
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id_rol = rs.getInt("ID_ROL");
			String nombre_rol = rs.getString("NOMBRE_ROL");

			Rol r = new Rol( id_rol,nombre_rol);
			rolList.add(r);
		}} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}

				if (rs != null) {
					rs.close();
				}
				

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return rolList;
	}
	
}
