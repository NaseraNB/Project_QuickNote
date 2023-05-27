module Project_QuickNote {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires java.sql;
	requires mysql.connector.java;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
}
