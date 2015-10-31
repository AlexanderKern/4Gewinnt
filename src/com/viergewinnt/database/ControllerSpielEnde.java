package com.viergewinnt.database;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.viergewinnt.database.Database;
import com.viergewinnt.database.ReuseableSatz;
import com.viergewinnt.database.ValueClass;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerSpielEnde implements Initializable{
	
	@FXML
	Button beenden;
	
	@FXML
	Label labelBeendet;
	
	@FXML
	TableView<ValueClass> tableViewGespielteSatz;
	
	@FXML
	TableColumn<ValueClass, String> col1, col2, col3,col4, col5;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ReuseableSatz reuseSatz = new ReuseableSatz();
		ObservableList<ValueClass> data = FXCollections.observableArrayList(); //Darzustellebde Daten
		
		 tableViewGespielteSatz.getSelectionModel().selectedItemProperty().addListener((observable2, oldValue2, newValue2) -> {
	          System.out.println(newValue2);
          	if (newValue2 != null) 
          	{
	            	System.out.println("Ausgewähltes SatzID = "+((ValueClass) newValue2).getColumn1());
	            	reuseSatz.setId(Integer.parseInt(((ValueClass) newValue2).getColumn1()));
	            	
	     
	            	
	            	String satzGewonnen = null;
	            	if(((ValueClass) newValue2).getColumn2().equals("gewonnen")){
	            		satzGewonnen= "verloren";

	            	}else{
	            		satzGewonnen= "gewonnen";
	            	}
	          
	            	Database db = new Database();
	            	try {
						db.updateSatz(satzGewonnen,reuseSatz.getId());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	data.get(tableViewGespielteSatz.getSelectionModel().getSelectedIndices().get(0)).setColumn2(satzGewonnen);
	                tableViewGespielteSatz.refresh();

          	}
		 });
		
		
		
		try {
			Database db = new Database();
			
			ResultSet rs = db.getSaetze(ReuseableSpiel.id);
			
			 
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
		
		ReuseableSpiel.setName("Franz");
		labelBeendet.setText("Das Spiel gegen "+ReuseableSpiel.getName() + " ist benendet!");
		
	}
}