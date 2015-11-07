package com.viergewinnt.gui;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import com.viergewinnt.api.common.util.ReuseServermethode;
import com.viergewinnt.database.Database;
import com.viergewinnt.database.ReuseableSpiel;
import com.viergewinnt.database.ValueClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

/**
 * Der Controller des FXML "SpielEnde" zeigt die gespielten Zuege des Spiels an 
 * 
 * @author MajkenPlugge
 */
public class ControllerSpielEnde implements Initializable {

	@FXML
	Button bBeenden;

	@FXML
	TableView<ValueClass> tableViewGespielteSatz;

	@FXML
	TableColumn<ValueClass, String> col1, col2, col3, col4, col5;

	@FXML
	Label lGewinner, lGegner;
/**
 * In der Methode initialize wird definiert, welche Aktionen ausgeführt werden koennen. Solange die entsprechende FXML-Seite geoeffnet ist.
 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<ValueClass> data = FXCollections.observableArrayList(); // Darzustellende
																				// Daten
		try {
			Database db = new Database();
			lGewinner.setText("Der Gewinner des Spiels ist " + db.spielGewinner());
			lGegner.setText("Das Spiel wurde gespielt gegen " + ReuseServermethode.getGegner());
			ResultSet rs = db.getSaetze(ReuseableSpiel.getId());
			String satzId = null;
			String satzGewonnen = null;

			while (rs.next()) {// Iteration über Zeilen
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) { // Iteration
																				// über
					switch (i) {
					case 1:
						satzId = (rs.getString(i));
						break;
					case 2:
						break;
					case 3:
						satzGewonnen = (rs.getString(i));
						break;
					}// end of switch case
				} // end of for
				ValueClass cl = new ValueClass(satzId, satzGewonnen, null, null, null);
				data.add(cl);
			} // end of while

			// Spaltendefinition
			col1.setText("ID");
			col2.setText("Gewonnen");
			col1.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column1"));
			col2.setCellValueFactory(new PropertyValueFactory<ValueClass, String>("column2"));

			tableViewGespielteSatz.setItems(data);
			tableViewGespielteSatz.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * Aufruf des Welcome Screens und schließen des aktuellen Fensters
		 */
		bBeenden.setOnAction((ev) -> {
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}