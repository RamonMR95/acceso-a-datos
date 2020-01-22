package accesoADatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Modelo.Alumno;

public class AlumnosDAO {

		private Connection db;
		private Statement stAlumnos;
		private ArrayList<Alumno> bufferAlumnos;
		private DefaultTableModel tmAlumnos;
		private ResultSet rsAlumnos;

		public AlumnosDAO() {
			db = Conexion.getInstance();
			try {
				stAlumnos = db.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
			}
			tmAlumnos = new DefaultTableModel();
			bufferAlumnos = new ArrayList<>();
		}

		public String mostrarAlumnos() {
	        try {
	            rsAlumnos = stAlumnos.executeQuery("select * from alumnos");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        etiquetarColumnasModelo();
	        borrarFilasModelo();
	        rellenarFilasModelo();
	        sincronizarBufferAlumnos();
	        StringBuilder datosAlumnos = new StringBuilder();
	        for (Alumno alumno : bufferAlumnos) {
	            datosAlumnos.append(alumno).append("\n");
	        }
	        return datosAlumnos.toString();
	    }

		public Alumno buscarAlumno(String id) {
			ArrayList<Alumno> alumnos = obtener("identificador", "=", id);
			if (alumnos == null) {
				return null;
			}
			return alumnos.get(0);
		}

		public boolean AñadirAlumno(String identificador, String nombre, Date f_nac,float calificacion, int coeficiente ) {
			Alumno alumno = new Alumno(identificador, nombre, f_nac, calificacion, coeficiente);
			return alta(alumno);
		}
		public boolean borrarAlumno(String id) {
			assert id != null;
			assert !id.matches("");
			assert !id.matches("[ ]+");
			List<Alumno> alumnos = obtener("Identificador", "=", id);
			if (alumnos != null) {
				try {
					bufferAlumnos.remove(alumnos.get(0));
					String sql = "DELETE FROM alumnos WHERE identificador ='" + id + "'";
					stAlumnos.executeUpdate(sql);
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return false;
			
		}

		public String MostrarAlumnosIQ(int coeficiente) {
			ArrayList<Alumno> alumnos = obtener("coeficiente_de_inteligencia", ">", String.valueOf(coeficiente));
			StringBuilder datosAlumnos = new StringBuilder();
			for (Alumno alumno : alumnos) {
				datosAlumnos.append(alumno).append("\n");
			}
			return datosAlumnos.toString();
		}

		public ArrayList<Alumno> obtener(String id, String comparador, String campo) {
			assert id != null;
			ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
			ejecutarConsulta(id, comparador, campo);
			etiquetarColumnasModelo();
			borrarFilasModelo();
			rellenarFilasModelo();
			sincronizarBufferAlumnos();
			if (bufferAlumnos.size() > 0) {
				for (int i = 0; i < bufferAlumnos.size(); i++) {
					alumnos.add(bufferAlumnos.get(i));
				}
				return alumnos;
			}
			return null;
		}
		

		private void ejecutarConsulta(String campo, String comparador, String id) {
			try {
				
				rsAlumnos = stAlumnos
							.executeQuery("select * from alumnos where " + campo + " " + comparador + " '" + id + "'");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		private void etiquetarColumnasModelo() {
			try {
				ResultSetMetaData metaDatos = this.rsAlumnos.getMetaData();
				// numero total de columnas
				int numCol = metaDatos.getColumnCount();
				// etiqueta de cada columna
				Object[] etiquetas = new Object[numCol];
				for (int i = 0; i < numCol; i++) {
					etiquetas[i] = metaDatos.getColumnLabel(i + 1);
				}
				// Incorpora array de etiquetas en el TableModel.
				this.tmAlumnos.setColumnIdentifiers(etiquetas);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		private void borrarFilasModelo() {
			while (this.tmAlumnos.getRowCount() > 0) {
				this.tmAlumnos.removeRow(0);
			}
		}

		private void rellenarFilasModelo() {
			Object[] datosFila = new Object[this.tmAlumnos.getColumnCount()];
			try {
				while (rsAlumnos.next()) {
					for (int i = 0; i < this.tmAlumnos.getColumnCount(); i++) {
						datosFila[i] = this.rsAlumnos.getObject(i + 1);
					}
					this.tmAlumnos.addRow(datosFila);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		private void sincronizarBufferAlumnos() {
			bufferAlumnos.clear();
			for (int i = 0; i < tmAlumnos.getRowCount(); i++) {
				try {
					String identificador = (String) tmAlumnos.getValueAt(i, 0);
					String nombre = (String) tmAlumnos.getValueAt(i, 1);
					Date fecha_de_nacimiento = (Date) tmAlumnos.getValueAt(i, 2);
					float calificacion = (float) tmAlumnos.getValueAt(i, 3);
					int coeficiente = (Integer) tmAlumnos.getValueAt(i, 4);
					Alumno alumno = new Alumno(identificador, nombre, fecha_de_nacimiento, calificacion, coeficiente);
					bufferAlumnos.add(alumno);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		public boolean alta(Alumno alumno){
			String sqlQuery = obtenerConsultaAlta(alumno);
			if (obtener("identificador","=",alumno.getIdentificador()) == null) {
				try {
					stAlumnos.executeUpdate(sqlQuery);
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return false;
		}

		@SuppressWarnings("deprecation")
		private String obtenerConsultaAlta(Alumno alumno) {
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(
					"INSERT INTO alumnos (identificador, nombre, fecha_de_nacimiento, calificacion,coeficiente_de_inteligencia) VALUES "
							+ "('" + alumno.getIdentificador() + "','" + alumno.getNombre()+ "','"
							+ alumno.getFechaNacimiento().getYear() + "-" +  alumno.getFechaNacimiento().getMonth() + "-" +  alumno.getFechaNacimiento().getDay()
							+ "'," + alumno.getCalificacion()+ ","
							+ alumno.getCoeficienteIntelectual()+")");
			return sqlQuery.toString();
		}


	}

