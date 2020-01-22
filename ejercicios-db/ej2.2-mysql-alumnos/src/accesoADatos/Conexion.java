package accesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	 private static Connection conn;

	    public static String DB_NAME = "aad_03";
	    public static String TABLE_NAME = "alumnos";
//	    private static String URL_CLASE = "jdbc:mysql://172.20.254.161/" + DB_NAME;
	    private static String URL_EXTERIOR = "jdbc:mysql://iescierva.net:14306/" + DB_NAME;
	    private static String USER = "aad_03";
	    private static String PASSWORD = "dam18_02";

	    private Conexion() {
	        this.open();
	    } 

	    private void open() {
	        try {
	            conn = DriverManager.getConnection(URL_EXTERIOR, USER, PASSWORD);
	            System.out.println("Connected");
	        } 
	        catch (SQLException e) {
	            System.out.println("No se pudo conectar a la base de datos: " + e.getMessage());
	        }

	    }


	    public static Connection getInstance() {
	        if (conn == null) {
	            new Conexion();
	        }
	        return conn;
	    }
}
