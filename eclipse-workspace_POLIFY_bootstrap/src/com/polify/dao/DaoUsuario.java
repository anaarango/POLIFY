package com.polify.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.polify.controller.utility.DBUtil;
import com.polify.entity.Usuario;

public class DaoUsuario {

	private Connection conexion;

	public DaoUsuario() {
		conexion = DBUtil.getConexion();
	}

	public List<Usuario> getAllUsers() {

		List<Usuario> usersList = new LinkedList<>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conexion.createStatement();
			String sql = "SELECT * " + " FROM USUARIO ORDER BY ID_USUARIO ASC ";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id_usuario = rs.getInt("ID_USUARIO");
				int id_rol = rs.getInt("ID_ROL");
				String nombre_usuario = rs.getString("NOMBRE_USUARIO");
				String email = rs.getString("EMAIL");
				Date fecha_creacion = rs.getDate("FECHA_CREACION");

				Usuario u = new Usuario(id_usuario, id_rol, nombre_usuario, email, fecha_creacion);
				usersList.add(u);
			}
		} catch (SQLException e) {

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

		return usersList;
	}

	public boolean save(Usuario usuario) {
		PreparedStatement ps = null;
		try {
			String sql = "INSERT INTO USUARIO ( ID_USUARIO , ID_ROL, NOMBRE_USUARIO, EMAIL, FECHA_CREACION)"
					+ " VALUES(ID_USUARIO_SEQUENCE.NEXTVAL,'" + usuario.getId_rol() + "','"
					+ usuario.getNombre_usuario() + "','" + usuario.getEmail() + "',SYSDATE)";

			ps = conexion.prepareStatement(sql);

			ps.executeUpdate();
			System.out.println("usuario");

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	public boolean update(Usuario usuario) {
		PreparedStatement ps = null;
		String sql = "UPDATE USUARIO SET ID_ROL = ? , NOMBRE_USUARIO = ?, EMAIL = ? , "
				+ "FECHA_CREACION = ?  WHERE ID_USUARIO = ? ";

		try {
			ps = conexion.prepareStatement(sql);
			ps.setInt(1, usuario.getId_rol());
			ps.setString(2, usuario.getNombre_usuario());
			ps.setString(3, usuario.getEmail());
			java.sql.Timestamp fi = new java.sql.Timestamp(usuario.getFecha_creacion().getTime());
			ps.setTimestamp(4, fi);
			ps.setInt(5, usuario.getId_usuario());
			ps.executeUpdate();
			
			System.out.println("update usuario");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	public boolean delete(int id_usuario) {
		String sql = "DELETE FROM USUARIO WHERE ID_USUARIO = '" + id_usuario + "'";
		PreparedStatement ps = null;
		try {
			ps = conexion.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
}
