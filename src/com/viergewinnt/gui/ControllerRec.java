package com.viergewinnt.gui;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.viergewinnt.database.Database;
import com.viergewinnt.database.ReuseableSatz;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.*;

/**
 * Die Klasse ControllerField
 * 
 * @author Cara Damm
 *
 */
public class ControllerRec implements Initializable {
	@FXML
	Button button, bExit, bBack, bNewSatz, bSatzVerwerfen;

	@FXML
	ToggleGroup myToggleGroup;

	@FXML
	ToggleButton tbGewonnen, tbVerloren;

	@FXML
	GridPane grid;

	@FXML
	TextField colR, colY, rowR, rowY;

	@FXML
	ImageView ivOne, ivTwo, ivThree, ivGreen, ivBlue;

	@FXML
	Label lPlayerY, lPlayerR, lGewonnen, lSatz;

	@FXML
	Pane pane;

	Image imageBlue = new Image("file:///" + System.getProperty("user.dir") + "/assets/img/coinBlue.png");
	Image imageGreen = new Image("file:///" + System.getProperty("user.dir") + "/assets/img/coinGreen.png");
	Image imageG = new Image("file:///" + System.getProperty("user.dir") + "/assets/img/coinGrey.png");

	@Override
	/**
	 * Mit dem Aufruf der Klasse wird das Spielfeld eines bereits gespielten Spiels wieder aufgerufen
	 * 
	 * Die gemachten Zuege werden aus der Datenbank geladen und nochmals auf dem Spielfeld angezeigt.
	 */
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		Database db = new Database();
		/**
		 * Auslesen der gespielten Zuege aus der DB
		 */
		try {
			ResultSet rs = db.getZuege(ReuseableSatz.getId());
			boolean gegner;
			int spalte;
			int zeile;
			while (rs.next()) {
				gegner = rs.getBoolean(3);
				spalte = rs.getInt(1);
				zeile = rs.getInt(2);
				setStone(gegner, spalte, zeile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/**
		 * Der User wird zurueck zur Tabellenauswahl gefuehrt
		 */
		bBack.setOnAction((ev) -> {
			((Node) (ev.getSource())).getScene().getWindow().hide();
		});
		/**
		 * Das Programm wird beendet
		 */
		bExit.setOnAction((ev) -> {
			Platform.exit();
		});
	}

	private void setStone(boolean gegner, int spalte, int zeile) {
		/**
		 *Nach jedem Zug, der aus der DB geladen wird, wird die Methode setStone aufgerufen.
		 *
		 *Die Methode uberprueft ob es sich um ein Stein vom Gegner oder von Claire handelt und setzt anschliessend 
		 *die Steinfarbe.
		 *
		 *Die Zeile und Spalte des Zuges wird ebenfalls aus der DB gelesen. Der Stein wird an der jeweils uebergebenen Zeilen / Spalten Position wieder dem Spielfeld hinzugefuegt
		 */
		if (gegner == true) {
			ImageView iv = new ImageView(imageBlue);
			GridPane.setRowIndex(iv, zeile);
			GridPane.setColumnIndex(iv, spalte);
			grid.getChildren().addAll(iv);
		} else if (gegner == false) {
			ImageView iv = new ImageView(imageGreen);
			GridPane.setRowIndex(iv, zeile);
			GridPane.setColumnIndex(iv, spalte);
			grid.getChildren().addAll(iv);
		}
	}
}
