package GUI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
//import javax.swing.Timer;

import Database.DatabaseCreate;
import Database.Spiel;
import Database.Spieler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerHistory implements Initializable
{
@FXML
Button bBack,bExit;

@FXML
TableView tGames, tZuege;

@FXML
TableColumn<ControllerHistory,String> col1;


TableColumn col2, col3;

@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) 
	{

		bBack.setOnAction((ev) -> {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setTitle("Spiel Starten");
				stage.setScene(new Scene(root1));
				stage.show();

				((Node) (ev.getSource())).getScene().getWindow().hide();
			
				}// enf of try
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}); // end of bBack
		
		bExit.setOnAction((ev)-> {
			
			((Node) (ev.getSource())).getScene().getWindow().hide();

		}); // end of bExit
		
	}// end of method


}// end of class
