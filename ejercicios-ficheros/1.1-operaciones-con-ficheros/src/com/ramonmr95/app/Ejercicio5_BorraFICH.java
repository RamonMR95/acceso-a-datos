package com.ramonmr95.app;

import java.io.File;

/**
 * Ej.5 - Hacer un programa que reciba 2 argumentos por linea de comando. El
 * programa se llamará BorraFICH.java. El programa borrará el nombre del fichero
 * pasado como argumento-2 dentro de la carpeta pasada como argumento-1. NOTA:
 * si el argumento-2 fuese una carpeta, deberá borrar todos los archivos que
 * haya dentro de ella y luego borrar la carpeta.
 * 
 * @author Ramón Moñino Rubio - 2º DAM
 *
 */
public class Ejercicio5_BorraFICH {

	public static void main(String[] args) {
		String fileName = args[1];
		String dir = args[0];
		String url = dir + fileName;
		File file = new File(url);
		borrarFichero(file);
	}

	public static void borrarFichero(File file) {
		File[] ficheros = file.listFiles();

		for (int i = 0; i < ficheros.length; i++) {
			if (ficheros[i].isDirectory()) {
				borrarFichero(ficheros[i]);
			}
			ficheros[i].delete();
		}
		file.delete();
	}
}
