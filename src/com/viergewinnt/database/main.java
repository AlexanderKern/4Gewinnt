package com.viergewinnt.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class main extends Application{

	public static void main(String[] args)  {
		launch(args);	
	}
	 public void start(Stage stage) throws SQLException {
		  Scene scene = new Scene(new Group());
		    stage.setTitle("Spiele");
		    stage.setWidth(450);
		    stage.setHeight(500);
	
		    TableView<ValueClass> tableView = createTableView();
		    
		  //  final Label selected = new Label();
	        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	            if (newValue == null) {
	             //   selected.setText("");
	                return;
	            }

	            /*
	             * Aufruf Satz Übersicht 
	             */
				if (((ValueClass) newValue).getColumn1() != null ){
					int spielId = Integer.parseInt(((ValueClass) newValue).getColumn1());
					System.out.println("Ausgewähltes Spiele ID = "+((ValueClass) newValue).getColumn1());
					 try{
			                StackPane secondaryLayout = new StackPane();
			                Scene secondScene = new Scene(secondaryLayout, 200, 100);
			 
			                Stage secondStage = new Stage();
			                secondStage.setTitle("Sätze");
			                secondStage.setScene(secondScene);
			          
			                TableView<ValueClass>  tableViewSatz = createTableViewSatz(spielId);
			                tableViewSatz.getSelectionModel().selectedItemProperty().addListener((observable2, oldValue2, newValue2) -> {
			    	          System.out.println(newValue2);
			                	if (newValue2 != null) {
			    	            	System.out.println("Ausgewähltes SatzID = "+((ValueClass) newValue2).getColumn1());
				    				// Spielfeld aufbauen
				    					try {
											ZuegeAnzeigen(Integer.parseInt(((ValueClass) newValue2).getColumn1()));
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
			    	            }else{
			    	            	  return;	
			    				}
			                });// end of get selected 
			                secondaryLayout.getChildren().add(tableViewSatz);
			                secondStage.show();
				          }catch(Exception e) {
				              e.printStackTrace();
				          }
				}// end of of 
	        });// end of get selected 
	    
		    ((Group) scene.getRoot()).getChildren().addAll(tableView);

		    stage.setScene(scene);
		    stage.show();
		  }

	 /**
	  * Tabelle Saetze erstellen
	  */
	 private TableView<ValueClass> createTableViewSatz(int spielId) throws SQLException {
		
		 System.out.println("Tabelle Satz erstellen ");
		 Database db = new Database();
		 ResultSet rs = db.getSaetze(spielId);
		 
		 String satzId = null;
		 String satzSpielid = null;
		 String satzGewonnen = null;
		 
		 ObservableList<ValueClass> data = FXCollections.observableArrayList(); //Darzustellebde Daten

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
                 }
             }
            ValueClass cl = new ValueClass(satzId,satzSpielid,satzGewonnen, null, null);
             data.add(cl);
         }
		    // Spaltendefinition 1. Id, 2. Punkte , 3. Gegner, 4. Datum, 5. Farbe
		  	TableColumn<ValueClass, String> col1 = new TableColumn<ValueClass, String>("Id");        
		    col1.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column1"));

		    TableColumn<ValueClass, String> col2 = new TableColumn<ValueClass, String>("SpielId");
		    col2.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column2"));

		    TableColumn<ValueClass, String> col3 = new TableColumn<ValueClass, String>("gewonnen");
		    col3.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column3"));
		 
		    // Tabellenerzeugung
		    TableView<ValueClass> tableViewSaetze= new TableView<ValueClass>();
		    tableViewSaetze.setItems(data);

		    // Zusätzliche Spalten vermeiden
		    tableViewSaetze.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		    tableViewSaetze.getColumns().add(col1);
		    tableViewSaetze.getColumns().add(col2);
		    tableViewSaetze.getColumns().add(col3);

		    return tableViewSaetze;
	}
	 
	private TableView<ValueClass> createTableView() throws SQLException {
		 
		 Database db = new Database();
		 ResultSet rs = db.getSpiele();
		 Spiel spiel = new Spiel();
		 ObservableList<ValueClass> data = FXCollections.observableArrayList(); //Darzustellebde Daten

         while(rs.next()){//Itariere über Zeile
             for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){   //Itariere über Spalten
                 switch(i){
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
                 }
             }
           
            ValueClass cl = new ValueClass(spiel.getId(), spiel.getPunkte(), spiel.getGegener(), spiel.getDatum(), spiel.getFarbe());
             data.add(cl);
         }

		    // Spaltendefinition 1. Id, 2. Punkte , 3. Gegner, 4. Datum, 5. Farbe
		  	TableColumn<ValueClass, String> col1 = new TableColumn<ValueClass, String>("Id");        
		    col1.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column1"));

		    TableColumn<ValueClass, String> col2 = new TableColumn<ValueClass, String>("Punkte");
		    col2.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column2"));

		    TableColumn<ValueClass, String> col3 = new TableColumn<ValueClass, String>("Gegner");
		    col3.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column3"));
		    
		    TableColumn<ValueClass, String> col4 = new TableColumn<ValueClass, String>("Datum");
		    col4.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column4"));
		    
		    TableColumn<ValueClass, String> col5 = new TableColumn<ValueClass, String>("Farbe");
		    col5.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column5"));
		 
		    // Tabellenerzeugung
		    TableView<ValueClass> tableView = new TableView<ValueClass>();
		    tableView.setItems(data);

		    // Zusätzliche Spalten vermeiden
		    tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		    tableView.getColumns().add(col1);
		    tableView.getColumns().add(col2);
		    tableView.getColumns().add(col3);
		    tableView.getColumns().add(col4);
		    tableView.getColumns().add(col5);

		    return tableView;
		  }
	
	public void ZuegeAnzeigen(int satzId) throws SQLException{
		Database db = new Database();
		ResultSet rs = db.getZuege(satzId);
		db.Zug(0, true, 0, 0 );
		System.out.println("zug erstellt");
		
		//public void setStone(int row, int col, boolean coin) {
		int zugSpalte;
		int  zugZeile;
		boolean zugGegner;
		
		while(rs.next()){ // Itariere Zeilen 
			   for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){   //Itariere über Spalten
				   System.out.println("I ist = "+i);
				    switch(i){
	                 case 1:
	                	 zugSpalte = (rs.getInt(i));
	                	 System.out.println("Spalte:"+zugSpalte);
	                	 break;
	                 case 2:
	                	 zugZeile = (rs.getInt(i));
	                	 System.out.println("Zeile:"+zugZeile);
	                	 break;
	                 case 3:
	                	zugGegner =  rs.getBoolean(i); // nicht 10=% sicher, ob richtiger cast
	                	System.out.println("Gegner?"+zugGegner);
	                	 break;
	                 }
				    //Methode aufrufen die Steine Setzt 
			   }
		}
	}
}
