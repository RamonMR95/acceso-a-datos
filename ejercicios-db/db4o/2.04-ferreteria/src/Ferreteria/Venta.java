package Ferreteria;

import util.Fecha;

public class Venta {

	private Fecha fecha_venta;
	private long id_venta;
	private Cliente cliente;
	private Articulo articulo;
	private int unidades_vendidas;
	private int pvp_unidad;

	public Venta(Cliente cliente, Articulo articulo, int unidades_vendidas, int pvp_unidad) {
		this.cliente = cliente;
		this.articulo = articulo;
		this.unidades_vendidas = unidades_vendidas;
		this.pvp_unidad = pvp_unidad;
	}

	public long getId_venta() {
		return id_venta;
	}

	public void setId_venta(long id_venta) {
		this.id_venta = id_venta;
	}

	public Fecha getFecha_venta() {
		return fecha_venta;
	}

	public void setFecha_venta(Fecha fecha_venta) {
		this.fecha_venta = fecha_venta;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getUnidades_vendidas() {
		return unidades_vendidas;
	}

	public void setUnidades_vendidas(int unidades_vendidas) {
		this.unidades_vendidas = unidades_vendidas;
	}

	public int getPvp_unidad() {
		return pvp_unidad;
	}

	public void setPvp_unidad(int pvp_unidad) {
		this.pvp_unidad = pvp_unidad;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
