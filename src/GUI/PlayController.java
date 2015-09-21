package GUI;

import java.io.IOException;
import java.util.HashMap;

import com.sun.javafx.collections.MappingChange.Map;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PlayController 
{

	@FXML
	public Button statistic;
	
	@FXML
	public Button play;
	
	@FXML
	public Button exit;
	
	@FXML
	public TableView tableView = new TableView<>();
	

	
	public void onPlay(ActionEvent event) throws IOException
	{
		play.setText("you pressed play");
		
		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("table2.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.initModality(Modality.APPLICATION_MODAL);
         stage.initStyle(StageStyle.UNDECORATED);
         stage.setTitle("ABC");
         stage.setScene(new Scene(root1));  
         stage.show();
         
         ((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	public void onExit(ActionEvent event)
	{
		//Schließt das Fenster welches gerade geöffnet ist
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	
}// end of class
