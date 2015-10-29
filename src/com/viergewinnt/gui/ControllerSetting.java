package com.viergewinnt.gui;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.viergewinnt.api.common.util.ReuseServermethode;
import com.viergewinnt.api.pusher.PusherMain;
import com.viergewinnt.database.Database;
import com.viergewinnt.database.Spiel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerSetting implements Initializable {
	@FXML
	Button bCancel, bSelect, bStart;

	@FXML
	RadioButton cO, cX;

	@FXML
	RadioButton rSocket, rPath;

	@FXML
	TextField tfPath, tfEnemy, tfkey, tfsecret;

	@FXML
	Button bExit;

	PusherMain pusherMain;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

		// Spielfeld soll aufgerufen werden
		bStart.setOnAction((ev) -> {
			
			System.out.println(System.getProperty("user.dir"));
		
			// Settingsparameter setzen
			if (cX.isSelected()) {
				ReuseServermethode.setTeam("X");
				ReuseServermethode.setTeamfarbe(true);
				ReuseServermethode.setGegnerfarbe(false);
			} else {
				ReuseServermethode.setTeam("O");
				ReuseServermethode.setTeamfarbe(false);
				ReuseServermethode.setTeamfarbe(true);
			}
			if (rSocket.isSelected()) {
				ReuseServermethode.setMethode("Pusher");

				ReuseServermethode.setKey(tfkey.getText());
				ReuseServermethode.setSecret(tfsecret.getText());
			} else {

				ReuseServermethode.setMethode("File");
				ReuseServermethode.setPfad(tfPath.getText());
			}

			ReuseServermethode.setGegner(tfEnemy.getText());
			
			//Spiel anlegen in Datenbank---------------------------------------------------------------------------
			Database db = new Database();
			boolean farbe = true; 
			System.out.println("Spiel anlegen");
			System.out.println("Team Farbe"+ReuseServermethode.getTeam());
			farbe = ReuseServermethode.getTeamfarbe(); //Grün true und Blau false
				
			try {
				db.updateSpiel(tfEnemy.getText(), farbe);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
			
			try {
				db.createSpieler(tfEnemy.getText());
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				System.out.println(tfEnemy.getText() + " konnte nicht angelegt werden!!!");
			}
			//-------------------------------------------------------------------------------


			
			if (rSocket.isSelected()) {
				if (!tfkey.getText().isEmpty() || !tfsecret.getText().isEmpty()) 
				{
					((Node) (ev.getSource())).getScene().getWindow().hide();
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
				}else{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Look, an Error Dialog");
					alert.setContentText("Bitte überprüfen Sie die Eingaben der Pusher-Kommunikation!");
					alert.show();
				}
			}else if(!tfPath.getText().isEmpty() && rPath.isSelected()){
				((Node) (ev.getSource())).getScene().getWindow().hide();
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
			}else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Look, an Error Dialog");
				alert.setContentText("Bitte überprüfen Sie die Eingaben der File-Kommunikation!");
				alert.show();
			}
			
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

			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Directory for FILE-API");
			File file = directoryChooser.showDialog(null);
			tfPath.setText(file.getPath() + System.getProperty("file.separator"));

		}); // end of select

		bExit.setOnAction((ev) -> {
			((Node) (ev.getSource())).getScene().getWindow().hide();
		}); // end of cancel

	}// end of initializize
}// END OF class
