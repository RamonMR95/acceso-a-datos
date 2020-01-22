package com.ramonmr95.app;

import org.w3c.dom.*;

import com.ramonmr95.app.connection.Conexion;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

public class CrearTablasXML {

	static Connection conn = Conexion.getInstance();
	
	public static void main(String[] args) {
		try {
			DatabaseMetaData databaseMetaData = conn.getMetaData();
			ResultSet rsTablas = databaseMetaData.getTables(null, null, "%", null);
			
			while (rsTablas.next()) {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				
				DocumentBuilder builder = factory.newDocumentBuilder();
				DOMImplementation implementation = builder.getDOMImplementation();
				Document document = implementation.createDocument(null, rsTablas.getString(3), null);
				document.setXmlVersion("1.0");
				
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery("SELECT * FROM " + rsTablas.getString(3));
				int numCol = rs.getMetaData().getColumnCount();
				int counter = 1;
				
				while (rs.next()) {
					Element raiz = document.createElement(getNombreElemento(rsTablas.getString(3)));
					raiz.setAttribute("id", String.valueOf(counter));
					
					for (int i = 1; i < numCol; i++) {
						CrearElemento(rs.getMetaData().getColumnName(i), rs.getString(i), raiz, document);
					}
					document.getDocumentElement().appendChild(raiz);
					counter++;
				}

				Source source = new DOMSource(document);
				Result result = new StreamResult(new java.io.File(rsTablas.getString(3) + ".xml"));
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.transform(source, result);
			}
			
		} 
		catch (Exception e) {
			System.out.println("Error metadata : " + e.getMessage());
		}
	}
	
	public static void CrearElemento(String datoEmple, String valor, Element raiz, Document document) {
		Element elem = document.createElement(datoEmple);
		Text text = document.createTextNode(valor);
		raiz.appendChild(elem);
		elem.appendChild(text);
	}
	
	public static String getNombreElemento(String nombre) {
		if (nombre.endsWith("es")) {
			return nombre.substring(0, nombre.length() - 2);
		}
		return nombre.substring(0, nombre.length() - 1);
	}

}
