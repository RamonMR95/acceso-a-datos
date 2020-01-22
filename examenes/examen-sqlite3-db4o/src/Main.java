/**
 * Alumno: Ramón Moñino Rubio
 * Curso: 2º DAM
 * Examen sqlite3 y db4o
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	
	/* Inicialización de la base de datos sqlite3 */
	static Connection conn = Conexion.getInstance();

	
	/* Inicialización de la base de datos db4o */
	static String DBVentas = "DBventas.db4o";
	static ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBVentas);

	public static void main(String[] args) {
		int opcion = -1;

		do {
			System.out.println("Elige la opcion:");
			System.out.println("================");
			System.out.println(" 1.- Mostrar los coches (CÓDIGO DE COCHE, NOMBRE Y MODELO) que se han"
					+ " vendido en una ciudad que se pide por teclado.");
			System.out.println();
			System.out.println(" 2.- Almacenar en una BBDDOO (ventas.dbo) todos los registros (como objetos) de la"
					+ " tabla VENTA, pero añadiendo los atributos codcoche.nombre, codcoche.modelo,"
					+ " cliente.nombre, cliente.apellidos, cliente.ciudad. (crear toda la estructura necesaria de clases"
					+ " java, etc.)");
			System.out.println();
			System.out.println(" 3.- Aumentar en la tabla DISTRIBUCION un 10% la cantidad de vehículos de un"
					+ " modelo que se pide por teclado.");
			System.out.println();
			System.out.println("4.- Salir");
			System.out.println("Opcion= ??");
			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
			case 1:
				mostrarCochesCiudad();
				break;
			case 2:
				almacenarVentas();
				break;
			case 3:
				aumentarDistribucionModeloCoche();
				break;
			case 4:
				System.out.println("HASTA LUEGO");
				break;
			}
		} 
		while (opcion != 4);
		sc.close();
	}
	
	public static void mostrarCochesCiudad() {
		System.out.println("Introduce el nombre de la ciudad: ");
		String ciudad = sc.nextLine();
		
		String query = "select c.codcoche, c.nombre, c.modelo " + 
						"from COCHE c " + 
						"inner join VENTA v on c.codcoche = v.codcoche " +  
						"inner join CONCESIONARIO cs on v.cifc = cs.cifc " + 
						"where cs.ciudad = '" + ciudad + "'";
		
		try (Statement statement = conn.createStatement(); 
				ResultSet resultSet = statement.executeQuery(query)) {
			
			while (resultSet.next()) {
				System.out.println("codcoche: " + resultSet.getString(1) + 
						", nombre: " + resultSet.getString(2) + 
						", modelo: " + resultSet.getString(3));
			}
			
		}
		catch (SQLException e) {
			System.out.println("Error coches ciudad: " + e.getMessage());
		}
		
	}
	
	public static void almacenarVentas() {
		String query = "select v.cifc, c.codcoche, v.Color, c.nombre, c.modelo, cl.nombre, cl.apellidos, cl.ciudad " + 
						"from coche c " + 
						"inner join venta v on c.codcoche = v.codcoche " + 
						"inner join cliente cl on v.cifcl = cl.cifcl;";

		
		try (Statement statement = conn.createStatement(); 
				ResultSet resultSet = statement.executeQuery(query)) {
			
			while (resultSet.next()) {
				Venta venta = new Venta(resultSet.getInt(1), 
						resultSet.getInt(2), 
						resultSet.getString(3), 
						resultSet.getString(4), 
						resultSet.getString(5), 
						resultSet.getString(6), 
						resultSet.getString(7), 
						resultSet.getString(8));
				db.store(venta);
			}
			
		}
		catch (SQLException e) {
			System.out.println("Error almacen ventas: " + e.getMessage());
		}
	}
	
	public static void aumentarDistribucionModeloCoche() {
		System.out.println("Introduce el modelo del coche: ");
		String modelo = sc.nextLine();
		
		String query = "UPDATE DISTRIBUCION " + 
				"set cantidad = cantidad * 1.1 " + 
				"WHERE codcoche IN (SELECT codcoche "
								+ "from coche "
								+ "where modelo like '" + modelo + "');";
		
		try (Statement statement = conn.createStatement()) {
			statement.executeUpdate(query);
			System.out.println("Update realizado del modelo: " + modelo);
		}
		catch (SQLException e) {
			System.out.println("Error update: " + e.getMessage());
		}
		
	}

}
