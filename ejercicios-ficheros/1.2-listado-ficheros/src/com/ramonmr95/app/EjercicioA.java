package com.ramonmr95.app;

import java.io.File;

/**
 * A.- Hacer un programa que devuelva los ficheros de un directorio ordenados en
 * orden alfabético inverso.
 * 
 * @author Ramón Moñino Rubio - 2º DAM
 *
 */
public class EjercicioA {

	public static void main(String[] args) {
		File directorio = new File(args[0]);

		File[] archivos = directorio.listFiles();

		System.out.println("-------------------------------");
		System.out.println("Ficheros SIN ordenar");
		System.out.println("-------------------------------\n");

		for (File file : archivos) {
			System.out.println(file.getName());
		}

		for (int i = 0; i < archivos.length - 1; i++) {
			for (int j = 0; j < archivos.length - i - 1; j++) {
				File aux;

				if ((archivos[j].getName().compareToIgnoreCase(archivos[j + 1].getName())) < 0) {
					aux = archivos[j];
					archivos[j] = archivos[j + 1];
					archivos[j + 1] = aux;
				}
			}
		}

		System.out.println("\n-------------------------------");
		System.out.println("Ficheros ORDENADOS inversamente");
		System.out.println("-------------------------------");

		for (File file : archivos) {
			System.out.println(file.getName());
		}

	}

}
