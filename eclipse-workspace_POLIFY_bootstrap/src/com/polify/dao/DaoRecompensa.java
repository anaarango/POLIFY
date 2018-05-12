package com.polify.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.polify.controller.utility.DBUtil;
import com.polify.entity.Recompensa;
import com.polify.entity.Rol;

public class DaoRecompensa {

	private Connection conexion;
	
	public DaoRecompensa() {
		conexion = DBUtil.getConexion();
	}
	
	public List<Recompensa> getAllRecompensas() {
		
		List<Recompensa> recompensaList = new LinkedList<>();
		
		Statement stmt = null;
		ResultSet rs = null;
		try {
		stmt = conexion.createStatement();
		String sql = "SELECT * " + " FROM RECOMPENSA ORDER BY VALOR_RECOMPENSA DESC";
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id_recompensa = rs.getInt("ID_RECOMPENSA");
			String nombre_recompensa = rs.getString("NOMBRE_RECOMPENSA");
			int valor_recompensa = rs.getInt("VALOR_RECOMPENSA");
			Recompensa r = new Recompensa(id_recompensa, nombre_recompensa, valor_recompensa);
			recompensaList.add(r);
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
		
		return recompensaList;
	}
	
}
