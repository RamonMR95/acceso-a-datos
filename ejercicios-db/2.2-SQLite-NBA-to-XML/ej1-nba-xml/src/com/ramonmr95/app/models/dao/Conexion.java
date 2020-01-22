package com.ramonmr95.app.models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	public static Connection conn = null;
	private static String url = "jdbc:sqlite:C:\\Users\\lawl\\Documents\\EspaciosDeTrabajo\\AccesoADatos\\nba-xml\\ej1-nba-xml\\nba_sqlite.db";
	
	private Conexion() {
		init();
	}
	
	private void init() {
		try {
			conn = DriverManager.getConnection(url);
		} 
		catch (SQLException e) {
			System.out.println("Error connexion: " + e.getMessage());
		}
	}
	
	public static Connection getInstance() {
		if (conn == null) {
			new Conexion();
		}
		return conn;
	}
}
