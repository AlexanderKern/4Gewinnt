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

import Database.ReuseableSpiel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

//Controller Field
public class ControllerF 
{
@FXML
Button button, bExit, bBack;

@FXML
GridPane grid;

@FXML
TextField colR, colY, rowR,rowY;

@FXML
ImageView ivOne, ivTwo, ivThree;


Image imageY = new Image(getClass().getResourceAsStream("coinYel.png"));
Image imageR = new Image(getClass().getResourceAsStream("coinRed.png"));
Image imageG = new Image(getClass().getResourceAsStream("coinGrey.png"));


@SuppressWarnings("static-access")
public void clicked()
{
	ReuseableSpiel reuse = new ReuseableSpiel();
	System.out.println("name:" + reuse.getName());
	int row = (int)(Math.random() * 5);
	int col = (int)(Math.random() * 5);
	Label l = new Label("");
	l.setGraphic(new ImageView(imageY));
	grid.setRowIndex(l, row);
	grid.setColumnIndex(l, col);


	grid.getChildren().addAll(l);
	
	
}// end of clicked()

public void onBack(ActionEvent event) throws IOException
{
	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selectScreen.fxml"));
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
    ((Node)(event.getSource())).getScene().getWindow().hide();

}

}// END OF class
