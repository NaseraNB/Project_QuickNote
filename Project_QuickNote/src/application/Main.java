package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar el archivo fxml
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        
        // Crear una escena para mostrar el contenido del archivo fxml
        Scene scene = new Scene(root);

        // Configurar el escenario principal
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

