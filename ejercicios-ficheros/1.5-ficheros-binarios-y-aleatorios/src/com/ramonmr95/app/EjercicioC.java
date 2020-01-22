package com.ramonmr95.app;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * C.- A partir de la definición de un empleado (ver más abajo), crea un
 * programa JAVA (SueldosDep) que pida un identificador de DEPARTAMENTO (o bien
 * lo pases por parámetro) y nos muestre en pantalla los nombres de los
 * empleados que pertenecen a él, su sueldo y la suma total de todos los sueldos
 * asociados a ese departamento.
 * 
 * @author Ramón Moñino Rubio - 2º DAM
 *
 */
public class EjercicioC {

	static Scanner sc = new Scanner(System.in);
	static double sueldoTotal = 0;

	public static void main(String[] args) {
		System.out.println("Introduce el codigo de departamento: ");
		sueldosDep(sc.nextInt());
	}

	public static void sueldosDep(int dpto) {
		RandomAccessFile raf = null;
		double sumaSueldosDpto = 0;

		try {
			raf = new RandomAccessFile("AleatorioEmple.dat", "r");
			int posicion = 24;

			while (true) {
				raf.seek(posicion);

				if (raf.getFilePointer() >= raf.length()) {
					break;
				}

				int departamento = raf.readInt();

				if (departamento == dpto) {
					raf.seek(raf.getFilePointer() - 28);
					int id = raf.readInt();

					char[] apell = new char[10];
					char aux;
					for (int i = 0; i < apell.length; i++) {
						aux = raf.readChar();
						apell[i] = aux;

					}
					
					raf.seek(raf.getFilePointer() + 4);
					double salario = raf.readDouble();
					Empleado empleado = new Empleado(id, apell, departamento, salario);
					sumaSueldosDpto += empleado.getSalario();
					System.out.println(empleado);

				}
				posicion += 36;

			}

			if (sumaSueldosDpto > 0) {
				System.out.println("La suma de los sueldos del dpto: " + dpto + " es de " + sumaSueldosDpto);
			} 
			else {
				System.out.println("No existe ningún empleado con el cod de dpto: " + dpto);
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
