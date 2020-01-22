package com.ramonmr95.app;

import java.io.File;
import java.util.Scanner;

/**
 * B.- Realiza un programa JAVA (borrar_dirB) que reciba un directorio que se
 * introduce por parámetro. Deberá recorrerlo recursivamente y para cada fichero
 * que encuentre nos preguntará si deseamos borrarlo. OJO si el objeto
 * encontrado fuese un directorio mostrará un aviso de que el objeto candidato a
 * ser borrado es un directorio y si el usuario desea borrarlo se borrará todo
 * el contenido de ese directorio sin necesidad de preguntar sobre los ficheros
 * y directorios que pueda contener.
 * 
 * @author Ramón Moñino Rubio - 2º DAM
 *
 */
public class EjercicioB {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		File file = new File(args[0]);
		borrar_dirB(file, true);
	}
	
	public static void borrar_dirB(File file, boolean preguntar) {
		File[] ficheros = file.listFiles();

		for (int i = 0; i < ficheros.length; i++) {
			if (ficheros[i].isDirectory()) {
				System.out.println("Quiere borrar el directorio? " + ficheros[i].getName());
				String resp = sc.nextLine();
				
				if (resp.equalsIgnoreCase("SI")) {
					borrar_dirB(ficheros[i], false);
				}
				else if (resp.equalsIgnoreCase("NO")) {
					borrar_dirB(ficheros[i], true);
				}
				ficheros[i].delete();
			}
			else if (preguntar) {
				System.out.println("Está seguro de que quiere borrar este fichero?: " + ficheros[i].getName());
				String resp = sc.nextLine();
				
				if (resp.equalsIgnoreCase("SI")) {
					ficheros[i].delete();
				}
				else if (resp.equalsIgnoreCase("NO")) {
					System.out.println("Fichero no borrado: " + ficheros[i].getName());
				}
				
			}
			else if (!preguntar) {
				ficheros[i].delete();
			}
			
		}
//		file.delete();
	}
}
