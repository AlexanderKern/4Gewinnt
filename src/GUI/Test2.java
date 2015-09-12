package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test2 {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		System.out.println("Hallo das ist ein Test.");
//
//	}

	public void start(Stage stage) throws Exception {
	       Parent root = FXMLLoader.load(getClass().getResource("Oberflaeche.fxml"));
	    
	        Scene scene = new Scene(root, 300, 275);
	    
	        stage.setTitle("FXML Welcome");
	        stage.setScene(scene);
	        stage.show();
	    }
}
