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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ControllerS 
{
@FXML
Button button;

@FXML
Label lab00, lab01, lab02 ,lab03, lab04, lab05;
@FXML
Label lab10, lab11, lab12 ,lab13, lab14, lab15;
@FXML
Label lab20, lab21, lab22 ,lab23, lab24, lab25;
@FXML
Label lab30, lab41, lab42 ,lab43, lab44, lab45;
@FXML
Label lab50, lab51, lab52 ,lab53, lab54, lab55;
@FXML
Label lab60, lab61, lab62 ,lab63, lab64, lab65;

public void clicked()
{
	Image imageY = new Image(getClass().getResourceAsStream("coinYel.png"));
	Image imageR = new Image(getClass().getResourceAsStream("coinRed.png"));

	
	String lab ="lab";
	

	for (int i = 0; i <3; i++) 
	{
		for (int j = 0; j < 4; j++) 
		{
			String label = lab+i+j;
			Label l = new Label("");
			System.out.println(l.);
			l.setGraphic(new ImageView(imageR));
		}//end of first for
	}// end of secound for
	
	//lab00.setGraphic(new ImageView(imageY));
	//lab01.setGraphic(new ImageView(imageR));
	
}// end of clicked()

}// END OF class
