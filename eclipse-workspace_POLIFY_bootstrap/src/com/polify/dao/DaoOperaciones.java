package com.polify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import com.polify.controller.utility.DBUtil;
import com.polify.entity.Operaciones;

public class DaoOperaciones {

	private Connection conexion;
	
	

	public DaoOperaciones() {
		super();
		conexion = DBUtil.getConexion();
	}

	public List<Operaciones> getAllOperaciones() {
		List<Operaciones> operacionesList = new LinkedList<>();
		ResultSet rs = null;
		Statement stmt = null;

		try {
			
			stmt = conexion.createStatement();
			String sql = "SELECT * " + " FROM OPERACIONES";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id_operaciones = rs.getInt("ID_OPERACIONES");
				int id_usuario = rs.getInt("ID_USUARIO");
				int id_artista = rs.getInt("ID_ARTISTA");
				int id_empresa_difusora = rs.getInt("ID_EMPRESA_DIFUSORA");
				int numero_operaciones = rs.getInt("NUMERO_OPERACIONES");
				Date fecha_inicial = rs.getDate("FECHA_INICIAL");
				Date fecha_final = rs.getDate("FEHA_FINAL");

				Operaciones op = new Operaciones(id_operaciones, id_usuario, id_artista, id_empresa_difusora,
						numero_operaciones, fecha_inicial, fecha_final);
				operacionesList.add(op);
			}
		} catch (Exception e) {
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

		return operacionesList;
	}

	public boolean delete(int id_operaciones) {
		String sql = "DELETE FROM OPERACIONES WHERE ID_OPERACIONES='" + id_operaciones + "'";
		PreparedStatement ps = null;
		try {
			
			ps = conexion.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			if (ps != null) {
				ps.close();
			}

			

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return false;
	}

	public boolean save(Operaciones operaciones) {

		PreparedStatement ps = null;
		try {

			String sql = "INSERT INTO OPERACIONES ( ID_OPERACIONES ,ID_ARTISTA, ID_EMPRESA_DIFUSORA, ID_USUARIO, NUMERO_OPERACIONES, FECHA_INICIAL, FEHA_FINAL)"
					+ " VALUES(ID_OPERACIONES_SEQUENCE.NEXTVAL,?,?,?,?,?,?)";
			
			ps = conexion.prepareStatement(sql);
			ps.setInt(1, operaciones.getId_artista());
			ps.setInt(2, operaciones.getId_empresa_difusora());
			ps.setInt(3, operaciones.getId_usuario());
			ps.setInt(4, operaciones.getNumero_operaciones());
			java.sql.Timestamp fi = new java.sql.Timestamp(operaciones.getFecha_inicial().getTime());
			ps.setTimestamp(5, fi);
			java.sql.Timestamp ff = new java.sql.Timestamp(operaciones.getFecha_final().getTime());
			ps.setTimestamp(6, ff);

			// execute insert SQL stetement

			ps.executeUpdate();
			System.out.println("operaciones");

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

	public boolean update(Operaciones operaciones) {
		PreparedStatement ps = null;

		try {
			
			String sql = "UPDATE OPERACIONES SET ID_ARTISTA = ? , ID_EMPRESA_DIFUSORA = ?, ID_USUARIO = ? , "
					+ "NUMERO_OPERACIONES = ?, FECHA_INICIAL = ? , FEHA_FINAL = ?  WHERE ID_OPERACIONES = ? ";

			ps = conexion.prepareStatement(sql);
			ps.setInt(1, operaciones.getId_artista());
			ps.setInt(2, operaciones.getId_empresa_difusora());
			ps.setInt(3, operaciones.getId_usuario());
			ps.setInt(4, operaciones.getNumero_operaciones());
			java.sql.Timestamp fi = new java.sql.Timestamp(operaciones.getFecha_inicial().getTime());
			ps.setTimestamp(5, fi);
			java.sql.Timestamp ff = new java.sql.Timestamp(operaciones.getFecha_final().getTime());
			ps.setTimestamp(6, ff);
			ps.setInt(7, operaciones.getId_operaciones());

			// execute insert SQL stetement

			ps.executeUpdate();
			System.out.println("operaciones");

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

}
