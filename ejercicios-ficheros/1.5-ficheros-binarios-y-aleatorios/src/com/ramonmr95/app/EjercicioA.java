package com.ramonmr95.app;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * A.- A partir de la definición de un empleado (ver más abajo), crea un
 * programa JAVA (LocalizarID_Empleado) que pida un identificador de empleado (o
 * bien lo pases por parámetro) y nos muestre en pantalla sus datos, o bien que
 * no existe. (se supone que el fichero está ordenado por ID_Empleado).
 * 
 * @author Ramón Moñino Rubio - 2º DAM
 *
 */
public class EjercicioA {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println("Introduce el id de un empleado: ");
		int id = sc.nextInt();
		localizarID_Empleado(id);
	}

	public static void localizarID_Empleado(int id) throws ClassNotFoundException {
		RandomAccessFile raf = null;

		try {
			raf = new RandomAccessFile("AleatorioEmple.dat", "r");
			raf.seek((id - 1) * 36);
			int id_empleado = raf.readInt();
			
			char[] apellido = new char[10];
			char aux;

			for (int i = 0; i < apellido.length; i++) {
				aux = raf.readChar();
				apellido[i] = aux;
			}

			int departamento = raf.readInt();
			double salario = raf.readDouble();

			Empleado empleado = new Empleado(id_empleado, apellido, departamento, salario);
			System.out.println(empleado);

		} 
		catch (IOException e) {
			e.printStackTrace();
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
