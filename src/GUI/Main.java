package GUI;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application
{
	@Override
	public void start(Stage stage) throws IOException 
	{
		Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));

		Scene scene = new Scene(root);
		stage.setTitle("FXML Welcome");
		stage.setScene(scene);
		stage.sizeToScene();

		// Show stage
		stage.show();
	} // end of start

	public static void main(String[] args) 
	{
		launch(args);
	}// end of Main
}// end of class
