package com.ramonmr95.app.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Conexion {

	public static Connection conn = null;
	private String URL = "jdbc:sqlite:C:\\Users\\lawl\\Documents\\EspaciosDeTrabajo\\AccesoADatos\\nba-xml\\ej2-nba-xml\\";
	
	private Conexion() {
		init();
	}
	
	private void init() {
		try {
			conn = DriverManager.getConnection(URL + this.pedirDB() + ".db");
		} 
		catch (SQLException e) {
			System.out.println("Error conexion: " + e.getMessage());
		}
	}
	
	public static Connection getInstance() {
		if (conn == null) {
			new Conexion();
		}
		return conn;
	}
	
	private String pedirDB() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el nombre de la base de datos: ");
		String dbName = sc.nextLine();
		sc.close();
		return dbName;
	}
}
