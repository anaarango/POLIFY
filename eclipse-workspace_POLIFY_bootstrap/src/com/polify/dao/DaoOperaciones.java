package com.polify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import com.polify.controller.utility.DBUtil;
import com.polify.dto.OperacionesArtistaEmpresaDTO;
import com.polify.entity.Artista;
import com.polify.entity.Empresa_difusora;
import com.polify.entity.Operaciones;

import oracle.jdbc.OracleTypes;

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

	public List<OperacionesArtistaEmpresaDTO> getAllOperacionesEmpresasArtistas() {
		List<OperacionesArtistaEmpresaDTO> operacionesList = new LinkedList<>();
		OperacionesArtistaEmpresaDTO operaciones = new OperacionesArtistaEmpresaDTO();
		Operaciones operacion = new Operaciones();
		Artista artista = new Artista();
		Empresa_difusora empresa = new Empresa_difusora();
		ResultSet rs = null;
		Statement stmt = null;

		try {

			stmt = conexion.createStatement();
			String sql = "SELECT op.ID_OPERACIONES, op.NUMERO_OPERACIONES, op.FECHA_INICIAL, op.FEHA_FINAL, "
					+ "art.id_artista, art.nombre_artista, emp.ID_EMPRESA_DIFUSORA, emp.NOMBRE_EMPRESA, op.ID_USUARIO  "
					+ "FROM ARTISTA art, EMPRESA_DIFUSORA emp, OPERACIONES op where op.ID_EMPRESA_DIFUSORA=emp."
					+ "ID_EMPRESA_DIFUSORA and op.ID_ARTISTA=art.ID_ARTISTA ORDER BY op.ID_OPERACIONES ASC";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				operacion = new Operaciones();
				artista = new Artista();
				empresa = new Empresa_difusora();
				operacion.setId_operaciones(rs.getInt("ID_OPERACIONES"));
				operacion.setId_usuario(rs.getInt("ID_USUARIO"));
				operacion.setFecha_inicial(rs.getDate("FECHA_INICIAL"));
				operacion.setFecha_final(rs.getDate("FEHA_FINAL"));
				operacion.setNumero_operaciones(rs.getInt("NUMERO_OPERACIONES"));

				empresa.setId_empresa_difusora(rs.getInt("ID_EMPRESA_DIFUSORA"));
				empresa.setNombre_empresa(rs.getString("NOMBRE_EMPRESA"));

				artista.setId_artista(rs.getInt("ID_ARTISTA"));
				artista.setNombre_artista(rs.getString("NOMBRE_ARTISTA"));

				operaciones = new OperacionesArtistaEmpresaDTO();
				operaciones.setArtista(artista);
				operaciones.setEmpresa(empresa);
				operaciones.setOperaciones(operacion);

				operacionesList.add(operaciones);
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
	
	
	public List<Operaciones> getAllOperacionesByArtist(java.util.Date date, java.util.Date date2, Integer artistId) {
		List<Operaciones> operacionesList = new LinkedList<>();
		java.sql.Date fi = null ;
		java.sql.Date ff = null;
		System.out.println(date);
		if(date != null) {
		fi = new java.sql.Date(date.getTime());
		}
		if(date2 != null) {
		ff = new java.sql.Date(date2.getTime());
		}

		try {
			String query = "" ;
			if(date != null && date2 != null ) {
			query = "begin ? := GETARTISTOPERATION("+artistId+",to_date('"+ convertStringToDate(fi) +"','dd/mm/yyyy'),to_date('"+ convertStringToDate(ff) +"','dd/mm/yyyy')); end;";
			}else if(date != null && date2 == null)
			{
				query = "begin ? := GETARTISTOPERATION("+artistId+",to_date('"+ convertStringToDate(fi) +"','dd/mm/yyyy'), null ); end;";
			}else if(date == null && date2 != null)
			{
				query = "begin ? := GETARTISTOPERATION("+artistId+",null,to_date( '"+ convertStringToDate(ff) +"','dd/mm/yyyy')); end;";
			}else{
				query = "begin ? := GETARTISTOPERATION("+artistId+",null,null); end;";
			}
			CallableStatement stmt = conexion.prepareCall(query);
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			

			// execute and retrieve the result set
			stmt.execute();
			ResultSet rs = (ResultSet)stmt.getObject(1);

			// print the results
//			while (rs.next()) {
//			    System.out.println(rs.getString(2) + "\t" +
//			        rs.getString(3) + "\t" +
//			        rs.getInt(4));
//			}
			while (rs.next()) {
				int id_artista = rs.getInt("ID_ARTISTA");
				String nombre_artista = rs.getString("NOMBRE_ARTISTA");
				String  nombre_empresa = rs.getString("NOMBRE_EMPRESA");
				int total = rs.getInt("TOTAL");

				Operaciones op = new Operaciones(id_artista,nombre_artista,nombre_empresa,total);
				operacionesList.add(op);
			}
		}
		catch (Exception e) {
			System.out.println("Test");
		}

		return operacionesList;
	}
	
	
	public List<Operaciones> getAllOperacionesByDiffuserCompany(java.util.Date date, java.util.Date date2, Integer companyId) {
		List<Operaciones> operacionesList = new LinkedList<>();
		java.sql.Date fi = null ;
		java.sql.Date ff = null;
		System.out.println(date);
		if(date != null) {
		fi = new java.sql.Date(date.getTime());
		}
		if(date2 != null) {
		ff = new java.sql.Date(date2.getTime());
		}
		try {
			String query = "";
					if(date != null && date2 != null)
					{
						query = "begin ? := GETDIFFUSERCOMPANYOPERATION("+companyId+",to_date('"+convertStringToDate(fi)+"','dd/mm/yyyy'),to_date('"+convertStringToDate(ff)+"','dd/mm/yyyy')); end;";
					}else if(date != null && date2 == null) {
						query = "begin ? := GETDIFFUSERCOMPANYOPERATION("+companyId+",to_date('"+convertStringToDate(fi)+"','dd/mm/yyyy'),null); end;";
					}else if(date == null && date2 != null){
						query = "begin ? := GETDIFFUSERCOMPANYOPERATION("+companyId+",null,to_date('"+convertStringToDate(ff)+"','dd/mm/yyyy')); end;";
					}else {
						query = "begin ? := GETDIFFUSERCOMPANYOPERATION("+companyId+",null,null); end;";
					}
		
			CallableStatement stmt = conexion.prepareCall(query);
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			

			// execute and retrieve the result set
			stmt.execute();
			ResultSet rs = (ResultSet)stmt.getObject(1);

			// print the results
//			while (rs.next()) {
//			    System.out.println(rs.getString(2) + "\t" +
//			        rs.getString(3) + "\t" +
//			        rs.getInt(4));
//			}
			while (rs.next()) {
				int id_artista = rs.getInt("ID_EMPRESA_DIFUSORA");
				String  nombre_empresa = rs.getString("NOMBRE_EMPRESA");
				String nombre_artista = rs.getString("NOMBRE_ARTISTA");
				int numero_operaciones = rs.getInt("NUMERO_OPERACIONES");
				int total = rs.getInt("TOTAL");

				Operaciones op = new Operaciones(id_artista,nombre_artista,nombre_empresa,numero_operaciones,total);
				operacionesList.add(op);
			}
		}
		catch (Exception e) {
			System.out.println("Test");
		}

		return operacionesList;
	}

	public List<Operaciones> getAllOperacionesRanking(java.util.Date date, java.util.Date date2) {
		List<Operaciones> operacionesList = new LinkedList<>();
		java.sql.Date fi = null ;
		java.sql.Date ff = null;
		if(date != null) {
		fi = new java.sql.Date(date.getTime());
		}
		if(date2 != null) {
		ff = new java.sql.Date(date2.getTime());
		}
		try {
			String query ="";
			if(date != null && date2 != null) {
				query = 	"begin ? := GETRANKINGOPERATION(to_date('"+convertStringToDate(fi)+"','dd/mm/yyyy'),to_date("+convertStringToDate(ff)+"','dd/mm/yyyy')); end;";
			}else if(date != null && date2 == null) {
				query = "begin ? := GETRANKINGOPERATION(to_date('"+convertStringToDate(fi)+"','dd/mm/yyyy'),null); end;";
			}else if(date == null && date2 != null) {
				query = "begin ? := GETRANKINGOPERATION(null,to_date("+convertStringToDate(ff)+"','dd/mm/yyyy')); end;";
			}else {
				query = "begin ? := GETRANKINGOPERATION(null,null); end;";
			}
				
			CallableStatement stmt = conexion.prepareCall(query);
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			

			// execute and retrieve the result set
			stmt.execute();
			ResultSet rs = (ResultSet)stmt.getObject(1);

			// print the results
//			while (rs.next()) {
//			    System.out.println(rs.getString(2) + "\t" +
//			        rs.getString(3) + "\t" +
//			        rs.getInt(4));
//			}
			int count = 1;
			while (rs.next()) {
				String nombre_artista = rs.getString("NOMBRE_ARTISTA");
				String  nombre_empresa = rs.getString("NOMBRE_EMPRESA");
				int total = rs.getInt("TOTAL");

				Operaciones op = new Operaciones(nombre_artista,nombre_empresa,total,count);
				operacionesList.add(op);
				count ++;
			}
		}
		catch (Exception e) {
			System.out.println("Test");
		}

		return operacionesList;
	}

	
	
	public String convertStringToDate(Date indate)
	{
	   String dateString = null;
	   SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
	   try{
		dateString = sdfr.format( indate );
	   }catch (Exception ex ){
		System.out.println(ex);
	   }
	   return dateString;
	}
}
