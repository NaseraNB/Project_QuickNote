package application;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;


public class ViewsLoginController implements Initializable{

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
	private void eventKey(KeyEvent event) {
		
	}
	
	@FXML
	private void eventAction(ActionEvent event) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
