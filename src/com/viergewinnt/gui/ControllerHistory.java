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

public class ControllerHistory implements Initializable
{
@FXML
Button bBack,bExit;

@FXML
TableView<ValueClass> tableView, tZuege;

@FXML
TableColumn<ValueClass, String> col1, col2, col3,col4, col5;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		
		ObservableList<ValueClass> data = FXCollections.observableArrayList(); //Darzustellebde Daten
		try {
			
		
		Database db = new Database();
		 ResultSet rs = db.getSpiele();
		  Spiel spiel = new Spiel();
		//Daten aus der Datenbank laden
		  while(rs.next())
		  {//Itariere über Zeile
	             for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){   //Itariere über Spalten
	               
	                 switch(i)
	                 {
	                 case 1:
	                	 spiel.setId(rs.getString(i));
	                	 System.out.println("ID"+rs.getString(i));
	                	 break;
	                 case 2:
	                	 spiel.setPunkte(rs.getString(i));
	                	 System.out.println("Punkte"+rs.getString(i));
	                	 break;
	                 case 3:
	                	 spiel.setGegner(rs.getString(i));
	                	 break;
	                 case 4:
	                	 spiel.setDatum(rs.getString(i));
	                	 break;
	                 case 5:
	                	 spiel.setFarbe(rs.getString(i));
	                		
	                 }// end of switch 
	             }// end of for
	             
	                 
	                 ValueClass cl = new ValueClass(spiel.getId(), spiel.getPunkte(), spiel.getGegener(), spiel.getDatum(), spiel.getFarbe());
	                 data.add(cl);   

	                 
	                //TableColumn<ValueClass, String> col1 = new TableColumn<ValueClass, String>("Id"); 
	                col1.setText("ID");
	                col2.setText("Punkte");
	                col3.setText("Gegner");
	                col4.setText("Datum");
	                col5.setText("Farbe");
	                
	     		    col1.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column1"));
	     		    col2.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column2"));
	     		    col3.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column3"));
	     		    col4.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column4"));
	     		    col5.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column5"));
	     		    
	     
	                 
		  }// end of while
		  
		   tableView.setItems(data);
 		    // Zusätzliche Spalten vermeiden
 		    tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
 		

		  
		} // end of Try
		catch (Exception e) 
		{
			System.out.println(e);// TODO: handle exception
		}
	
		tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) 
			{
			// Welche Zeile wurde makiert?
				TableView tv = (TableView) event.getSource();
				System.out.println("test1");
				String selectedString = tv.getSelectionModel().getSelectedItem().toString();
				System.out.println("Test 2");
				System.out.println(selectedString);
//				String selectedID = selectedString.substring(1,selectedString.indexOf(","));
//				System.out.println(selectedString+ " ID "+selectedID);
				
				/**
			     System.out.println(id + " geklickt");
			     // Saetze von Spiel in DB suchen
			     ResultSet rs = dbConn.getSaetzeOfSpiel(id);
			     // Saetze anzeigen
			     showSatzTable(rs);
			     }
				 */
				
			}
			
			});
		
		
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
