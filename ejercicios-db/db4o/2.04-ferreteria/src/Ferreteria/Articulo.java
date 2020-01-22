package Ferreteria;

public class Articulo {

	private String descripcion;
	private String cod_articulo;
	private int stock_actual;
	private int stock_minimo;

	// Constructores
	public Articulo(String descripcion, String cod_articulo, int unidades) {
		this.descripcion = descripcion;
		this.cod_articulo = cod_articulo;
		this.stock_actual = unidades;
		this.stock_minimo = 5;
	}

	public Articulo() {
		this.descripcion = null;
		this.cod_articulo = null;
		this.stock_actual = 0;
		this.stock_minimo = 0;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCod_articulo() {
		return cod_articulo;
	}

	public void setCod_articulo(String cod_articulo) {
		this.cod_articulo = cod_articulo;
	}

	public int getStock_actual() {
		return stock_actual;
	}

	public void setStock_actual(int stock_actual) {
		this.stock_actual = stock_actual;
	}

	public int getStock_minimo() {
		return stock_minimo;
	}

	public void setStock_minimo(int stock_minimo) {
		this.stock_minimo = stock_minimo;
	}

	public void comprarArticulo(int unidades_compradas) {
		this.stock_actual += unidades_compradas;
	}

	public void mostrarDatos() {
		System.out.println("Descripcion: " + this.getDescripcion() + ", Cod_Articulo: " + this.getCod_articulo());
	}

}
