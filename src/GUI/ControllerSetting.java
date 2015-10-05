package GUI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
//import javax.swing.Timer;

import com.viergewinnt.api.pusher.PusherMain;

import Database.DatabaseCreate;
import Database.Spiel;
import Database.Spieler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * @author  Cara Damm
 * Dieser Controller kontrolliert den Select Bildschirm!
 */
public class ControllerSetting implements Initializable {
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
	
	PusherMain pusherMain;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

		// Spielfeld soll aufgerufen werden

		bStart.setOnAction((ev) -> {

			DatabaseCreate db = new DatabaseCreate();
			try {
				db.create_table(db);
			} catch (SQLException e2) {
				// System.out.println("Tabellen sind bereits angelegt");
			}

			Spieler spieler = new Spieler(tfEnemy.getText());
			try {
				spieler.create(spieler, db);
			} catch (SQLException e1) {
				System.out.println(spieler.name + " konnte nicht angelegt werden!!!");
			}

			Spiel spiel;
			try {
				if (cX.isSelected()){
					 spiel = new Spiel(spieler.name, true); // Rot
				}
				else {
					 spiel = new Spiel(spieler.name, false); //gelb
				}
				spiel.create(db, spiel);
				System.out.println("Spiel angelgt"+ spiel.gegner);
			} catch (SQLException e3) {
				System.out.println("Es ist ein Fehler bei dem Erstellen eines Spiels aufgetreten!");
			}
			try {
				db.shutdown();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			pusherMain.pusher();
			// mit in Klasse Spiel drinnen
			// ReuseableSpiel reuse = new ReuseableSpiel();
			// reuse.setName(spieler.name);

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
			} catch (Exception e) {
				e.printStackTrace();
			}

			((Node) (ev.getSource())).getScene().getWindow().hide();
		}); // end of play

		bCancel.setOnAction((ev) -> {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome.fxml"));
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
				e.printStackTrace();
			}
		}); // end of cancel

		bSelect.setOnAction((ev) -> {
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new java.io.File("."));
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setAcceptAllFileFilterUsed(false);

			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				tfPath.setText(fc.getSelectedFile().toString() + System.getProperty("file.separator"));
			}

		}); // end of select

		bExit.setOnAction((ev) -> {
			((Node) (ev.getSource())).getScene().getWindow().hide();
		}); // end of cancel

	}// end of initializize
}// END OF class
