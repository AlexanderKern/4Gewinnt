package com.viergewinnt.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.viergewinnt.database.Database;

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

/**
 * Die Klasse ControllerStart ist die Kontrollerklase für das Welcome FXML
 * Hier wird definiert, welche Aktionen von den einzelnen Elementen der Benutzeroberfläche ausgefuehrt werden
 * 
 * @author Cara Damm
 *
 */
public class ControllerStart implements Initializable {
	@FXML
	public Button statistic, play, exit;

	@FXML
	public TableView<Object> tableView = new TableView<>();

	@FXML
	public Label coinYel, coinRed;

	@FXML
	public Pane pane;

	@Override
	/**
	 * Initailisiert den Startbildschirm
	 * 
	 * @param fxmlFileLocation
	 * @param resources
	 * 
	 * Es wird definiert, welches Coding ausgefuehrt werden soll, wenn ein bestimmter Butto geklickt wird.
	 */
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
/**
 * Beim drucken des play - Buttons wird der selectScreen aufgerufen.
 */
		play.setOnAction((ev) -> {
			try {
				Database db = new Database();
				db.createSpiel();

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
				e.printStackTrace();
			}
		}); 

		/**
		 * Beim druecken auf das statistic-Buttons wird das History.fxml aufgerufen.
		 */
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
				e.printStackTrace();
			}
		}); 
		/**
		 * Mit dem Exit Button wird das aktuelle Fenster geschlossen
		 */
		exit.setOnAction((ev) -> {
			((Node) (ev.getSource())).getScene().getWindow().hide();
		}); 
		
	}
}
