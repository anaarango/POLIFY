package com.polify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.polify.controller.utility.DBUtil;
import com.polify.entity.Empresa_difusora;

public class DaoEmpresa_difusora {

	private Connection conexion;

	public DaoEmpresa_difusora() {
		conexion = DBUtil.getConexion();
	}

	public List<Empresa_difusora> getAllEmpresas() throws SQLException {
		List<Empresa_difusora> empresaList = new LinkedList<>();

		Statement stmt = conexion.createStatement();
		String sql = "SELECT * " + " FROM EMPRESA_DIFUSORA ";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			int id_empresa = rs.getInt("ID_EMPRESA_DIFUSORA");
			String nombre = rs.getString("NOMBRE_EMPRESA");
			String email =rs.getString("EMAIL");
			int valor_operacion = rs.getInt ("VALOR_X_OPERACION");

			Empresa_difusora em = new Empresa_difusora(id_empresa, nombre, email, valor_operacion);
			empresaList.add(em);
		}

		return empresaList;
	}

	public boolean delete(int id_empresa_difusora)  {
		String sql = "DELETE FROM EMPRESA_DIFUSORA WHERE ID_EMPRESA_DIFUSORA='" + id_empresa_difusora + "'";
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

	public boolean save(Empresa_difusora empresa) throws SQLException {

	        try {
	            String sql = "INSERT INTO EMPRESA_DIFUSORA ( ID_EMPRESA_DIFUSORA, NOMBRE_EMPRESA, EMAIL, VALOR_X_OPERACION)"
	                    + " VALUES(ID_EMPRESA_DIFUSORA_SEQUENCE.NEXTVAL,'" + empresa.getNombre_empresa() + "','" 
	            		+ empresa.getEmail() + "','" + empresa.getValor_x_operacion() + "' )";

	            PreparedStatement ps = conexion.prepareStatement(sql);

	            ps.executeUpdate();
	            System.out.println("empresa");

	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	}
	
	 public boolean update(Empresa_difusora empresa) {
	        String sql = "UPDATE EMPRESA_DIFUSORA SET NOMBRE_EMPRESA ='" + empresa.getNombre_empresa()  + "' , "
	                + "EMAIL = '" + empresa.getEmail() + "', "
	                 + "VALOR_X_OPERACION = '" + empresa.getValor_x_operacion() + "'"
	                + " WHERE ID_EMPRESA_DIFUSORA = '" + empresa.getId_empresa_difusora() + "'";

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
	
	
}
