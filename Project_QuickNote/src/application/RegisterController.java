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
			correctMessageSignIn.setText("");
			
		} else {
			
			// Comprovamos lo mismo que el anterio pero por separado, cada uno tendra mensaje diferentes.
			
			// Nombre de la persona
			if (firstnameSignin.getText().isEmpty()) {
				signinMessage.setText("Please enter your first name.");
				correctMessageSignIn.setText("");
			
			// Nombre de usuario
			} else if (usernameSignin.getText().isEmpty()) {
				signinMessage.setText("Please enter a username");
				correctMessageSignIn.setText("");
				
			// Gmail	
			} else if (emailSignin.getText().isEmpty()) {
				signinMessage.setText("Please enter your email address.");
				correctMessageSignIn.setText("");
				
			// Contraseña	
			} else if (passwordSignin.getText().isEmpty()) {
				signinMessage.setText("Please enter a password");
				correctMessageSignIn.setText("");
			
			// Confirmar la contraseña
			} else if (confirmPasswordSignin.getText().isEmpty()) {
				signinMessage.setText("Please confirm your password.");
				correctMessageSignIn.setText("");
				
			/* Ahora lo que comprobaremos es si lo que introduce el usuario en los campos son correctos. sino saltara un mensaje de error.*/	
				
			// Nombre de la persona solamente tiene que contener letras sino saldra un mensaje de error.
			} else if (!firstnameSignin.getText().matches("[a-zA-Z]+")) {
				signinMessage.setText("Invalid name. Please enter only letters."); 
				correctMessageSignIn.setText("");
			

			// Tipos de errores en el gmail.
			
			// Tiene que contener un @.
			} else if (!emailSignin.getText().matches(".*@.*")) {
				signinMessage.setText("Invalid email. An email must contain @.");
				correctMessageSignIn.setText("");
			
			// Tiene que contener un ".".
			} else if (!emailSignin.getText().matches(".*\\..*")) { 
				signinMessage.setText("Invalid email. An email must contain a period '.' for the domain.");
				correctMessageSignIn.setText("");
			
			// No puede contener más de un @.
			} else if (emailSignin.getText().split("@").length > 2) {
				signinMessage.setText("Invalid email. An email can only contain one @");
				correctMessageSignIn.setText("");
			
			// Después del punto del dominio al menos tiene que escribir 2 caracteres.
			} else if (!emailSignin.getText().matches(".+@.+\\..{2,63}")) {
				signinMessage.setText("Invalid email. The domain must have a length between 2 and 63.");
				correctMessageSignIn.setText("");
				
				
			// Tipos de errores en la contraseña.
				
			// La contraseña tiene que tener 8 caracteres
			} else if (passwordSignin.getText().length() < 8) {
				signinMessage.setText("Invalid password. Must be at least 8 characters");
				correctMessageSignIn.setText("");
			
			// En la contraseña tiene que tener al menos una mayuscula.
			} else if (!passwordSignin.getText().matches(".*[A-Z].*")) {
				signinMessage.setText("A password must have uppercase letters.");
				correctMessageSignIn.setText("");
			
			// En la contraseña tiene que tener al menos una minuscula.
			} else if (!passwordSignin.getText().matches(".*[a-z].*")) {
				signinMessage.setText("A password must have lowercase letters.");
				correctMessageSignIn.setText("");
				
			// En una contraseña tiene que tener números.
			} else if (!passwordSignin.getText().matches(".*\\d.*")) {
				signinMessage.setText("A password must have numbers.");
				correctMessageSignIn.setText("");
			
			// En una contraseña tiene que tener caracteres especiales.
			} else if (!passwordSignin.getText().matches(".*\\W.*")) {
				signinMessage.setText("A password must have special characters.");
				correctMessageSignIn.setText("");
			
			// Una contraseña no puede tener espacios.
			} else if (passwordSignin.getText().matches(".*\\s.*")) {
				signinMessage.setText("The password should not have spaces.");
				correctMessageSignIn.setText("");
			
			// Cuando se confirma la contraseña significa que es la misma que tiene contraseña sino saldra un mensaje de error
			} else if (!passwordSignin.getText().equals(confirmPasswordSignin.getText())) {
				signinMessage.setText("Passwords do not match. Please enter the same password in both fields.");
				correctMessageSignIn.setText("");
				
			} else {
				// Sino sera correcto.
				correctMessageSignIn.setText("User has been registered successfully!");
				signinMessage.setText(" ");
			}
		}
	}
}