package GUI;

import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.Timer;



import com.sun.javafx.collections.MappingChange.Map;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class Controller 
{

	@FXML
	public Button statistic;
	
	@FXML
	public Button play;
	
	@FXML
	public Button exit;
	
	@FXML
	public TableView tableView = new TableView<>();
	
	@FXML 
	public Label coinYel;
	
	@FXML 
	public Label coinRed;
	
	
	public void movingLabels()
	{
		final int movingCoinCtr = 10;
		final JLabel label[] = new JLabel [movingCoinCtr];
		//for (int i = 0; i < label.length; i++) 
		//{
			int randomColor = 1 + (int) (Math.random() * ((2 - 1) + 1));
			int randomX = -20 + (int) (Math.random() * ((660 - -20) + 1));
			int randomY = -20 + (int) (Math.random() * ((500 - -20) + 1));

			coinYel.setLayoutX(randomX);
			coinYel.setLayoutY(randomY);
			
		//}
	}// end of constructor

	public void onStatistic(ActionEvent event)
	{
		statistic.setText("BÄÄÄM");
		int randomX = -20 + (int) (Math.random() * ((660 - -20) + 1));
		coinYel.setLayoutX(randomX);
		movingLabels();
		
	}//end of onStatistic
	
	public void onPlay(ActionEvent event) throws IOException
	{
		play.setText("you pressed play");
		
		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("spielfeld1.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.initModality(Modality.APPLICATION_MODAL);
         stage.initStyle(StageStyle.UNDECORATED);
         stage.setTitle("ABC");
         stage.setScene(new Scene(root1));  
         stage.show();
         
         ((Node)(event.getSource())).getScene().getWindow().hide();
	}//end of onPlay
	
	public void onExit(ActionEvent event)
	{
		exit.setText("you pressed exit");
		
	}// end of onExit
	
	
}// end of class
