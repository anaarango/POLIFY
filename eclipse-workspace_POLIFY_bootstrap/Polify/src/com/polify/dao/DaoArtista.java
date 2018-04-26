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

public class DaoArtista {

	private Connection conexion;

	public DaoArtista() {
		conexion = DBUtil.getConexion();
	}

	public List<Artista> getAllArtist() throws SQLException {
		List<Artista> artistList = new LinkedList<>();

		Statement stmt = conexion.createStatement();
		String sql = "SELECT * " + " FROM ARTISTA ORDER BY ID_ARTISTA ASC ";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			int id_artista = rs.getInt("ID_ARTISTA");
			String nombre = rs.getString("NOMBRE_ARTISTA");
			int id_empresa = rs.getInt("ID_EMPRESA_DIFUSORA");
			String email = rs.getString("EMAIL");

			Artista a = new Artista(id_artista, nombre, id_empresa, email);
			artistList.add(a);
		}

		return artistList;
	}

	public boolean delete(int id_artista)  {
		String sql = "DELETE FROM ARTISTA WHERE ID_ARTISTA='" + id_artista + "'";
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

	public boolean save(Artista artista) throws SQLException {

	        try {
	            String sql = "INSERT INTO ARTISTA ( ID_ARTISTA ,NOMBRE_ARTISTA, ID_EMPRESA_DIFUSORA, EMAIL)"
	                    + " VALUES(ARTISTA_ID_SEQUENCE.NEXTVAL,'" + artista.getNombre_artista() + "','" + artista.getId_empresa_difusora() + "','" + artista.getEmail() + "')";

	            PreparedStatement ps = conexion.prepareStatement(sql);

	            ps.executeUpdate();
	            System.out.println("artista");

	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	}
	
	 public boolean update(Artista artista) {
	        String sql = "UPDATE ARTISTA SET NOMBRE_ARTISTA ='" + artista.getNombre_artista()+"', "
	        		+ "ID_EMPRESA_DIFUSORA = '" + artista.getId_empresa_difusora()+"', "
	        		+ "EMAIL = '" + artista.getEmail()+"' "
	                + " WHERE ID_ARTISTA = '" + artista.getId_artista() + "'";

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
