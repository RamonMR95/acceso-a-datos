package com.ramonmr95.app;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * C.- Hacer un programa que muestre los ficheros admitiendo caracteres comodín,
 * esto es, ha de permitir las posibles siguientes entradas: (usando el ? y el
 * *, al modo de los S.O.).
 * 
 * @author Ramón Moñino Rubio - 2º DAM
 *
 */
public class EjercicioC {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el nombre del fichero: ");
		String nombreFichero = sc.nextLine();

		File file = new File("/home");

		Pattern pat = Pattern.compile(nombreFichero);

		for (File fichero : file.listFiles()) {

			Matcher mat = pat.matcher(fichero.getName());

			if (mat.matches()) {
				System.out.println("Fichero: " + fichero.getName());
			}
		}
		sc.close();

	}

}
