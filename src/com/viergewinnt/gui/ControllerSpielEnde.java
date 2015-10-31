package com.viergewinnt.gui;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.viergewinnt.database.Database;
import com.viergewinnt.database.ReuseableSatz;
import com.viergewinnt.database.ValueClass;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerSpielEnde implements Initializable{
	
	@FXML
	Button beenden;
	
	@FXML
	TableView<ValueClass> tableViewGespielteSatz;
	
	@FXML
	TableColumn<ValueClass, String> col1, col2, col3,col4, col5;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<ValueClass> data = FXCollections.observableArrayList(); //Darzustellebde Daten
		try {
			Database db = new Database();
			ReuseableSatz reuseSatz = new ReuseableSatz();
			ResultSet rs = db.getSaetze(reuseSatz.getId());
			 
			String satzId = null;
		    String satzGewonnen = null;

	         while(rs.next()){//Itariere über Zeile
	             for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){   //Itariere über Spalten
	                 switch(i){
	                 case 1:
	                	 satzId = (rs.getString(i));
	                	 break;
	                 case 2:
	                	 break;
	                 case 3:
	                	 satzGewonnen = (rs.getString(i));
	                	 break;
	                 }// end of switch case
	             }// end of for 
	            ValueClass cl = new ValueClass(satzId,satzGewonnen,null, null, null);
	             data.add(cl);
	         }// end of while
	         
	     
	         // Spaltendefinition 
	         col1.setText("ID");
	         col2.setText("Gewonnen");
	         col1.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column1"));
			     col2.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column2"));

	         
			     tableViewGespielteSatz.setItems(data);

			// Zusätzliche Spalten vermeiden
			     tableViewGespielteSatz.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		} // end of Try
		catch (Exception e) 
		{
			

	}
		
	}
}