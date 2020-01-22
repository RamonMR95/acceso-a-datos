package vista;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Modelo.Alumno;
import accesoADatos.AlumnosDAO;



public class Programa extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea;
	private JScrollPane scrollPane;
	
	private AlumnosDAO alumnosDAO;
	
	private JPanel panelBotones;
	
	private JButton btnMostrarDatos;
	private JButton btnBuscarAlumno;
	private JButton btnAddAlumno;
	private JButton btnBorrarAlumno;
	private JButton btnMostrarAlumnosIQ;
	private JButton btnSalir;
	
	private JTextField campoTextoId;
	private JTextField campoTextoNombre;
	private JTextField campoTextoFechaNacimiento;
	private JTextField campoTextoCalificacion;
	private JTextField campoTextoCI;

	public Programa() {
		this.init();
	}
	
	public void init() {
		setTitle("Alumnos");
		setSize(1200, 900);
		this.getContentPane().setBackground(Color.CYAN);
		this.getContentPane().setLayout(new GridBagLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addTextArea();
		this.initCamposTexto();
		this.initPanelBotones();
		this.alumnosDAO = new AlumnosDAO();
		setResizable(false);
		setVisible(true);
	}
	
	public void initPanelBotones() {
		this.panelBotones = new JPanel();
		this.panelBotones.setLayout(new FlowLayout());
		this.panelBotones.setBackground(Color.CYAN);
		this.initBotones();
		GridBagConstraints gbConstraints = new GridBagConstraints();
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 2;
		gbConstraints.gridwidth = 3;
		gbConstraints.gridheight = 1;
		this.panelBotones.add(btnMostrarDatos);
		this.getContentPane().add(panelBotones, gbConstraints);
		
	}
	
	public void initBotones() {
		btnMostrarDatos = new JButton("Mostrar datos");
		btnMostrarDatos.addActionListener(this);
		
		btnBuscarAlumno = new JButton("Buscar Alumno");
		btnBuscarAlumno.addActionListener(this);
		
		btnAddAlumno = new JButton("Añadir alumno");
		btnAddAlumno.addActionListener(this);
		
		btnBorrarAlumno = new JButton("Borrar alumno");
		btnBorrarAlumno.addActionListener(this);
		
		btnMostrarAlumnosIQ = new JButton("Mostrar CI");
		btnMostrarAlumnosIQ.addActionListener(this);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(this);
		
		this.addBotones();
	}
	
	public void addBotones() {
		this.panelBotones.add(btnMostrarDatos);
		this.panelBotones.add(btnBuscarAlumno);
		this.panelBotones.add(btnAddAlumno);
		this.panelBotones.add(btnBorrarAlumno);
		this.panelBotones.add(btnMostrarAlumnosIQ);
		this.panelBotones.add(btnSalir);
	}
	
	
	public void addTextArea() {
		this.textArea = new JTextArea();
		this.scrollPane = new JScrollPane(textArea);
		this.textArea.setRows(20);
		this.textArea.setColumns(40);
		this.textArea.setBackground(Color.lightGray);
		GridBagConstraints gbConstraints = new GridBagConstraints();
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;
		gbConstraints.gridwidth = 3;
		gbConstraints.gridheight = 1;
		gbConstraints.weighty = 0.5;
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		this.getContentPane().add(scrollPane, gbConstraints);
	}
	
	public void initCamposTexto() {
		this.campoTextoId = new JTextField();
		this.campoTextoFechaNacimiento = new JTextField();
		this.campoTextoNombre = new JTextField();
		this.campoTextoCalificacion = new JTextField();
		this.campoTextoCI = new JTextField();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		limpiarTextArea();
		if (event.getSource() == btnMostrarDatos) {
			textArea.append(alumnosDAO.mostrarAlumnos());
		}
		else if (event.getSource() == btnBuscarAlumno) {
			String id = this.mostrarDialogEntrada("Introduce el id del alumno a buscar", "Buscar alumno", JOptionPane.YES_NO_OPTION);
			Alumno alumno = alumnosDAO.buscarAlumno(id);
			
			if (id != null) {
				if (alumno != null) {
					this.textArea.setText(alumno.toString());
				}
				else {
					this.mostrarError("No existe ningun alumno en la base de datos con id: " + id);
				}
			}

		}
		else if (event.getSource() == btnAddAlumno) {
			limpiarTextArea();
			int input = JOptionPane.showConfirmDialog(getContentPane(), 
					this.addMensajesAlta(), 
					"Añadir un nuevo alumno", 
					JOptionPane.OK_CANCEL_OPTION);
			
			if (input == 0) {
				try {
					String identificador = campoTextoId.getText();
					String nombre = campoTextoNombre.getText();
					String fechaNacimiento = campoTextoFechaNacimiento.getText();
					float calificacion = Float.parseFloat(campoTextoCalificacion.getText());
					int coeficienteIntelectual = Integer.parseInt(campoTextoCI.getText());
						
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					
					Date fecha;
					fecha = formatter.parse(fechaNacimiento);
					
					boolean usuarioAlta = alumnosDAO.AñadirAlumno(identificador, nombre, fecha, calificacion, coeficienteIntelectual);
					if (usuarioAlta) {
						JOptionPane.showMessageDialog(getContentPane(), "Usuario " + nombre +  " dado de alta", "Alta de alumno", JOptionPane.OK_OPTION);
					}
					else {
						this.mostrarError("El usuario ya existe en la db");
					}
				} 
				catch (Exception e) {
					this.mostrarError("Formato alumno inválido");
				}

			}
				
		}
		else if (event.getSource() == btnBorrarAlumno) {
			limpiarTextArea();
			String id = JOptionPane.showInputDialog(getContentPane(), "Introduce el id del usuario a borrar", "Borrar un usuario", JOptionPane.WARNING_MESSAGE);
			Alumno alumno = this.alumnosDAO.buscarAlumno(id);
			
			if (id != null) {
				if (alumno != null) {
					int option = JOptionPane.showConfirmDialog(getContentPane(), this.addMensajesBaja(alumno.getIdentificador(), 
							alumno.getNombre(), 
							alumno.getFechaNacimiento(), 
							alumno.getCalificacion(), 
							alumno.getCoeficienteIntelectual()), 
							"¿Está seguro?", 
							JOptionPane.OK_CANCEL_OPTION);
					
					if (option == 0) {
						this.alumnosDAO.borrarAlumno(id);
						JOptionPane.showMessageDialog(getContentPane(), "Alumno con id: " + id + " eliminado");
					}
					
				}
				else {
					this.mostrarError("El usuario no existe en la db");
				}
				
			}
			
		}
		else if (event.getSource() == btnMostrarAlumnosIQ) {
			limpiarTextArea();
			String ci = this.mostrarDialogEntrada("Introduce el CI", "Comparación de CI de alumnos", JOptionPane.INFORMATION_MESSAGE);
			
				try {
					int coeficiente = Integer.parseInt(ci);
					String alumnos = alumnosDAO.MostrarAlumnosIQ(coeficiente);
					
					if (alumnos.length() > 0) {
						textArea.append(alumnos);
					}
					else {
						this.mostrarError("No existe ningun alumno con un CI superior al introducido");
					}
					
				} catch (Exception e) {
					this.mostrarError("Introduce un número por favor");
				}
				
		}
		else if (event.getSource() == btnSalir) {
			int salir = JOptionPane.showConfirmDialog(getContentPane(),  "¿Está seguro de que quiere abandonar la app?", "salir", JOptionPane.OK_CANCEL_OPTION);
			if (salir == 0) {
				System.exit(0);
			}
		}
	}
	
	public String mostrarDialogEntrada(String mensaje, String titulo, int tipo) {
		return JOptionPane.showInputDialog(getContentPane(), mensaje, titulo, tipo);
	}
	
	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(getContentPane(), mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public Object[] addMensajesAlta() {
		Object[] mensajes = {
				"Identificador: ", campoTextoId,
				"Nombre: ", campoTextoNombre,
				"Fecha de Nacimiento: (yyyy-MM-dd) ", campoTextoFechaNacimiento,
				"Calificación: ", campoTextoCalificacion,
				"Coeficiente intelectual: ", campoTextoCI
		};
		return mensajes;
	}
	
	public Object[] addMensajesBaja(String id, String nombre, Date fechaNac, float calificacion, int ci) {
		Object[] mensajes = {
				"Identificador: ", id,
				"Nombre: ", nombre,
				"Fecha de Nacimiento: (yyyy-MM-dd) ", fechaNac,
				"Calificación: ", calificacion,
				"Coeficiente intelectual: ", ci
		};
		return mensajes;
	}
	
	public void limpiarTextArea() {
		textArea.setText("");
	}
	
}


