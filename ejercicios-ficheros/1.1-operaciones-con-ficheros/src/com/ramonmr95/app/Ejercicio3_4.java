package com.ramonmr95.app;

import java.io.File;

/**
 * Ej.3 - Modificar VerInf para que muestre los ficheros de un directorio con un
 * tamaño inferior a un valor de Kbytes. (El nombre del directorio y el valor de
 * Kbytes se pasarán por argumento). (Ojo convertir el argumento Nª Kbytes a
 * entero, ya que se recibe como cadena).
 * 
 * Ej.4 - Modificar el Ej.3 para que además muestre las propiedades de los
 * ficheros listados.
 * 
 * @author Ramón Moñino Rubio - 2º DAM
 *
 */
public class Ejercicio3_4 {

	public static void main(String[] args) {
		System.out.println("INFORMACIÓN SOBRE EL FICHERO:");

		File f = new File(args[0]);
		File[] listaFicheros = f.listFiles();
		int size = Integer.parseInt(args[1]);
		System.out.println(size);

		if (f.exists()) {
			for (File fichero : listaFicheros) {
				File f2 = fichero;

				if ((f2.length() / 1024) < size) {
					System.out.println("Nombre del fichero  : " + f2.getName());
					System.out.println("Ruta                : " + f2.getPath());
					System.out.println("Ruta absoluta       : " + f2.getAbsolutePath());
					System.out.println("Se puede leer       : " + f2.canRead());
					System.out.println("Se puede escribir   : " + f2.canWrite());
					System.out.println("Tamaño              : " + f2.length() / 1024 + " KBs");
					System.out.println("Es un directorio    : " + f2.isDirectory());
					System.out.println("Es un fichero       : " + f2.isFile());
					System.out.println("Nombre del directorio padre: " + f2.getParent());
					System.out.println();
				}
			}
		}
	}
}
