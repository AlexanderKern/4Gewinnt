package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sun.javafx.collections.MappingChange.Map;

import Database.DatabaseCreate;
import Database.ReuseableSatz;
import Database.ReuseableSpiel;
import Database.Satz;
import Database.Zug;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class ControllerField implements Initializable  
{
@FXML
Button button, bExit, bBack;

@FXML
GridPane grid;

@FXML
TextField colR, colY, rowR,rowY;

@FXML
ImageView ivOne, ivTwo, ivThree;

@FXML
Label lPlayerY, lPlayerR;


@SuppressWarnings("static-access")
@Override
public void initialize(URL fxmlFileLocation, ResourceBundle resources) 

{
	/*Satz in Datenbank anlegen*/ 
	ReuseableSpiel reuseSpiel = new ReuseableSpiel();
	DatabaseCreate db = new DatabaseCreate(); // funktioniert das wenn hier neue db-Verbindung angelegt wird? 
	try {
		Satz satz = new Satz(db, reuseSpiel.id);
		System.out.println("Satz mit der Id = " + satz.id +" wurde angelegt und gehört zum Spiel mit der ID " + satz.spiel_id);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 
	
	
	Image imageY = new Image(getClass().getResourceAsStream("coinYel.png"));
	Image imageR = new Image(getClass().getResourceAsStream("coinRed.png"));
	Image imageG = new Image(getClass().getResourceAsStream("coinGrey.png"));
	
	lPlayerR.setText("Claire");

/* Setze Stein */ 
button.setOnAction((ev)-> 
{	

	int row = (int)(Math.random() * 5);
	int col = (int)(Math.random() * 5);
	Label l = new Label("");
	l.setGraphic(new ImageView(imageY));
	grid.setRowIndex(l, row);
	grid.setColumnIndex(l, col);


	grid.getChildren().addAll(l);
	
	
	ReuseableSatz reuseSatz = new ReuseableSatz();
	System.out.println("Die aktuelle Satz Id --> "+reuseSatz.id + "Nun wird der ZUg in DB gespeichert");
	//Zug in Datenbank speichern hier?
	Boolean gegner = true; // false wenn wir den Zug machnen :) 
	int spalte = col;
	Zug zug;
	try {
		zug = new Zug(reuseSatz.id , gegner,  spalte, db);
		System.out.println("Der angelegte Zug hat die Id" + zug.id);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}); //end of button

bBack.setOnAction((ev)-> 
{	
	try {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selectScreen.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("ABC");
        stage.setScene(new Scene(root1));  
        stage.show();
        
        ((Node)(ev.getSource())).getScene().getWindow().hide();
		} 
		
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}); //end of cancel

bExit.setOnAction((ev)-> 
{	
	((Node) (ev.getSource())).getScene().getWindow().hide();
}); //end of cancel

}// end of init

}// END OF class
