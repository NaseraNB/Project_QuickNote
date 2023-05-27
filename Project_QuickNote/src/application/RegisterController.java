package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable{

	// Variables y botones.
	@FXML
	private ImageView iconSignin;
	
	@FXML
	private Button closeButtonS; 
	
	@FXML
	private Label correctMessageSignIn;
	
	@FXML
	private Label signinMessage;
	
	@FXML
	private TextField firstnameSignin;
	
	@FXML 
	private TextField usernameSignin;
	
	@FXML
	private TextField emailSignin;
	
	@FXML
	private PasswordField passwordSignin;
	
	@FXML
	private PasswordField confirmPasswordSignin;
	
	
	@Override
	public void initialize(URL url, ResourceBundle arg1) {
		// Carga la image del icon atravez de la ruta donde se encuantra.
		
		File iconSignInFile = new File("/home/nasera/git/Repository_YN/Project_QuickNote/Image/icon4.png");
		Image iconSignInImage = new Image(iconSignInFile.toURI().toString());
		iconSignin.setImage(iconSignInImage);		
	}
	
	@FXML
	public void cancelButtonOnAction(ActionEvent event) {
		
		// Cerramos el registro, si el usuario pulsa el boton de close le llevará  a la vista del login.
		Stage stage = (Stage) closeButtonS.getScene().getWindow();
		stage.close();
	}
	
	public void registerButtonOnAction(ActionEvent event) {
		resgisterUser();
	}
	
	// Metodo que contendra todo los tipos de error que puden surgir al registrarse.
	public void resgisterUser() {
		
		// Comprovamos si todos los campos están vaciós, si es hacin se mostrar un mensaje de error.
		if (firstnameSignin.getText().isEmpty() && usernameSignin.getText().isEmpty()
				&& emailSignin.getText().isEmpty() && passwordSignin.getText().isEmpty()
				&& confirmPasswordSignin.getText().isEmpty()) {
			
			signinMessage.setText("Please enter your information in the fields."); // Mensaje de error que aparecerá.
			
		} else {
			
			// Comprovamos lo mismo que el anterio pero por separado, cada uno tendra mensaje diferentes.
			
			// Nombre de la persona
			if (firstnameSignin.getText().isEmpty()) {
				signinMessage.setText("Please enter your first name."); 
			
			// Nombre de usuario
			} else if (usernameSignin.getText().isEmpty()) {
				signinMessage.setText("Please enter a username");
				
			// Gmail	
			} else if (emailSignin.getText().isEmpty()) {
				signinMessage.setText("Please enter your email address.");
				
			// Contraseña	
			} else if (passwordSignin.getText().isEmpty()) {
				signinMessage.setText("Please enter a password");
			
			// Confirmar la contraseña
			} else if (confirmPasswordSignin.getText().isEmpty()) {
				signinMessage.setText("Please confirm your password.");
			}
		}
		
	
		/*
		// firstnameSignin.getText().isEmpty() --> Si la entrada del usuario esta vacio.
		if (firstnameSignin.getText().isEmpty() || !firstnameSignin.getText().matches("[a-zA-Z]+")) {
			signinMessage.setText("Invalid name. Please enter only letters.");
		} else {
			correctMessageSignIn.setText("User has been registered successfully!");
		}*/
		
	}

}
