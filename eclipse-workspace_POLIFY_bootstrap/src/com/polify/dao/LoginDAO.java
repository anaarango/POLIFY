package com.polify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.polify.controller.utility.DBUtil;

public class LoginDAO {

	public static boolean validate(String user, String password) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBUtil.getConexion();
			ps = con.prepareStatement("Select NOMBRE_USUARIO , CONTRASENA from USUARIO where NOMBRE_USUARIO = ? and CONTRASENA = ?");
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//result found, means valid inputs
				System.out.println("correct");
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
			con.close();
		}
		return false;
	}
	
	public static int getRole(String user) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBUtil.getConexion();
			ps = con.prepareStatement("Select ID_ROL from USUARIO where NOMBRE_USUARIO = ?");
			ps.setString(1, user);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//result found, means valid inputs
				System.out.println("correct");
				return rs.getInt("ID_ROL");
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return 0;
		} finally {
			con.close();
		}
		return 0;
	}
	
	public static int getId(String user) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBUtil.getConexion();
			ps = con.prepareStatement("Select ID_USUARIO from USUARIO where NOMBRE_USUARIO = ?");
			ps.setString(1, user);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//result found, means valid inputs
				System.out.println("correct");
				return rs.getInt("ID_USUARIO");
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return 0;
		} finally {
			con.close();
		}
		return 0;
	}
	}