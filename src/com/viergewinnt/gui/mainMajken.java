package com.viergewinnt.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class mainMajken extends Application {

	public static void main(String[] args){
		// TODO Auto-generated method stub

	}

	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("Hallo?");
		AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("SpielEnde.fxml"));
		
		
		Scene scene = new Scene(anchorPane);
		stage.setTitle("Spiel Ende");
		stage.setScene(scene);
		stage.sizeToScene();

		// Show stage
		stage.show();
		
	}

}
