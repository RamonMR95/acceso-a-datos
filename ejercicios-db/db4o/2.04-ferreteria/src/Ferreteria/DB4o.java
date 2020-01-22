package Ferreteria;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

import util.Fecha;


public class DB4o {

	static String DBferreteria = "DBferreteria.db4o";
	static EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
	static ObjectContainer db = Db4oEmbedded.openFile(config, DBferreteria);

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		config.common().objectClass(Calendar.class).callConstructor(true);
		
		int opcion = -1;
		
		do {
			System.out.println("Elige la opcion:");
			System.out.println("================");
			System.out.println(" 1.- Añadir Cliente");
			System.out.println(" 2.- Mostrar Clientes");
			System.out.println(" 3.- Modificar Cliente");
			System.out.println();
			System.out.println(" 4.- Añadir Artículo nuevo");
			System.out.println(" 5.- Mostrar Artículos");
			System.out.println(" 6.- Modificar Artículo");
			System.out.println(" 7.- Reponer Artículo");
			System.out.println();
			System.out.println(" 8.- Hacer Venta");
			System.out.println(" 9.- Anular Venta");
			System.out.println(
					"10.- Mostrar todos los artículos de los que haya que pedir unidades al almacen por tener pocas unidades en la ferretería");
			System.out.println(
					"11.- Mostrar los nombres de los artículos vendidos entre 2 fechas que se piden por teclado");
			System.out.println("12.- Localiza a todos los clientes que han hecho una compra en los tres últimos meses");
			System.out.println(
					"13.- Muestra las localidades de los clientes y los nombres de los clientes que han realizado una factura por un importe superior a 50€");
			System.out.println();
			System.out.println("0.- Salir");
			System.out.println("Opcion= ??");
			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
			case 0:
				System.out.println("HASTA LUEGO");
				break;
			case 1:
				anadirCliente();
				break;
			case 2:
				mostrarClientes();
				break;
			case 3:
				modificarCliente();
				break;
			case 4:
				anadirArticulo();
				break;
			case 5:
				mostrarArticulos();
				break;
			case 6:
				modificarArticulo();
				break;
			case 7:
				reponerArticulo();
				break;
			case 8:
				hacerVenta();
				break;
			case 9:
				anularVenta();
				break;
			case 10:
				articulosAreponer();
				break;
			case 11:
				ventasRealizadas();
				break;
			case 12:
				ultimosClientes();
				break;
			case 13:
				ventasMasde50();
				break;

			default:
				break;

			}
		} 
		while (opcion != 0);

		db.close();

	}

	/**
	 * Metodo que obtiene un cliente dado su DNI
	 * @param DNI - Dni del cliente
	 * @return Cliente - cliente de la db
	 */
	private static Cliente obtenerCliente(String DNI) {
		ObjectSet<Cliente> clientes = db.query(new Predicate<Cliente>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(Cliente cliente) {
				return cliente.getDni().equalsIgnoreCase(DNI);
			}
			
		});

		if (clientes.size() > 0) {
			return clientes.get(0);
		}
		return null;
	}
	
	/**
	 * Metodo que obtiene un articulo dado su cod_articulo
	 * @param cod_articulo - Codigo que representa el articulo en la db
	 * @return Articulo - Articulo de la db
	 */
	private static Articulo obtenerArticulo(String cod_articulo) {
		ObjectSet<Articulo> articulos = db.query(new Predicate<Articulo>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(Articulo articulo) {
				return articulo.getCod_articulo().equalsIgnoreCase(cod_articulo);
			}
			
		});

		if (articulos.size() > 0) {
			return articulos.get(0);
		}
		return null;
	}
	
	/**
	 * Metodo que devuelve todos los articulos de la bd
	 * @return Lista de articulos
	 */
	private static List<Articulo> obtenerTodosArticulos() {
		Query query = db.query();
		query.constrain(Articulo.class);
		return query.execute();
	}
	
	/**
	 * Metodo que obtiene una venta dado el DNI del Cliente y la fecha de la venta
	 * @param DNI - DNI del cliente
	 * @param fecha - Fecha de la venta
	 * @return Venta - Venta correspondiente a ese DNI y fecha
	 */
	private static Venta obtenerVenta(String DNI, String id_venta) {
		List<Venta> ventas = db.query(new Predicate<Venta>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(Venta venta) {
				return venta.getCliente().getDni().equalsIgnoreCase(DNI) 
						&& venta.getId_venta() == (Long.parseLong(id_venta));
			}
			
		});

		if (ventas.size() > 0) {
			return ventas.get(0);
		}
		return null;
	}

	/**
	 * Metodo que añade un cliente a la db
	 */
	private static void anadirCliente() {
		System.out.println("Introduce el nombre: ");
		String nombre = sc.nextLine();
		System.out.println("Introduce el dni: ");
		String DNI = sc.nextLine();
		System.out.println("Introduce la localidad: ");
		String localidad = sc.nextLine();
		Cliente cliente = new Cliente(nombre, DNI, localidad);
		db.store(cliente);
	}

	/**
	 * Metodo que muestra todos los clientes de la db
	 */
	private static void mostrarClientes() {
		Query query = db.query();
		query.constrain(Cliente.class);
		List<Cliente> clientes = query.execute();
		
		for (Cliente cliente : clientes) {
			cliente.mostrarDatos();
		}
	}
	
	/**
	 * Metodo que modifica los datos de un cliente dado su dni
	 */
	private static void modificarCliente() {
		System.out.println("Introduce el DNI del cliente a modificar: ");
		String dni = sc.nextLine();
		Cliente clienteModificar = obtenerCliente(dni);
		
		if (clienteModificar != null) {
			System.out.println("Introduce nombre: ");
			String nombre = sc.nextLine();
			System.out.println("Introduce localidad: ");
			String localidad = sc.nextLine();
			clienteModificar.setNombre(nombre);
			clienteModificar.setLocalidad(localidad);
			db.store(clienteModificar);
		}
		else {
			System.err.println("Cliente modificar: No existe cliente con ese DNI");
		}

	}
	
	/**
	 * Metodo que añade un articulo en la db
	 */
	private static void anadirArticulo() {
		System.out.println("Introduce el cod_articulo: ");
		String cod_articulo = sc.nextLine();
		
		if (obtenerArticulo(cod_articulo) == null) {
			System.out.println("Introduce la descripcion del articulo: ");
			String descripcion = sc.nextLine();
			System.out.println("Introduce el stock actual: ");
			int stock_actual = sc.nextInt();
			System.out.println("Introduce el stock minimo: ");
			int stock_minimo = sc.nextInt();
			
			Articulo articulo = new Articulo();
			articulo.setCod_articulo(cod_articulo);
			articulo.setDescripcion(descripcion);
			articulo.setStock_actual(stock_actual);
			articulo.setStock_minimo(stock_minimo);
			db.store(articulo);
		}
		else {
			System.err.println("Articulo: Ya existe un articulo con el cod: " + cod_articulo);
		}
	}
	

	/**
	 * Metodo que muestra todos los articulos de la db
	 */
	private static void mostrarArticulos() {
		List<Articulo> articulos = db.queryByExample(Articulo.class);

		for (Articulo articulo : articulos) {
			System.out.println("Cod_art: " + articulo.getCod_articulo() + 
					", Descripcion: " + articulo.getDescripcion() + 
					", Stock actual: " + articulo.getStock_actual() + 
					", Stock minimo: " + articulo.getStock_minimo());
		}
	}
	
	/**
	 * Metodo que modifica un articulo dado su codigo de articulo
	 */
	private static void modificarArticulo() {
		System.out.println("Introduce el cod_art del articulo a modificar: ");
		String cod_art = sc.nextLine();
		Articulo articuloModificar = obtenerArticulo(cod_art);
		
		if (articuloModificar != null) {
			System.out.println("Introduce descripcion: ");
			String descripcion = sc.nextLine();
			System.out.println("Introduce stock_actual: ");
			int stock_actual = sc.nextInt();
			System.out.println("Introduce stock_minimo: ");
			int stock_minimo = sc.nextInt();
			articuloModificar.setDescripcion(descripcion);
			articuloModificar.setStock_actual(stock_actual);
			articuloModificar.setStock_minimo(stock_minimo);
			db.store(articuloModificar);
		}
		else {
			System.err.println("Articulo modificar: No existe articulo con ese cod_art");
		}
	}
	
	/**
	 * Metodo que comprueba el stock actual de un articulo
	 */
	private static void reponerArticulo() {
		System.out.println("Introduzca cod_art: ");
		String cod_art = sc.nextLine();
		Articulo articulo = obtenerArticulo(cod_art);
		
		if (articulo != null) {
			System.out.println("Introduzca el numero de unidades adquiridas: ");
			int unidades = sc.nextInt();
			articulo.setStock_actual(articulo.getStock_actual() + unidades);
			db.store(articulo);
		}
		else {
			System.err.println("Articulo reponer: No existe articulo con ese cod_art");
		}
	}
	
	/**
	 * Metodo que se encarga de realizar una venta con la posterior gestion del stock del articulo
	 */
	private static void hacerVenta() {
		System.out.println("Introduzca su DNI: ");
		String dni = sc.nextLine();
		System.out.println("Introduzca cod_art: ");
		String cod_art = sc.nextLine();
		System.out.println("Introduzca el numero de unidades: ");
		int unidades = sc.nextInt();
		System.out.println("Introduzca el precio por unidad: ");
		int precio_unidad = sc.nextInt();
		
		if (precio_unidad <= 0) {
			System.out.println("El precio por unidad debe ser mayor que 0!!");
			return;
		}
		
		Cliente cliente = obtenerCliente(dni);
		Articulo articulo = obtenerArticulo(cod_art);
		
		if (cliente != null) {
			if (articulo != null) {
				if (articulo.getStock_actual() >= unidades) {
					Venta venta = new Venta(cliente, articulo, unidades, precio_unidad);
					venta.setId_venta((int) (Math.random() * 10000));
					
					Calendar hoy = GregorianCalendar.getInstance();
					
					venta.setFecha_venta(new Fecha(hoy.get(Calendar.YEAR), 
								hoy.get(Calendar.MONTH),
								hoy.get(Calendar.DAY_OF_MONTH)));
					
					articulo.setStock_actual(articulo.getStock_actual() - unidades);
					
					db.store(venta);
					
					System.out.println("Venta: id_venta: " + venta.getId_venta() + 
							", Cliente DNI: " + cliente.getDni() + 
							", Articulo cod_art: " + articulo.getCod_articulo() +
							", Unidades: " + unidades + 
							", fecha: " + venta.getFecha_venta().toString());
					
					System.out.println("Stock Actual del Articulo con cod_art: " + 
							articulo.getCod_articulo() + " = " + articulo.getStock_actual());
				}
				else {
					System.err.println("Unidades en stock actual insuficientes");
				}
			}
			else {
				System.err.println("El articulo no existe en la db");
			}
		}
		else {
			System.err.println("Venta: El cliente no existe en la db");
		}
		
	}
	
	/**
	 * Metodo que busca una venta y si la encuentra la elimina de la db
	 */
	private static void anularVenta() {
		System.out.println("Introduzca su dni: ");
		String dni = sc.nextLine();
		System.out.println("Introduzca el id de venta: ");
		String id_venta = sc.nextLine();
		
		Venta venta = obtenerVenta(dni, id_venta);
		
		if (venta != null) {
			Articulo articulo = venta.getArticulo();
			articulo.setStock_actual(articulo.getStock_actual() + venta.getUnidades_vendidas());
			
			System.out.println("Venta anulada: id_venta: " + venta.getId_venta() + 
							", Cliente DNI: " + venta.getCliente().getDni() + 
							", Articulo cod_art: " + articulo.getCod_articulo() +
							", Unidades: " + venta.getUnidades_vendidas() + 
							", fecha: " + venta.getFecha_venta().toString());
			db.delete(venta);
		}
		else {
			System.err.println("Venta anular: No existe cliente o articulo");
		}
		
	}
	
	/**
	 * Metodo que muestra todos los articulos los cuales el stock actual es inferior al minimo
	 */
	private static void articulosAreponer() {
		List<Articulo> articulosAReponer = obtenerTodosArticulos()
				.stream()
				.filter((articulo) -> articulo.getStock_actual() < articulo.getStock_minimo())
				.collect(Collectors.toList());
		
		if (!articulosAReponer.isEmpty()) {
			for (Articulo articulo : articulosAReponer) {
				articulo.mostrarDatos();
			}
		}
		else {
			System.err.println("No existen articulos que reponer");
		}

	}
	
	/**
	 * Metodo que muestra el cliente, articulo, y fecha de cada una de las ventas realizadas entre dos fechas
	 */
	private static void ventasRealizadas() {
		System.out.println("Introduce una fecha: yyyy-mm-dd");
		String fechaCadena = sc.nextLine();
		Calendar fecha1 = pedirFecha(fechaCadena);
		
		System.out.println("Introduce una fecha: yyyy-mm-dd");
		String fechaCadena2 = sc.nextLine();
		Calendar fecha2 = pedirFecha(fechaCadena2);
		
		List<Venta> ventas = db.query(new Predicate<Venta>() {

			@Override
			public boolean match(Venta venta) {
				return true;
			}
			
		});
		
		if (ventas.size() > 0) {
			for (Venta venta : ventas) {
				System.out.println(venta.getCliente().getDni() + 
						venta.getArticulo().getCod_articulo() + 
						venta.getFecha_venta());
			}
		}
		else {
			System.err.println("No existen ventas realizadas!!");
		}
		
	}
	
	/**
	 * Metodo que muestra todos los clientes que han realizado una compra en los ultimos 3 meses
	 */
	private static void ultimosClientes() {
		ObjectSet<Venta> ventas = db.query(new com.db4o.query.Predicate<Venta>(){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(Venta venta) {
				Fecha fecha = venta.getFecha_venta();
				fecha.add(Calendar.MONTH, 3);
				return fecha.getFecha().before(GregorianCalendar.getInstance());
			}
		});
		
		if (!ventas.isEmpty()) {
			for (Venta venta : ventas) {
				System.out.println(venta.getCliente().getNombre() + "\n");
			}
		}
		else {
			System.err.println("Ventas ultimosClientes: No existe ninguna venta con esos datos!");
		}

	}
	
	/**
	 * Metodo que muestra la localidad y el nombre de los clientes que han 
	 * realizado una compra de un coste mayor de 50 euros
	 */
	private static void ventasMasde50() {
		ObjectSet<Venta> ventas = db.query(new com.db4o.query.Predicate<Venta>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(Venta venta) {
				return venta.getUnidades_vendidas() * venta.getPvp_unidad() > 50;
			}
		});
		
		if (!ventas.isEmpty()) {
			System.out.println("\n********************");
			for (Venta venta : ventas) {
				System.out.println("Cliente: " + venta.getCliente().getNombre() + ", localidad: " +  venta.getCliente().getLocalidad() + ", id_venta: " + venta.getId_venta());
			}
			System.out.println("*********************\n");
		}
		else {
			System.err.println("Ventas ventaMasDe50: No existe ninguna venta con valor superior a 50€");
		}

	}
	
	private static GregorianCalendar pedirFecha(String fechaCadena) {
		String[] fechaArray = fechaCadena.split("-");
		return new GregorianCalendar(Integer.parseInt(fechaArray[0]), 
									Integer.parseInt(fechaArray[1]), 
									Integer.parseInt(fechaArray[2]));
	}

}
