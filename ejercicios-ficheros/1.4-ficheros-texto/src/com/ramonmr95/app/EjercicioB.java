package com.ramonmr95.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * B.- Realiza un programa JAVA que lea un fichero de texto que contiene por
 * cada línea una cantidad numérica entera. Deberá mostrar en pantalla el
 * resultado y crear el fichero sumatorio.txt que contendrá dicho valor.
 * 
 * @author Ramón Moñino Rubio - 2º DAM
 *
 */
public class EjercicioB {

	public static void main(String[] args) {

		File fichero1 = new File("ficheroNumerosB.txt");
		File fichero2 = new File("sumatorio.txt");

		try (BufferedReader in = new BufferedReader(new FileReader(fichero1));
				BufferedWriter out = new BufferedWriter(new FileWriter(fichero2))) {

			fichero2.createNewFile();

			double sumatorio = 0;
			String aux = null;

			while ((aux = in.readLine()) != null) {
				sumatorio += Double.parseDouble(aux);
			}

			out.write(String.valueOf(sumatorio));
			System.out.println("Archivo Terminado: " + fichero2.getName());

		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
