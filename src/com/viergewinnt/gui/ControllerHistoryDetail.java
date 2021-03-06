package com.viergewinnt.gui;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import com.viergewinnt.database.Database;
import com.viergewinnt.database.ValueClass;
import com.viergewinnt.database.ReuseableSatz;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Die Klasse ControllerHistoryDetail zeigt alle gespielten Saetze zum gewaehlten Spiel an
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
	 * Beim Aufruf der HistoryDetailed FXML Seite uebernimmt der Controller die Funktion, dass die Spieldaten aus der Datenbank geladen werden.
	 * Und anschliessend in einer Tabelle ausgegeben werden.
	 * 
	 * Durch das anklicken der einzelnen Tabelleneinträge wird dem Benutzer ermöglicht, sich das Spielfeld dieser Spiele noch einmal anzeigen zu lassen.
	 */
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		 tableViewSatz.getSelectionModel().selectedItemProperty().addListener((observable2, oldValue2, newValue2) -> {
           	if (newValue2 != null) 
           	{
	            	ReuseableSatz.setId(Integer.parseInt(((ValueClass) newValue2).getColumn1()));
	           // Spielfeld für die Historienbetrachtung wieder aufbauen
	            	try {
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("spielfeldRec.fxml"));
						Parent root1 = (Parent) fxmlLoader.load();
						Stage stage = new Stage();
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.initStyle(StageStyle.UNDECORATED);
						stage.setTitle("Spiel Starten");
						stage.setScene(new Scene(root1));
						stage.show();
						}// enf of try
	            	catch(Exception e)
					{
						e.printStackTrace();
					}
           	}		
           	}); // end of tableView handle
	
		ObservableList<ValueClass> data = FXCollections.observableArrayList(); //Darzustellende Daten
		try {
			Database db = new Database();
			ResultSet rs = db.getSaetze(ReuseableSatz.getId());
			 
			String satzId = null;
			String satzSpielid = null;
		    String satzGewonnen = null;

	         while(rs.next()){//Iteration über Zeilen
	             for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){ // Iteration über Spalten
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
			tableViewSatz.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
  
		} // end of Try
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		/**
		 * Zurueck zur Histroy Auswahl Seite
		 */
		bBack.setOnAction((ev) -> {
		((Node) (ev.getSource())).getScene().getWindow().hide();
		}); 
		
		/**
		 * Beenden des Programms
		 */
		bExit.setOnAction((ev)-> 
		{
			Platform.exit();
		}); 

	}// end of method
}// end of class
