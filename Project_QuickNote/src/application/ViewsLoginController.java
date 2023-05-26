package application;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;


public class ViewsLoginController {

	// Variables que vamos a utilizar que an sido inicializadas en Login.FXML.
	@FXML
	private TextField usernameFx;
	
	@FXML
	private PasswordField passwordFx;
	
	@FXML
	private Button loginButton;
	
	@FXML
	private Button cancelButton;
	
	@FXML
	private Button registerButton;
	
	@FXML
	
	public void cancelButtonOnAction(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	
}
