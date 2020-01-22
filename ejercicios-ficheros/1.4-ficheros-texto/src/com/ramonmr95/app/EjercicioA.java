package com.ramonmr95.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A.- Realiza un programa JAVA que lea un fichero de texto y lo copie en otro
 * fichero de texto sustituyendo cada letra minúscula que encuentre a mayúscula.
 * 
 * @author Ramón Moñino Rubio - 2º DAM
 *
 */
public class EjercicioA {

	public static void main(String[] args) {

		File fichero1 = new File("fichero1.txt");
		File fichero2 = new File("fichero2.txt");

		try (BufferedReader in = new BufferedReader(new FileReader(fichero1));
				BufferedWriter out = new BufferedWriter(new FileWriter(fichero2))) {

			String aux = null;

			while ((aux = in.readLine()) != null) {
				out.write(aux.toUpperCase());
				out.newLine();
			}

			System.out.println("Archivo Terminado: " + fichero2.getName());

		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
