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
Label lab00, lab01;



public void clicked()
{
	Image imageY = new Image(getClass().getResourceAsStream("coinYel.png"));
	Image imageR = new Image(getClass().getResourceAsStream("coinRed.png"));

	lab00.setGraphic(new ImageView(imageY));
	lab01.setGraphic(new ImageView(imageR));
}
}// END OF class
