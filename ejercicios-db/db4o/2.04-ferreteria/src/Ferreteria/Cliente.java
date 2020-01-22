package Ferreteria;

public class Cliente {

	// Atributos de Clientes

	private String nombre;
	private String dni;
	private String localidad;
	private boolean cliente_activo;
	private boolean LOPD_firmada;

	// Constructores

	public Cliente(String nombre, String dni, String localidad) {
		this.nombre = nombre;
		this.dni = dni;
		this.localidad = localidad;
		this.cliente_activo = true;
		this.LOPD_firmada = false;
	}

	public Cliente(String nombre) {
		this.nombre = nombre;
		this.dni = "";
		this.localidad = "";
		this.cliente_activo = true;
		this.LOPD_firmada = false;
	}

	public Cliente() {
		this.nombre = null;
		this.dni = null;
		this.localidad = null;
		this.cliente_activo = true;
		this.LOPD_firmada = false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public boolean isCliente_activo() {
		return cliente_activo;
	}

	public void setCliente_activo(boolean cliente_activo) {
		this.cliente_activo = cliente_activo;
	}

	public boolean isLOPD_firmada() {
		return LOPD_firmada;
	}

	public void setLOPD_firmada(boolean lOPD_firmada) {
		LOPD_firmada = lOPD_firmada;
	}

	public void mostrarDatos() {
		System.out.println(
				"Nombre: " + this.getNombre() + ", DNI: " + this.getDni() + ", localidad: " + this.getLocalidad()
						+ ", Activo: " + this.isCliente_activo() + ", LOPD_firmada: " + this.isLOPD_firmada());
	}

}
