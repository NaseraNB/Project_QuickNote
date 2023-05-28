package application;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import model.Note;

/**
 * 
 * @author Yarií Soto - Nasera 
 *
 */
public class NotePadController implements Initializable{
	
	//Variables from NoteView
	@FXML
	private MenuBar menuBarNotePad;
	
	@FXML
	private Menu openExternalNote;
	
	@FXML
	private Button newNote;
	
	@FXML
	private Menu editNote;
	
	@FXML
	private Menu deleteNote;
	
	@FXML
	private Menu exitNotePad;
	
	@FXML
	private ScrollBar scrollNotes;
	
	@FXML
	private ListView<String> notesListView;
	
	/**
	 * ObservableList to store the note objects.
	 */
	private ObservableList<Note> notes;
	
	/**
	 * ObservableList to store the notes title.
	 */
	private ObservableList<String> arrayListTitles = FXCollections.observableArrayList();;
	
	private int longinUserId = 0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		notes = FXCollections.observableArrayList();
		
		// Obtener las notas de usuario desde la base de datos
		try {
			fetchUserNotes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (Note note : notes) {
			arrayListTitles.add(note.getTitle());
		} 
		
		notesListView.setItems(arrayListTitles);
		notesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
	
	}
	
	// Metodo para obtener las notas creadas del usuario desde la base de datos.
	private void fetchUserNotes() throws IOException{
		try {
			
			Connection connectionDB = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8620870","sql8620870","Br7vTpCslf");
			Statement noteStatement = connectionDB.createStatement();
			
			ResultSet resultSet = noteStatement.executeQuery("SELECT title, body FROM Note WHERE IdUser = " + longinUserId);
			
			while (resultSet.next()) {
				String title = resultSet.getString("title");
				String body = resultSet.getString("body");
				notes.add(new Note(title, body));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method to open an external note.
	 * @param event
	 */
	public void OpenExtNote(ActionEvent event) {
		//
		
		/*File logoFile = new File();
		Image logoImage = new Image(logoFile.toURI().toString());
		imageLogo.setImage(logoImage);*/
		
		// Carga la image del icono atravez de la ruta donde se encuantra.
		
		/*File iconFile = new File("/home/nasera/git/Repository_YN/Project_QuickNote/Image/Captura desde 2023-05-26 06-35-02.png");
		Image iconImage = new Image(iconFile.toURI().toString());
		ImageIcon.setImage(iconImage);*/
	}
	
	/**
	 * Method to change to Note windows.
	 * @param event
	 */
	@FXML
	private void newNoteAction(ActionEvent event) {
		openNoteWindows(null);
	}
	
	/**
	 * Method to open a note editor.
	 * @param event - on click.
	 */
	@FXML
	private void editNoteAction(ActionEvent event) {
		int selectedIndex = notesListView.getSelectionModel().getSelectedIndex();
		
		if (selectedIndex >= 0) {
			Note selectedNote = notes.get(selectedIndex);
			openNoteWindows(selectedNote);
		}
	}
	
	/**
	 * Method to open the note windows.
	 */
	public void openNoteWindows(Note note) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/NoteView.fxml"));
			Parent windows = loader.load();
			NoteController noteController = loader.getController();
			noteController.setNote(note);
			noteController.setNotePadController(this);
			Scene scene = new Scene(windows);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**		
	 * Method to delete a note.
	 * @param event
	 */
	public void deleteNote(ActionEvent event) {
		int selectedIndex = notesListView.getSelectionModel().getSelectedIndex();
		
		if(selectedIndex >= 0) {
			notes.remove(selectedIndex);
			arrayListTitles.remove(selectedIndex);
			
			
			// Eliminiamos la nota de la base de datos.
			Note selectedNote = notes.get(selectedIndex);
			openNoteWindows(selectedNote);
			
		}
	}
	
	
	/**
	 * Method to close the NotePad.
	 * @param event - on click.
	 */
	@FXML
	private void exitNotePad(ActionEvent event) {
		Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
	}
	
	
	public void saveNote(Note note) {
		
		if (note != null) {
			if(notes.contains(note)) {
				// Actualizamos la nota existente en la base de datos.
				updateNoteInDB(note);
			} else {
				notes.add(note);
				
				arrayListTitles.add(note.getTitle());
				
				// Insertamos la nueva nota en la base de datos
				insertNoteDB(note);
			}
		}
	}
	
	
	private void insertNoteDB(Note note) {
		
		try {
			
			Connection connectionDB = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8620870","sql8620870","Br7vTpCslf");
			Statement statement = connectionDB.createStatement();
			String query = "INSERT INTO Note (title, body, IdUser) VALUES ('" + note.getTitle() + "', '" + note.getBody() + "', " + longinUserId + ")";
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private void updateNoteInDB(Note note) {
		
		try {	
			Connection connectionDB = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8620870","sql8620870","Br7vTpCslf");
			Statement statement = connectionDB.createStatement();
			String query = "UPDATE Note SET title = '" + note.getTitle() + "', body = '" + note.getBody() + "' WHERE IdUser = " + longinUserId + " AND title = '" + note.getTitle() + "'";
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	private void deleteNoteFromDatabase(Note note) {
		int selectedIndex = notesListView.getSelectionModel().getSelectedIndex();

		if (selectedIndex >= 0) {
			Note selectedNote = notes.get(selectedIndex);
			
			try {	
				Connection connectionDB = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8620870","sql8620870","Br7vTpCslf");
				Statement statement = connectionDB.createStatement();
				String query = "DELETE FROM Note WHERE IdUser = " + longinUserId + " AND title = '" + selectedNote.getTitle() + "'";
				statement.executeUpdate(query);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method to scroll at notes windows.
	 * @param event - 
	 */
	/*public void scrollNotes(ActionEvent event) {
		//TODO to implement.
	}*/
	
	
	public int getLonginUserId() {
		return longinUserId;
	}

	public void setLonginUserId(int longinUserId) {
		this.longinUserId = longinUserId;
	}
}
