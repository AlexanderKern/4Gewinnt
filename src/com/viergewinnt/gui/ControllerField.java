package com.viergewinnt.gui;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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
public class ControllerField implements Initializable {
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

		/*--------------------------------------------------------------------------------------------------------------
		 * Satz in Datenbank anlegen
		 */
		Database db = new Database();
		bNewSatz.setVisible(false);
		bSatzVerwerfen.setVisible(false);
		tbGewonnen.setVisible(false);
		tbGewonnen.setToggleGroup(myToggleGroup);
		tbVerloren.setVisible(false);
		tbVerloren.setToggleGroup(myToggleGroup);
		lGewonnen.setVisible(false);
		try {
				setGespielteSaetze(db.getGewonneneSaetze(ReuseableSpiel.getId()));
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			lSatz.setText(String.valueOf(db.getAnzahlSaetze(ReuseableSpiel.getId())+1));
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		try {
			System.out.println("Satz anlegen Spiel id"+ReuseableSpiel.getId());
			db.createSatz(ReuseableSpiel.getId());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// --------------------------------------------------------------------------------------------------------------

		if (ReuseServermethode.getTeam().equals("O")) {
			lPlayerR.setText("Claire");
			lPlayerY.setText(ReuseServermethode.getGegner());
		} else {
			lPlayerY.setText("Claire");
			lPlayerR.setText(ReuseServermethode.getGegner());
		}

		if (ReuseServermethode.getMethode().equals("Pusher")) {
			try {
				final PusherMain pusherMain = new PusherMain(this);
				pusherMain.pusher(ReuseServermethode.getTeam(), db.getAnzahlSaetze(ReuseableSpiel.getId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ReuseServermethode.getMethode().equals("File")) {
			try {
				new FileMain(this, db.getAnzahlSaetze(ReuseableSpiel.getId())).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/* Setze Stein */
		// button.setOnAction((ev) -> {
		//
		// int row = (int) (Math.random() * 7);
		// int col = (int) (Math.random() * 6);
		// Label l = new Label("");
		// boolean bol;
		//
		// int random = (int) (Math.random()*10);
		// if(random <5)
		// {
		// bol = true;
		// }
		// else{bol = false;}
		//
		//
		// setStone(row, col, bol);
		//
		// }); // end of button

		bBack.setOnAction((ev) -> {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selectScreen.fxml"));
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
		}); // end of cancel

		bExit.setOnAction((ev) -> {
			((Node) (ev.getSource())).getScene().getWindow().hide();
		}); // end of cancel

		bNewSatz.setOnAction((ev) -> {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selectScreen.fxml"));
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
		tbGewonnen.setOnAction((ev) ->{

			try {
				db.updateSatz("gewonnen", ReuseableSatz.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lGewonnen.setText("Claire hat gewonnen");
			
		});
		
		tbVerloren.setOnAction((ev) ->{

			try {
				db.updateSatz("verloren", ReuseableSatz.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lGewonnen.setText("Claire hat verloren");
			
		});
		
		bSatzVerwerfen.setOnAction((ev) ->{
			try {
				db.loeschenZuege(ReuseableSatz.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Züge gelöscht");
			try {
				db.satzloeschen(ReuseableSatz.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Satz gelöscht");
			lGewonnen.setText("Satz wurde verworfen");
			tbGewonnen.setVisible(false);
			tbVerloren.setVisible(false);
		});
		
	}// end of init

	/**
	 * Setzt den gespielten Stein ins Feld
	 * 
	 * @param row
	 *            Spalte
	 * @param col
	 *            Zeile
	 * @param coin
	 *            Steinfarbe
	 */
	public void setStone(int row, int col, boolean coin) {
		// coin green = true coin blue = false
		Platform.runLater(new Runnable() {
			public void run() {
				if (coin == true) {
					ImageView iv = new ImageView(imageGreen);
					// Label l = new Label("");
					// l.setGraphic(new ImageView(imageGreen));
					grid.setRowIndex(iv, row);
					grid.setColumnIndex(iv, col);

					grid.getChildren().addAll(iv);
				}

				else {
					ImageView iv = new ImageView(imageBlue);
					// Label l = new Label("");
					// l.setGraphic(new ImageView(imageBlue));
					grid.setRowIndex(iv, row);
					grid.setColumnIndex(iv, col);

					grid.getChildren().addAll(iv);
				}
			}
		});
	}// end of setStone

	/**
	 * 
	 * @param result
	 * @param sequenceNumber aktuelle Satznummer
	 */
	public void setResult(String result, int sequenceNumber) {
		Platform.runLater(new Runnable() {
			public void run() {

				String eigenesTeam = "Spieler " + ReuseServermethode.getTeam();

				if (result.equals(eigenesTeam)) {
					if (sequenceNumber == 1) {
						if (ReuseServermethode.getTeam().equals("X")) {
							ivOne.setImage(imageGreen);
						} else {
							ivOne.setImage(imageBlue);
						}
					} else if (sequenceNumber == 2) {
						if (ReuseServermethode.getTeam().equals("X")) {
							ivTwo.setImage(imageGreen);
						} else {
							ivTwo.setImage(imageBlue);
						}
					} else {
						if (ReuseServermethode.getTeam().equals("X")) {
							ivThree.setImage(imageGreen);
						} else {
							ivThree.setImage(imageBlue);
						}
					}
				} else if (result != eigenesTeam && result != "offen") {
					if (sequenceNumber == 1) {
						if (ReuseServermethode.getGegnerfarbe() == true) {
							ivOne.setImage(imageGreen);
						} else {
							ivOne.setImage(imageBlue);
						}
					} else if (sequenceNumber == 2) {
						if (ReuseServermethode.getGegnerfarbe() == true) {
							ivTwo.setImage(imageGreen);
						} else {
							ivTwo.setImage(imageBlue);
						}
					} else {
						if (ReuseServermethode.getGegnerfarbe() == true) {
							ivThree.setImage(imageGreen);
						} else {
							ivThree.setImage(imageBlue);
						}
					}

				} else {
					if (sequenceNumber == 1) {
						ivOne.setImage(imageG);
					} else if (sequenceNumber == 2) {
						ivTwo.setImage(imageG);
					} else {
						ivThree.setImage(imageG);
					}
				}
			}
		});

	}// end of setResult

	/**
	 * Bei Satzende wird der Button zum Aufruf eines neuen Satz angeziegt
	 * 
	 * @param visible
	 */
	public void sichtbar(boolean visible, String sieger) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				bNewSatz.setVisible(visible);
				bSatzVerwerfen.setVisible(visible);
				tbGewonnen.setVisible(visible);
				tbVerloren.setVisible(visible);
				lGewonnen.setVisible(visible);
				
				if(sieger.equals("Claire")){
					tbGewonnen.setSelected(true);
					lGewonnen.setText("Claire hat gewonnen");
				}else{
					tbVerloren.setSelected(true);
					lGewonnen.setText("Claire hat verloren");
				}

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sieger");
				alert.setHeaderText("Der Gewinner des Satzes ist");
				alert.setContentText(sieger);
				alert.show();
				
	
			}
		});
	}

	public void setGespielteSaetze(String[] gewonneneSaetze) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {

System.out.println(gewonneneSaetze.length);
				for (int i = 0; i < gewonneneSaetze.length; i++) {
					System.out.println(gewonneneSaetze[i]);
						if (i == 0 && gewonneneSaetze[i] != null) {
							if (gewonneneSaetze[i].equals("X")) {
								ivOne.setImage(imageGreen);
							} else if(gewonneneSaetze[i].equals("O")) {
								ivOne.setImage(imageBlue);
							} else{
								ivOne.setImage(imageG);
							}

						} else if (i == 1 && gewonneneSaetze[i] != null) {
							if (gewonneneSaetze[i].equals("X")) {
								ivTwo.setImage(imageGreen);
							} else if(gewonneneSaetze[i].equals("O")) {
								ivTwo.setImage(imageBlue);
							} else{
								ivTwo.setImage(imageG);
							}
						} else if(gewonneneSaetze[i] != null){
							if (gewonneneSaetze[i].equals("X")) {
								ivThree.setImage(imageGreen);
							} else if(gewonneneSaetze[i].equals("O")) {
								ivThree.setImage(imageBlue);
							} else{
								ivThree.setImage(imageG);
							}
						}
					}

				}
		});

	}
}// end of class
