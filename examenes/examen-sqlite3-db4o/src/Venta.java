

public class Venta {

	private int cifc;
	private int codCoche;
	private String color;
	private String codCocheNombre;
	private String codCocheModelo;
	private String clienteNombre;
	private String clienteApellidos;
	private String clienteCiudad;

	public Venta(int cifc, int codCoche, String color, String codCocheNombre, String codCocheModelo,
			String clienteNombre, String clienteApellidos, String clienteCiudad) {
		this.cifc = cifc;
		this.codCoche = codCoche;
		this.color = color;
		this.codCocheNombre = codCocheNombre;
		this.codCocheModelo = codCocheModelo;
		this.clienteNombre = clienteNombre;
		this.clienteApellidos = clienteApellidos;
		this.clienteCiudad = clienteCiudad;
	}

	public int getCifc() {
		return cifc;
	}

	public void setCifc(int cifc) {
		this.cifc = cifc;
	}

	public int getCodCoche() {
		return codCoche;
	}

	public void setCodCoche(int codCoche) {
		this.codCoche = codCoche;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCodCocheNombre() {
		return codCocheNombre;
	}

	public void setCodCocheNombre(String codCocheNombre) {
		this.codCocheNombre = codCocheNombre;
	}

	public String getCodCocheModelo() {
		return codCocheModelo;
	}

	public void setCodCocheModelo(String codCocheModelo) {
		this.codCocheModelo = codCocheModelo;
	}

	public String getClienteNombre() {
		return clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public String getClienteApellidos() {
		return clienteApellidos;
	}

	public void setClienteApellidos(String clienteApellidos) {
		this.clienteApellidos = clienteApellidos;
	}

	public String getClienteCiudad() {
		return clienteCiudad;
	}

	public void setClienteCiudad(String clienteCiudad) {
		this.clienteCiudad = clienteCiudad;
	}

	@Override
	public String toString() {
		return "Venta [cifc=" + cifc + ", codCoche=" + codCoche + ", color=" + color
				+ ", codCocheNombre=" + codCocheNombre + ", codCocheModelo=" + codCocheModelo + ", clienteNombre="
				+ clienteNombre + ", clienteApellidos=" + clienteApellidos + ", clienteCiudad=" + clienteCiudad + "]";
	}

}
