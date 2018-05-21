package com.polify.controller.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DBUtil {
	
	 private static Connection conexion = null;

	    public static Connection getConexion() {
	        try {
	            // Cargo el driver de la base de datos
	            Class.forName("oracle.jdbc.OracleDriver");
	            System.out.println("Driver de ORACLE cargado");

	           String nombreUsuario = "bd_polify";
	            String password = "1234";
	            String url = "jdbc:oracle:thin:@localhost:49161:XE";
	            conexion = DriverManager.getConnection(url, nombreUsuario, password);
	            System.out.println("Conexión establecida satisfactoriamente");
	        } catch (ClassNotFoundException ex) {
	            System.out.println("Exception: " + ex.getMessage());
	            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (SQLException ex) {
	            System.out.println("Exception: " + ex.getMessage());
	            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return conexion;
	    }

}
