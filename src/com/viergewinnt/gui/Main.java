package com.viergewinnt.gui;

import java.io.IOException;
import java.sql.SQLException;

import com.viergewinnt.database.Database;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		// Datenbank anlegen
		Database db = new Database();
		try {
			db.createTable(db);
		} catch (SQLException e2) {
			// System.out.println("Tabellen sind bereits angelegt");
		}

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