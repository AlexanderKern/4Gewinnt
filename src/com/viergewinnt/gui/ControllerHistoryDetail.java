package com.viergewinnt.gui;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
//import javax.swing.Timer;

import com.viergewinnt.database.Database;
import com.viergewinnt.database.Spiel;
import com.viergewinnt.database.ValueClass;
import com.viergewinnt.database.ReuseableSatz;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Die Klasse ControllerHistoryDetail zeigt alle gespielten Saetze zum gewaehöetn Spiel an
 * @author Cara Damm
 *
 */

public class ControllerHistoryDetail implements Initializable
{
@FXML
Button bBack,bExit;

@FXML
TableView<ValueClass> tableViewSatz;

@FXML
TableColumn<ValueClass, String> col1, col2, col3,col4, col5;

	@Override
	/**
	 * 
	 */
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		ReuseableSatz reuseSatz = new ReuseableSatz();

		 tableViewSatz.getSelectionModel().selectedItemProperty().addListener((observable2, oldValue2, newValue2) -> {
	          System.out.println(newValue2);
           	if (newValue2 != null) 
           	{
	            	System.out.println("Ausgewähltes SatzID = "+((ValueClass) newValue2).getColumn1());
	            	reuseSatz.setId(Integer.parseInt(((ValueClass) newValue2).getColumn1()));

	           // Spielfeld für die Historienbetrachtung wieder aufbauen
	            	try {
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("spielfeld2.fxml"));
						Parent root1 = (Parent) fxmlLoader.load();
						Stage stage = new Stage();
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.initStyle(StageStyle.UNDECORATED);
						stage.setTitle("Spiel Starten");
						stage.setScene(new Scene(root1));
						stage.show();

						//((Node) (ev.getSource())).getScene().getWindow().hide();
					
						}// enf of try
	            	catch(Exception e)
					{
						e.printStackTrace();
					}
	            	//____________Ende Spielfeld aufruf_____________________
           	}		
           	}); // end of tableView handle
		
	
		ObservableList<ValueClass> data = FXCollections.observableArrayList(); //Darzustellebde Daten
		try {
			Database db = new Database();
			ResultSet rs = db.getSaetze(reuseSatz.getId());
			 
			String satzId = null;
			String satzSpielid = null;
		    String satzGewonnen = null;

	         while(rs.next()){//Itariere über Zeile
	             for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){   //Itariere über Spalten
	                 switch(i){
	                 case 1:
	                	 satzId = (rs.getString(i));
	                	 break;
	                 case 2:
	                	 satzSpielid = (rs.getString(i));
	                	 break;
	                 case 3:
	                	 satzGewonnen = (rs.getString(i));
	                	 break;
	                 }// end of switch case
	             }// end of for 
	            ValueClass cl = new ValueClass(satzId,satzSpielid,satzGewonnen, null, null);
	             data.add(cl);
	         }// end of while
	         
	     
	         // Spaltendefinition 
	         col1.setText("ID");
             col2.setText("Spiel Id");
             col3.setText("Gewonnen");
             col1.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column1"));
  		     col2.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column2"));
  		     col3.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column3"));
	         
  		    tableViewSatz.setItems(data);

			// Zusätzliche Spalten vermeiden
			tableViewSatz.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
  
		} // end of Try
		catch (Exception e) 
		{
			System.out.println(e);// TODO: handle exception
		}
	
		//-------------------------------------------------------------------
		
		bBack.setOnAction((ev) -> {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setTitle("Spiel Starten");
				stage.setScene(new Scene(root1));
				stage.show();

				((Node) (ev.getSource())).getScene().getWindow().hide();
			
				}// enf of try
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}); // end of bBack
		
		bExit.setOnAction((ev)-> {
			
			((Node) (ev.getSource())).getScene().getWindow().hide();

		}); // end of bExit
		
		
		
		
	}// end of method
	

}// end of class
