package com.ramonmr95.app;


public class Empleado {

	private int id_empleado;
	private char[] apellido;
	private int departamento;
	private double salario;

	public Empleado(int id, char[] apellido, int departamento, double salario) {
		this.id_empleado = id;
		this.apellido = apellido;
		this.departamento = departamento;
		this.salario = salario;
	}

	public int getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(int id_empleado) {
		this.id_empleado = id_empleado;
	}

	public char[] getApellido() {
		return apellido;
	}

	public void setApellido(char[] apellido) {
		this.apellido = apellido;
	}

	public int getDepartamento() {
		return departamento;
	}

	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Empleado [id_empleado=" + id_empleado + ", apellido=" + new String(apellido).trim() + ", departamento="
				+ departamento + ", salario=" + salario + "]";
	}

}
