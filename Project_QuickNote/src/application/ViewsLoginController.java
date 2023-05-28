package application; 

// Paquetes importados
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.Statement;


public class ViewsLoginController implements Initializable{

	// Variables que estan inicializadas en el FXML llamado Login.
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
	
	private int longinUserId;
	
	private String registeredUsername;
	private String registeredPassword;;
	
	
	// Metodo que cuando el usuario pulse en el boton de sign in el usuario podra ver la vista del registro para poder registarse.
	public void signInButtonOnActivion(ActionEvent event) {
		createAccountForm();
	}
	
	
	public void loginButtonOnActivion(ActionEvent event) throws IOException {
		
		String enterdUsername = usernameFx.getText();
		String enterdPassword = passwordFx.getText();
		
		if (usernameFx.getText().isBlank() == false && passwordFx.getText().isBlank() == false) {
			validateLogin();
		} else {
			loginMessage.setText("Please enter username and password");
		}
		
		
		try {
		
			// Miramos si los datos del usuario son los mismo que el de registro
			if (enterdUsername.equals(registeredUsername) && enterdPassword.equals(registeredPassword)) {
				// cerramos la ventana de inicio de sescion
				Stage stage = (Stage) loginButton.getScene().getWindow();
				stage.close();
				
				// Mostramos la ventana de la lista de ntas
				FXMLLoader notesPadLoader = new FXMLLoader(getClass().getResource("/view/NotePadView.fxml"));
				Parent notesPadRoot = notesPadLoader.load();
				NotePadController notesPadController = notesPadLoader.getController();
				
				// Pasamos los datos necesario para poder saber que lista tenemos que ver
				notesPadController.setLonginUserId(longinUserId);
				
				// Mostramos la ventana de la lista de notas
				Stage notesPadStage = new Stage();
				notesPadStage.setScene(new Scene(notesPadRoot));
				notesPadStage.show();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		
	}
	
	@FXML
	public void cancelButtonOnAction(ActionEvent event) {
		
		// Cerramos el login
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL url, ResourceBundle arg1) {
		// Carga la image del logo atravez de la ruta donde se encuantra.
		
		File logoFile = new File("/home/nasera/git/Repository_YN/Project_QuickNote/Image/Captura desde 2023-05-26 06-21-34.png");
		Image logoImage = new Image(logoFile.toURI().toString());
		imageLogo.setImage(logoImage);
		
		// Carga la image del icono atravez de la ruta donde se encuantra.
		
		File iconFile = new File("/home/nasera/git/Repository_YN/Project_QuickNote/Image/Captura desde 2023-05-26 06-35-02.png");
		Image iconImage = new Image(iconFile.toURI().toString());
		ImageIcon.setImage(iconImage);
		
	}
	
	public void validateLogin() {
		
		// Careamos una instancia de la clase DatabaseConnection.
		DatabaseConnection connectNow = new DatabaseConnection();
		
		// Obtenermos una conexión a la base de datos.
		Connection connectDB = connectNow.getConnection();
		
		// Esta consulta sql nos permite verificar el inicio de sesión utilizando el nombre del usuario y la constraseña.
		String verifyLogin = "SELECT * FROM User WHERE username = '" + usernameFx.getText() + "' and password = '" 
		+ passwordFx.getText() + "'";

		
		try {
			
			// Hemos creado una declaración para ejecutar la consulta.
			Statement statement =  connectDB.createStatement();
			
			// Nos permite ejecutar la consulta para obtener el resultado.
			ResultSet queryResult = statement.executeQuery(verifyLogin);


			// Si queryResult verifica que el resultado de la consulta es igual a 1 significa que a habido coincidencia
			if (queryResult.next()) {
				
				// Mostramos un mensaje de felicitacion si el inicio de sesión es válido.
				loginMessage.setText("congratulations!");
				
				// Obtenemos el ID del usuario que inicio sesión 
				longinUserId = queryResult.getInt("IdUser");
				
				// cerramos la ventana de inicio de sescion
				Stage stage = (Stage) loginButton.getScene().getWindow();
				stage.close();
				
				// Mostramos la ventana de la lista de ntas
				FXMLLoader notesPadLoader = new FXMLLoader(getClass().getResource("/view/NotePadView.fxml"));
				Parent notesPadRoot = notesPadLoader.load();
				NotePadController notesPadController = notesPadLoader.getController();
				
				// Pasamos los datos necesario para poder saber que lista tenemos que ver
				notesPadController.setLonginUserId(longinUserId);
				
				// Mostramos la ventana de la lista de notas
				Stage notesPadStage = new Stage();
				notesPadStage.setScene(new Scene(notesPadRoot));
				notesPadStage.show();
				
			} else {
				
				// Sino mostrara un mensaje de error si el inicio de sesion no es correcto.
				loginMessage.setText("Invalid login. Please try again");
			}
			
			
		} catch (Exception e) {
			
			// Manejamos la excepción que pueda ocurrir durante la ejecución.
			e.printStackTrace();

		} finally {
			try {
				
				// Cerranomos la sesion con la base de datos con el bloque finally
				if (connectDB != null) {
					connectDB.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	// Metodo que llama la vista de registro
	public void createAccountForm() {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Register.fxml"));
			
			Parent root = loader.load();
			
			
			RegisterController registerController = loader.getController();
			registerController.setLonginUserId(longinUserId);
			
			Stage registerStage = new Stage();
			registerStage.initStyle(StageStyle.UNDECORATED);
			registerStage.setScene(new Scene(root, 520, 580));
			registerStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	public void setRegisteredCredentials(String username, String password) {
		registeredUsername = username;
        registeredPassword = password;
		
	}
	
	
	
}













