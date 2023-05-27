package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;


public class ViewsLoginController implements Initializable{

	// Variables que utilitzarem que estan inicialitzades a Login.FXML.
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
	private Label loginMessage;
	
	@FXML 
	private ImageView imageLogo;
	
	@FXML 
	private ImageView ImageIcon;
	
	public void loginButtonOnActivion(ActionEvent event) {
		
		if (usernameFx.getText().isBlank() == false && passwordFx.getText().isBlank() == false) {
			validateLogin();
		} else {
			loginMessage.setText("Please enter username and password");
		}
	}
	
	@FXML
	public void cancelButtonOnAction(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL url, ResourceBundle arg1) {
		File logoFile = new File("/home/nasera/git/Repository_YN/Project_QuickNote/Image/Captura desde 2023-05-26 06-21-34.png");
		Image logoImage = new Image(logoFile.toURI().toString());
		imageLogo.setImage(logoImage);
		
		File iconFile = new File("/home/nasera/git/Repository_YN/Project_QuickNote/Image/Captura desde 2023-05-26 06-35-02.png");
		Image iconImage = new Image(iconFile.toURI().toString());
		ImageIcon.setImage(iconImage);
		
	}
	
	public void validateLogin() {
		
	}
}
