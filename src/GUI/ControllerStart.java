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

public class ControllerStart implements Initializable {
	@FXML
	public Button statistic, play, exit;

	@FXML
	public TableView tableView = new TableView<>();

	@FXML
	public Label coinYel, coinRed;
	
	@FXML
	public Pane pane;
	

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		
				play.setOnAction((ev) -> {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selectScreen.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setTitle("ABC");
				stage.setScene(new Scene(root1));
				stage.show();

				((Node) (ev.getSource())).getScene().getWindow().hide();
				
			}

			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}); // end of exit

		statistic.setOnAction((ev) -> {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("history.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setTitle("ABC");
				stage.setScene(new Scene(root1));
				stage.show();
				
				((Node) (ev.getSource())).getScene().getWindow().hide();
			}

			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}); // end of statistic

		exit.setOnAction((ev) -> {
			((Node) (ev.getSource())).getScene().getWindow().hide();

		}); // end of exit()

	}// end of initialize
	
	

}// end of Class
