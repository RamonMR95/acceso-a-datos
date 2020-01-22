package com.ramonmr95.app;

import java.io.File;
import java.util.Scanner;

/**
 * A.- Realiza un programa JAVA (borrar_dirA) que reciba un directorio que se
 * introduce por parámetro. Deberá recorrerlo recursivamente y para cada fichero
 * que encuentre nos preguntará si deseamos borrarlo.
 * 
 * @author Ramón Moñino Rubio - 2º DAM
 *
 */
public class EjercicioA {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		File file = new File(args[0]);
		borrar_dirA(file);
	}
	
	public static void borrar_dirA(File file) {
		File[] ficheros = file.listFiles();

		for (int i = 0; i < ficheros.length; i++) {
			if (ficheros[i].isDirectory()) {
				borrar_dirA(ficheros[i]);
			}
			
			System.out.println("Quiere borrar");
			
			if (sc.nextLine().equalsIgnoreCase("SI")) {
				ficheros[i].delete();
			}
		}
//		file.delete();
	}
}
