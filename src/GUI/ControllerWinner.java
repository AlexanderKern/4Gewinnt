package GUI;

import java.net.URL;
import java.util.ResourceBundle;

import com.viergewinnt.api.pusher.PusherMain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerWinner implements Initializable {
	@FXML
	public Button statistic, play, exit;

	@FXML
	public TableView tableView = new TableView<>();

	@FXML
	public Label coinYel, coinRed;
	
	@FXML
	public Pane pane;
	

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		
			

	}// end of initialize
	
	

}// end of Class
