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

	@FXML
	private ImageView iconSignin;
	
	@FXML
	private Button closeButtonS; 
	
	
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

}
