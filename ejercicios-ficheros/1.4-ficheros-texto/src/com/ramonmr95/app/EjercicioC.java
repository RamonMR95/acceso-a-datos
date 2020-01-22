package com.ramonmr95.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * C.- Realiza un programa JAVA que escriba en un fichero de texto los primeros
 * X nº naturales, dicho valor se preguntará por teclado, o se introducirá por
 * parámetro.
 * 
 * @author Ramón Moñino Rubio - 2º DAM
 */

public class EjercicioC {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		File fichero1 = new File("ficheroEjercicioC.txt");
		File fichero2 = new File("ficheroEjercicioC2.txt");

		try (BufferedReader in = new BufferedReader(new FileReader(fichero1));
				BufferedWriter out = new BufferedWriter(new FileWriter(fichero2))) {

			String aux = null;
			int counter = 0;
			
			System.out.println("Introduzca el numero de lineas!");
			int numeroNaturales = sc.nextInt();

			while (((aux = in.readLine()) != null) && (counter < numeroNaturales)) {
				out.write(aux);
				out.newLine();
				counter++;
			}

			System.out.println("Archivo Terminado: " + fichero2.getName());

		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		sc.close();
	}
}
