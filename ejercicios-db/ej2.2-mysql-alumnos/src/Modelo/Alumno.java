package Modelo;

import java.util.Date;

public class Alumno {

	private String identificador;
	private String nombre;
	private Date fechaNacimiento;
	private float calificacion;
	private int coeficienteIntelectual;

	public Alumno(String identificador, String nombre, Date fechaNacimiento, float calificacion,
			int coeficienteIntelectual) {
		this.identificador = identificador;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.calificacion = calificacion;
		this.coeficienteIntelectual = coeficienteIntelectual;
	}


	
	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public float getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
	}

	public int getCoeficienteIntelectual() {
		return coeficienteIntelectual;
	}

	public void setCoeficienteIntelectual(int coeficienteIntelectual) {
		this.coeficienteIntelectual = coeficienteIntelectual;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String toString() {
		return String.format("Alumno:\nNombre \t\t%s\nIdentificador: \t\t%s\nFecha de nacimiento: \t%s\nCalificación: \t\t%f\nCI: \t\t%d\n ", nombre, identificador, fechaNacimiento.getDay() + "-" + fechaNacimiento.getMonth() + "-" + fechaNacimiento.getYear(), calificacion, coeficienteIntelectual);
	}

//	return "Alumno [identificador=" + identificador + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento
//	+ ", calificacion=" + calificacion + ", CI=" + coeficienteIntelectual + "]";
}

