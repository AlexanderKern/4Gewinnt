package com.viergewinnt.database;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Main2 extends Application {

	public static void main(String[] args) {
	 launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		       
		    primaryStage.setTitle("Popup Example");  
		  //  final Popup popup = new Popup(); popup.setX(300); popup.setY(200);
		   // popup.getContent().addAll(new Circle(25, 25, 50, Color.AQUAMARINE));
		    
		    
		    StackPane secondaryLayout = new StackPane();
            Scene secondScene = new Scene(secondaryLayout, 200, 100);

            Stage secondStage = new Stage();
            secondStage.setTitle("Satz Ende");
            secondStage.setScene(secondScene);

		    Button show = new Button("Show");
		    show.setOnAction(new EventHandler<ActionEvent>() {
		      @Override public void handle(ActionEvent event) {
		        //popup.show(primaryStage);
		    	  
		    	 
		        secondaryLayout.getChildren().addAll();
                secondStage.show();
		      }
		    });

		    Button hide = new Button("Hide");
		    hide.setOnAction(new EventHandler<ActionEvent>() {
		      @Override public void handle(ActionEvent event) {
		        popup.hide();
		      }
		    });

		    HBox layout = new HBox(10);
		    layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
		    layout.getChildren().addAll(show, hide);
		    primaryStage.setScene(new Scene(layout));
		    primaryStage.show();
		  }


}
