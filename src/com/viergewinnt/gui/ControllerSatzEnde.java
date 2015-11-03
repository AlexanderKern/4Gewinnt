package com.viergewinnt.gui;

import java.sql.SQLException;

import com.viergewinnt.database.Database;
import com.viergewinnt.database.ReuseableSatz;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ControllerSatzEnde {
	@FXML
	public Button neuenSatz, satzVerwerfen, siegerAendern;
	
	@FXML
	public Label gewinner;
	
	@FXML
	public AnchorPane anchorPane;

	@FXML
	public void siegerAendern( ) throws SQLException{
		Database db = new Database();
		if(ReuseableSatz.gewonnen.equals("gewonnen")){
			ReuseableSatz.gewonnen = "verloren";
		}else{
			ReuseableSatz.gewonnen = "gewonnen";
		}
		db.updateSatz(ReuseableSatz.gewonnen, ReuseableSatz.id);
		gewinner.setText("Claire hat" +ReuseableSatz.gewonnen);
	}

	@FXML
	public void satzVerwerfen() throws SQLException{
	Database db = new Database();
	db.loeschenZuege(ReuseableSatz.id);
	System.out.println("Züge gelöscht");
	db.satzloeschen(ReuseableSatz.id);
	System.out.println("Satz gelöscht");
	}
}
