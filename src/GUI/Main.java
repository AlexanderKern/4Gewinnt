package GUI;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application 
{
	@Override
	public void start(Stage stage) throws IOException 
	{
		Parent root = FXMLLoader.load(getClass().getResource("table.fxml"));
	    
		Scene scene = new Scene(root);
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.sizeToScene();
        
        //
        stage.show();
		} 
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
