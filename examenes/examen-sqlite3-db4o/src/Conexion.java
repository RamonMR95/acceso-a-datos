/**
 * Alumno: Ramón Moñino Rubio
 * Curso: 2º DAM
 * Examen sqlite3 y db4o
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	public static Connection conn = null;
	private static String url = "jdbc:sqlite:/home/dam18-17/Escritorio/workspace/acceso-a-datos/examen-sqlite3-db4o/ejemploCoches.db";

	private Conexion() {
		init();
	}

	private void init() {
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
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