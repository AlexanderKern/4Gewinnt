package GUI;

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
import javafx.stage.Stage;
public class Controller 
{

	@FXML
	public Button ok;
	
	@FXML
	public Button play;
	
	@FXML
	public Button exit;
	
	@FXML
	public TableView tableView = new TableView<>();
	
	
//	public Controller(TableView tableView)
//	{
//		super();
//		this.tableView = tableView;
////		tableView.rowFactoryProperty();
//	}

	public void onClicked(ActionEvent event)
	{
		ok.setText("BÄÄÄM");
		
	}
	
	public void goPlay(ActionEvent event)
	{
		play.setText("you pressed play");
	}
	
	public void goExit(ActionEvent event)
	{
		exit.setText("you pressed exit");
		
	}
	
	
}// end of class
