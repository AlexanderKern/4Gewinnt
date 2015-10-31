package com.viergewinnt.database;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 

public class Main2 extends Application {
	
	
	public static void main(String[] args) {
	 launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		
		Database db = new Database();

		db.createSpiel();
		db.createSatz(ReuseableSpiel.id);
	
		System.out.println(ReuseableSatz.id);
		db.updateSatz("gewonnen", ReuseableSatz.id);
	
		AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("SpielEnde.fxml"));
		System.out.println("Hi");
		
		Scene scene = new Scene(anchorPane);
		stage.setTitle("Satz Ende");
		stage.setScene(scene);
		stage.sizeToScene();

		// Show stage
		stage.show();

	/**
		       
		    primaryStage.setTitle("Satz Ende");  
		    StackPane firstLayout = new StackPane();
		    Scene firstScene = new Scene(firstLayout, 300, 200 );
		    HBox hbox = new HBox(200);
		   
		    
		    StackPane secondaryLayout = new StackPane();
            Scene secondScene = new Scene(secondaryLayout, 200, 100);

            Stage secondStage = new Stage();
            secondStage.setTitle("Spiel Ende");
            secondStage.setScene(secondScene);

            Button neuenSatz = new Button("Neuen Satz");
		    neuenSatz.setOnAction(new EventHandler<ActionEvent>() {
		      @Override public void handle(ActionEvent event) {
		        //aufruf des Spielfelds
		      }
		    });

		    Button satzVerwerfen = new Button("Satz verwerfen");
		    
		    satzVerwerfen.setOnAction(new EventHandler<ActionEvent>() {
		      @Override public void handle(ActionEvent event) {
		       // Löschen alle Züge zur aktuellen Satzid 
		    	  
		    	 
		      
		      }
		    });

		    Button aendernSieger = new Button("Sieger ändern");
		    
		    aendernSieger.setOnAction(new EventHandler<ActionEvent>() {
		      @Override public void handle(ActionEvent event) {
		        //ändern des Tabellen eintrags 
		    	  
		    	  secondaryLayout.getChildren().addAll();
	                secondStage.show();
		      }
		    });

		   
		 
		    firstLayout.getChildren().addAll(hbox);
		    hbox.getChildren().addAll(neuenSatz, aendernSieger, satzVerwerfen);
		  
		    primaryStage.setScene(firstScene);
		    primaryStage.show();
		    **/
		  }


}
