package application; // Package name.

// Imported packages
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class that connects with the database.
 * 
 * @author Yarií Soto - Nasera Boulehoual.
 * @version 0.1 May 31, 2023
 * */

public class DatabaseConnection {

	/**
	 * We get the connection with the database.
	 * 
	 * @return The database connection.
	 * */
	public Connection getConnection() {
		Connection connectionDB = null;
		
		try {
			
			// Establish connection with the database
			connectionDB = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8622418","sql8622418","ckypqL8v3e");
			
		} catch (Exception e) {
			e.printStackTrace(); // We handle the exception that may occur during execution.
		}
		return connectionDB; // The database connection.
	}
}