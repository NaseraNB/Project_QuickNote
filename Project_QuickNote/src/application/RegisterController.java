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
				
			/* Ahora lo que comprobaremos es si lo que introduce el usuario en los campos son correctos. sino saltara un mensaje de error.*/	
				
			// Nombre de la persona solamente tiene que contener letras sino saldra un mensaje de error.
			} else if (!firstnameSignin.getText().matches("[a-zA-Z]+")) {
				signinMessage.setText("Invalid name. Please enter only letters."); 
			
			
			// Tipos de errores en el gmail.
			
			// Tiene que contener un @.
			} else if (!emailSignin.getText().matches(".*@.*")) {
				signinMessage.setText("Invalid email. An email must contain @.");
			
			// Tiene que contener un ".".
			} else if (!emailSignin.getText().matches(".*\\..*")) { 
				signinMessage.setText("Invalid email. An email must contain a period '.' for the domain.");
			
			// No puede contener más de un @.
			} else if (emailSignin.getText().split("@").length > 2) {
				signinMessage.setText("Invalid email. An email can only contain one @");
			
			// Después del punto del dominio al menos tiene que escribir 2 caracteres.
			} else if (!emailSignin.getText().matches(".+@.+\\..{2,63}")) {
				signinMessage.setText("Invalid email. The domain must have a length between 2 and 63.");
				
				
			// Tipos de errores en la contraseña.
				
			} else {
				signinMessage.setText("Correct");
			}
		}
		
	
		/*
		 * else {
				signinMessage.setText("");
				correctMessageSignIn.setText("User registration successfully!");
			}
			
				// Tipos de errores en la contraseña.
				
			// La contraseña tiene que tener 8 caracteres
			} else if (passwordSignin < 8) {
				signinMessage.setText("Invalid password. Must be at least 8 characters");
		 * 
		// firstnameSignin.getText().isEmpty() --> Si la entrada del usuario esta vacio.
		if (firstnameSignin.getText().isEmpty() || !firstnameSignin.getText().matches("[a-zA-Z]+")) {
			signinMessage.setText("Invalid name. Please enter only letters.");
		} else {
			correctMessageSignIn.setText("User has been registered successfully!");
		}*/
		
	}

}
