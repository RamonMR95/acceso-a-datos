package com.ramonmr95.app;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * B.- A partir de la definición de un empleado (ver más abajo), crea un
 * programa JAVA (LocalizarAPELLIDO_Empleado) que pida un APELLIDO de empleado
 * (o bien lo pases por parámetro) y nos muestre en pantalla sus datos, o bien
 * que no existe.
 * 
 * @author Ramón Moñino Rubio - 2º DAM
 *
 */
public class EjercicioB {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Introduce el apellido del empleado a buscar: ");
		localizarAPELLIDO_Empleado(sc.nextLine());
	}

	public static void localizarAPELLIDO_Empleado(String apellido) {
		RandomAccessFile raf = null;
		
		try {
			 raf = new RandomAccessFile("AleatorioEmple.dat", "r");

			char[] apell = new char[10];
			int posicion = 4;

			while (true) {
				raf.seek(posicion);
				
				char aux;
				for (int i = 0; i < apell.length; i++) {
					aux = raf.readChar();
					apell[i] = aux;
				}
				
				if (apellido.equalsIgnoreCase(new String(apell).trim())) {
					raf.seek(raf.getFilePointer() - 24);
					int id = raf.readInt();
					raf.seek(raf.getFilePointer() + 20);
					int departamento = raf.readInt();
					double salario = raf.readDouble();
					
					Empleado empleado = new Empleado(id, apell, departamento, salario);
					System.out.println(empleado);
					break;
				}
				else {
					apell = new char[10];
					
					if (raf.getFilePointer() == raf.length()) {
						return;
					}
				    posicion += 36;	 
				}

			}
		} 
		catch (IOException e) {
			System.out.println("El empleado con apellido: " + apellido + " no existe");
		}
		finally {
			if (raf != null) {
				try {
					raf.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
