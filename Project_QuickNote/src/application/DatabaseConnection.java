package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	public Connection getConnection() {
		Connection connectionDB = null;
		
		try {
			
			// Establecer conexión con la base de datos
			connectionDB = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8620870","sql8620870","Br7vTpCslf");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connectionDB;
	}
	
}
