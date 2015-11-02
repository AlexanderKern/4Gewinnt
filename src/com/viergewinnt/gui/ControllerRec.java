package com.viergewinnt.gui;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import com.viergewinnt.api.common.util.ReuseServermethode;
import com.viergewinnt.api.file.FileMain;
import com.viergewinnt.api.pusher.PusherMain;
import com.viergewinnt.database.Database;
import com.viergewinnt.database.ReuseableSatz;
import com.viergewinnt.database.ReuseableSpiel;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Die Klasse ControllerField
 * 
 * @author Cara Damm
 *
 */
// Controller Field
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
	Label lPlayerY, lPlayerR, lGewonnen,lSatz;
	
	
	@FXML
	Pane pane;

	Image imageBlue = new Image("file:///" + System.getProperty("user.dir") + "/assets/img/coinBlue.png");
	Image imageGreen = new Image("file:///" + System.getProperty("user.dir") + "/assets/img/coinGreen.png");
	Image imageG = new Image("file:///" + System.getProperty("user.dir") + "/assets/img/coinGrey.png");

	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)

	{
		
		Database db = new Database();
		try {
			
		ResultSet rs =	db.getZuege(ReuseableSatz.getId());
		//spalte(int), zeile(int), gegner(boolean)
	
		 boolean gegner;
		int spalte;
		int zeile;
		while(rs.next())
		{
			gegner = rs.getBoolean(3);
			spalte = rs.getInt(1);
			zeile = rs.getInt(2);
			setStone(gegner, spalte, zeile);
		}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bBack.setOnAction((ev) -> {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("historyDetail.fxml"));
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
		}); // end of bBack

		bExit.setOnAction((ev) -> {
			
			((Node) (ev.getSource())).getScene().getWindow().hide();
		}); // end of bExit


	}// end of initi

	private void setStone(boolean gegner, int spalte, int zeile) 
	{
		//Booelan true = blau false = grün
		
		if (gegner == true)
		{
			ImageView iv = new ImageView(imageBlue);
			grid.setRowIndex(iv, zeile);
			grid.setColumnIndex(iv, spalte);
			grid.getChildren().addAll(iv);		
		}
		else if(gegner == false)
		{
			ImageView iv = new ImageView(imageGreen);
			grid.setRowIndex(iv, zeile);
			grid.setColumnIndex(iv, spalte);
			grid.getChildren().addAll(iv);
		}
	}
}// end of class
