
import java.awt.EventQueue;
import java.sql.Connection;

import accesoADatos.*;
import vista.*;

public class Main {

	public static void main(String[] args) {
		
		/* Creación de la conexión a la base de datos */
		Connection conn = Conexion.getInstance();

        EventQueue.invokeLater(() -> new Programa());
        
	}

}
