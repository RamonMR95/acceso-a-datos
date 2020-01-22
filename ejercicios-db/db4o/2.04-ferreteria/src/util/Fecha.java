package util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fecha {

	private Calendar fecha;

	public Fecha(int ano, int mes, int dia) {
		this.fecha = new GregorianCalendar(ano, mes - 1, dia);
	}

	public void add(int campo, int cantidad) {
		this.fecha.add(campo, cantidad);
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return fecha.get(Calendar.YEAR) + "-" + (fecha.get(Calendar.MONTH) - 1) + "-"
				+ fecha.get(Calendar.DAY_OF_MONTH);
	}

}
