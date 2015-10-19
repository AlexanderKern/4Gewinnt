package Database;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Parent;

public class main extends Application{

	
	public static void main(String[] args)  {
		launch(args);
			
	}
	 public void start(Stage stage) throws SQLException {
		    Scene scene = new Scene(new Group());
		    stage.setTitle("MyTableView");
		    stage.setWidth(450);
		    stage.setHeight(500);

		    TableView tableView = createTableView();
		    

		    //ValueClass vC = (ValueClass) tableView.getSelectionModel().getSelectedItem();
		    //System.out.println(""+vC.getColumn1()); // Hole Id 
		    
		
		    
		    
		    ((Group) scene.getRoot()).getChildren().addAll(tableView);

		    stage.setScene(scene);
		    stage.show();
		    
		
		  }

	 private TableView createTableView() throws SQLException {
		 
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
		    
		    //Event Listner
		    tableView.getSelectionModel().selectedIndexProperty().addListener(
		    		                new RowSelectChangeListener());




		    tableView.getColumns().add(col1);
		    tableView.getColumns().add(col2);
		    tableView.getColumns().add(col3);
		     tableView.getColumns().add(col4);
		   tableView.getColumns().add(col5);
		    
	
		    return tableView;
		  }
}
