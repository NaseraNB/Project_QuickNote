package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class User extends Application{

	@Override
	public void start(Stage primaryStage) throws IOException {
	
		// Cargar el archivo fxml
		
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		
		// Creamos una escena para poder mostrar el login
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
