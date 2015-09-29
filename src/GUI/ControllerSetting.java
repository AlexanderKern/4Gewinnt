package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.Timer;
import javax.swing.UIManager;

import com.sun.javafx.collections.MappingChange.Map;

import Database.DatabaseCreate;
import Database.ReuseableSpiel;
import Database.Spiel;
import Database.Spieler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * @author  Cara Damm
 * Dieser Controller kontrolliert den Select Bildschirm!
 */
public class ControllerSetting implements Initializable  
{
@FXML
Button bCancel, bSelect, bStart;

@FXML
RadioButton cO, cX;

@FXML
RadioButton rSocket, rPath;

@FXML
TextField tfPath, tfEnemy;

@FXML
Button bExit;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) 
	{
		
	// Spielfeld soll aufgerufen werden
		
		bStart.setOnAction((ev)-> 
		{	
			
			DatabaseCreate db = new DatabaseCreate();
			try {
				db.create_table(db);
			} catch (SQLException e2) {
				//System.out.println("Tabellen sind bereits angelegt");
			}
			
			Spieler spieler = new Spieler(tfEnemy.getText());
			try {
				spieler.create(spieler, db);
			} catch (SQLException e1) {
				System.out.println(spieler.name + " konnte nicht angelegt werden!!!");
			}
			
			Spiel spiel = new Spiel(spieler.name);
			try {
				spiel.create(db, spiel);
			} catch (SQLException e3) {
				System.out.println("Es ist ein Fehler bei dem Erstellen eines Spiels aufgetreten!");
			}
			ReuseableSpiel reuse = new ReuseableSpiel();
			reuse.setName(spieler.name);
			
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("spielfeld2.fxml"));
			Parent root1;
			try {
				root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setTitle("Spielfeld");
				stage.setScene(new Scene(root1));
				stage.show();
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			((Node) (ev.getSource())).getScene().getWindow().hide();
		}); //end of play	
	

		bCancel.setOnAction((ev)-> 
		{	
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome.fxml"));
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
		
		bSelect.setOnAction((ev)-> 
		{	
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new java.io.File("."));
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setAcceptAllFileFilterUsed(false);

			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				tfPath.setText(fc.getSelectedFile().toString() + System.getProperty("file.separator"));
			}

		}); //end of select

	

		bExit.setOnAction((ev)-> 
		{	
			((Node) (ev.getSource())).getScene().getWindow().hide();
		}); //end of cancel

	
	
	}//end of initializize
}// END OF class
