package GUI;

import java.io.IOException;
import java.sql.SQLException;

import Database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {
//_________________________________________________________________________________________________		
		
		Database db = new Database();// Datenbank anlegen
		try {
			db.createTable(db);
		} catch (SQLException e2) {
			// System.out.println("Tabellen sind bereits angelegt");
		}
//_________________________________________________________________________________________________

		Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));

		Scene scene = new Scene(root);
		stage.setTitle("FXML Welcome");
		stage.setScene(scene);
		stage.sizeToScene();

		// Show stage
		stage.show();
	} // end of start

	public static void main(String[] args) {
		launch(args);
	}// end of Main
}// end of class
