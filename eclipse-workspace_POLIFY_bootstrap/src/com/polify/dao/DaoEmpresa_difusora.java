package com.polify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.polify.controller.utility.DBUtil;
import com.polify.entity.Artista;
import com.polify.entity.Empresa_difusora;

public class DaoEmpresa_difusora {

	private Connection conexion;

	public DaoEmpresa_difusora() {
		super();
		conexion = DBUtil.getConexion();
	}

	public List<Empresa_difusora> getAllEmpresas() {
		List<Empresa_difusora> empresaList = new LinkedList<>();

		ResultSet rs = null;
		Statement stmt = null;

		try {

			stmt = conexion.createStatement();
			String sql = "SELECT * " + " FROM EMPRESA_DIFUSORA ORDER BY ID_EMPRESA_DIFUSORA ASC ";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				int id_empresa = rs.getInt("ID_EMPRESA_DIFUSORA");
				String nombre = rs.getString("NOMBRE_EMPRESA");
				String email = rs.getString("EMAIL");
				int valor_operacion = rs.getInt("VALOR_X_OPERACION");

				Empresa_difusora em = new Empresa_difusora(id_empresa, nombre, email, valor_operacion);
				empresaList.add(em);
			}
		} catch (Exception e) {

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

		return empresaList;
	}

	public boolean delete(int id_empresa_difusora) {
		String sql = "DELETE FROM EMPRESA_DIFUSORA WHERE ID_EMPRESA_DIFUSORA='" + id_empresa_difusora + "'";
		PreparedStatement ps = null;
		try {

			ps = conexion.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {

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

	public boolean save(Empresa_difusora empresa) {
		PreparedStatement ps = null;

		try {

			String sql = "INSERT INTO EMPRESA_DIFUSORA ( ID_EMPRESA_DIFUSORA, NOMBRE_EMPRESA, EMAIL, VALOR_X_OPERACION)"
					+ " VALUES(ID_EMPRESA_DIFUSORA_SEQUENCE.NEXTVAL,'" + empresa.getNombre_empresa() + "','"
					+ empresa.getEmail() + "','" + empresa.getValor_x_operacion() + "' )";

			ps = conexion.prepareStatement(sql);

			ps.executeUpdate();
			System.out.println("empresa");

			return true;
		} catch (Exception e) {
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

	public boolean update(Empresa_difusora empresa) {
		String sql = "UPDATE EMPRESA_DIFUSORA SET NOMBRE_EMPRESA ='" + empresa.getNombre_empresa() + "' , "
				+ "EMAIL = '" + empresa.getEmail() + "', " + "VALOR_X_OPERACION = '" + empresa.getValor_x_operacion()
				+ "'" + " WHERE ID_EMPRESA_DIFUSORA = '" + empresa.getId_empresa_difusora() + "'";

		PreparedStatement ps = null;
		try {

			ps = conexion.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {

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

	public List<Empresa_difusora> getEmpresaByArtistaID(int artistaId) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Empresa_difusora> empresaList = new LinkedList<>();

		try {

			String sql = "select * from empresa_difusora emp, "
					+ "artista art where emp.id_empresa_difusora = art.id_empresa_difusora "
					+ "and id_artista = ?";

			ps = conexion.prepareStatement(sql);
			ps.setInt(1, artistaId);

			// execute insert SQL stetement

			rs = ps.executeQuery();
			while (rs.next()) {
				int id_empresa = rs.getInt(1);
				String nombre_empresa = rs.getString(2);
				String email_empresa = rs.getString(3);
				int valor_operacion = rs.getInt(4);
				
				

				Empresa_difusora empresa = new Empresa_difusora(id_empresa, nombre_empresa, email_empresa, valor_operacion);

				empresaList.add(empresa);
			}

		} catch (Exception e) {
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

		return empresaList;
	}

}
