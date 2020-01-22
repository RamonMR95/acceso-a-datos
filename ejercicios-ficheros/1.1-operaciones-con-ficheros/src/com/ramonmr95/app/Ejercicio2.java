package com.ramonmr95.app;

import java.io.*;

/**
 * Ej.2 - Modificar VerInf para que se liste el contenido de varios directorios que se pasarán por argumento.
 * @author Ramón Moñino Rubio - 2º DAM
 */
public class Ejercicio2 {

	public static void main(String[] args) {
		System.out.println("INFORMACIÓN SOBRE EL FICHERO:");

		for (String fichero : args) {
			File f = new File(fichero);

			if (f.exists()) {
				System.out.println("Nombre del fichero  : " + f.getName());
				System.out.println("Ruta                : " + f.getPath());
				System.out.println("Ruta absoluta       : " + f.getAbsolutePath());
				System.out.println("Se puede leer       : " + f.canRead());
				System.out.println("Se puede escribir   : " + f.canWrite());
				System.out.println("Tamaño              : " + f.length());
				System.out.println("Es un directorio    : " + f.isDirectory());
				System.out.println("Es un fichero       : " + f.isFile());
				System.out.println("Nombre del directorio padre: " + f.getParent());
				System.out.println();
			}
		}
	}
}
