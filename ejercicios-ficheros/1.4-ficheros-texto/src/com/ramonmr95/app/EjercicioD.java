package com.ramonmr95.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * D.- Realiza un programa JAVA que reciba por parámetro una serie de argumentos
 * (que serán nombres de persona) y los almacenará ordenados en un fichero de
 * texto de salida.
 * 
 * @author Ramón Moñino Rubio - 2º DAM
 *
 */

public class EjercicioD {
	
	public static void main(String[] args) {
		List<String> listaNombres = new ArrayList<>();
		File ficheroNombres = new File("ficheroNombresEjercicioD.txt");
		
		for (String nombre : args) {
			listaNombres.add(nombre);
		}
		
		listaNombres.sort((String nombre1, String nombre2) -> {
			return nombre1.compareToIgnoreCase(nombre2);
		});
		
		try (BufferedWriter out = new BufferedWriter(new FileWriter(ficheroNombres))) {
			
			for (String nombre : listaNombres) {
				out.write(nombre);
				out.newLine();
			}
			
			System.out.println("Archivo Terminado: " + ficheroNombres.getName());

		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
