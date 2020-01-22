package com.ramonmr95.app;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * B.- Hacer un programa que muestre los ficheros con fecha de modificación
 * posterior a una fecha pasada por argumento. (ayuda:
 * http://lineadecodigo.com/java/obtener-fecha-de-modificacion-de-un-fichero-con-java/)
 * 
 * @author Ramón Moñino Rubio - 2º DAM
 *
 */
public class EjercicioB {

	public static void main(String[] args) {
		File fichero = new File(args[1]);
		System.out.println(fichero.lastModified());
		long ms = Long.parseLong(args[0]);
		Calendar fechaBarrera = obtenerFecha(ms);

		File[] ficheros = fichero.listFiles();
		System.out.println(ficheros.length);

		for (File file : ficheros) {
			Calendar fecha = obtenerFecha(file.lastModified());
			if (fecha.after(fechaBarrera)) {
				System.out.println("Archivo: " + file.getName() + 
						", fecha: " + 
						fecha.get(Calendar.DAY_OF_MONTH) + "-" + 
						fecha.get(Calendar.MONTH) + "-" + 
						fecha.get(Calendar.YEAR));
			}
		}

	}

	public static Calendar obtenerFecha(long ms) {
		Date d = new Date(ms);
		Calendar fecha = new GregorianCalendar();
		fecha.setTime(d);
		return fecha;
	}
}
