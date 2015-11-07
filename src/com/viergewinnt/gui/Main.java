package com.viergewinnt.gui;

import java.io.IOException;
import java.sql.SQLException;

import com.viergewinnt.database.Database;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Die Klasse Main startet die 4Gewinnt Applikation und ruft den Controller des Startbildschrims auf
 * 
 * @author Cara Damm
 */

public class Main extends Application {
	
	/**
	 * Die FXML Seite Welcome wird von  aufgerufen. Nachdem die Seite welcome.fxml ausgerufen wurde uebernimmt der Controller, der fuer diese Seite definiert wurde, das weiter handling.
	 * Desweiteren wird hier eine Datenbanktabelle angelegt.
	 */
	@Override
	public void start(Stage stage) throws IOException {
		// Datenbank anlegen
		Database db = new Database();
		try {
			db.createTable(db);
		} catch (SQLException e2) {
			//Tabellen wurden bereits angelegt
		}
		
		Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("FXML Welcome");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	} 

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}// end of class
