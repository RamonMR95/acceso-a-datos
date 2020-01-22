package com.ramonmr95.app;

import org.w3c.dom.*;

import com.ramonmr95.app.models.dao.Conexion;
import com.ramonmr95.app.models.entities.Equipo;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CrearEquipoXml {

	static Connection conn = Conexion.getInstance();

	public static void main(String args[]) throws IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, "equipos", null);
			document.setXmlVersion("1.0");

			List<Equipo> equipos = queryEquipos();

			for (Equipo equipo : equipos) {
				Element raiz = document.createElement("equipo");
				CrearElemento("nombre", equipo.getNombre(), raiz, document);
				CrearElemento("ciudad", equipo.getCiudad(), raiz, document);
				CrearElemento("conferencia", equipo.getConferencia(), raiz, document);
				CrearElemento("division", equipo.getDivision(), raiz, document);
				document.getDocumentElement().appendChild(raiz);
			}

			Source source = new DOMSource(document);
			Result result = new StreamResult(new java.io.File("equipos-nba.xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);

		} catch (Exception e) {
			System.err.println("Error: " + e);
		}

	}

	public static void CrearElemento(String datoEmple, String valor, Element raiz, Document document) {
		Element elem = document.createElement(datoEmple);
		Text text = document.createTextNode(valor);
		raiz.appendChild(elem);
		elem.appendChild(text);
	}

	public static List<Equipo> queryEquipos() {
		String query = "SELECT * FROM EQUIPOS";
		List<Equipo> equipos = new ArrayList<>();

		try (Statement stEquipos = conn.createStatement(); 
				ResultSet result = stEquipos.executeQuery(query)) {

			while (result.next()) {
				equipos.add(new Equipo(result.getString(1), result.getString(2), result.getString(3), result.getString(4)));
			}

			return equipos;
		} 
		catch (Exception e) {
			System.out.println("Error query: 2" + e.getMessage());
			return null;
		}
	}
}
