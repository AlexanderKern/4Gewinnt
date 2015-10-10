package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.viergewinnt.api.file.FileMain;
import com.viergewinnt.api.pusher.PusherMain;

import Database.ReuseServermethode;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

//Controller Field
public class ControllerField implements Initializable {
	@FXML
	Button button, bExit, bBack;

	@FXML
	GridPane grid;

	@FXML
	TextField colR, colY, rowR, rowY;

	@FXML
	ImageView ivOne, ivTwo, ivThree;

	@FXML
	Label lPlayerY, lPlayerR;
	@FXML
	Pane pane;

	Image imageBlue = new Image(getClass().getResourceAsStream("coinBlue.png"));
	Image imageGreen = new Image(getClass().getResourceAsStream("coinGreen.png"));
	Image imageG = new Image(getClass().getResourceAsStream("coinGrey.png"));

	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)

	{
		// /* Satz in Datenbank anlegen */
		// ReuseableSpiel reuseSpiel = new ReuseableSpiel();
		// DatabaseCreate db = new DatabaseCreate(); // funktioniert das wenn
		// hier
		// // neue db-Verbindung
		// // angelegt wird?
		// //TODO muss bei neuen satz auch wieder angelegt werden
		// try {
		// Satz satz = new Satz(db, reuseSpiel.id);
		// System.out.println("Satz mit der Id = " + satz.id + " wurde angelegt
		// und gehört zum Spiel mit der ID "
		// + satz.spiel_id);
		// } catch (SQLException e1) {
		// e1.printStackTrace();
		// }

		lPlayerR.setText("Claire");
		
		if(ReuseServermethode.getMethode().equals("Pusher")){
			final PusherMain pusherMain = new PusherMain(this);
			pusherMain.pusher(ReuseServermethode.getTeam(), 1);
		}else if(ReuseServermethode.getMethode().equals("File")){
			new FileMain(this, 1).start();
		}

		/* Setze Stein */
//		button.setOnAction((ev) -> {
//			
//			 int row = (int) (Math.random() * 7);
//			 int col = (int) (Math.random() * 6);
//			 Label l = new Label("");
//			 boolean bol;
//			
//			 int random = (int) (Math.random()*10);
//			 if(random <5)
//			 {
//			 bol = true;
//			 }
//			 else{bol = false;}
//			
//			
//			 setStone(row, col, bol);
//
//		}); // end of button

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

	}// end of init

	public void setStone(int row, int col, boolean coin) {
		// coin green = true coin blue = false
		Platform.runLater(new Runnable() {
			public void run() {
				if (coin == true) {
					Label l = new Label("");
					l.setGraphic(new ImageView(imageGreen));
					grid.setRowIndex(l, row);
					grid.setColumnIndex(l, col);

					grid.getChildren().addAll(l);
				}

				else {
					Label l = new Label("");
					l.setGraphic(new ImageView(imageBlue));
					grid.setRowIndex(l, row);
					grid.setColumnIndex(l, col);

					grid.getChildren().addAll(l);
				}
			}
		});
	}
	
	public void setResult(String result, int sequenceNumber) {
		Platform.runLater(new Runnable() {
			public void run() {
				ivOne.setImage(imageBlue);
				if(result.equals(ReuseServermethode.getTeam())){
					if(sequenceNumber == 1){
						ivOne.setImage(imageBlue);
					} else if(sequenceNumber == 2){
						ivTwo.setImage(imageBlue);
					}else{
						ivThree.setImage(imageBlue);
					}
				}else if(result != ReuseServermethode.getTeam() && result != "offen"){
					if(sequenceNumber == 1){
						ivOne.setImage(imageGreen);
					} else if(sequenceNumber == 2){
						ivTwo.setImage(imageGreen);
					}else{
						ivThree.setImage(imageGreen);
					}
				}else{
					if(sequenceNumber == 1){
						ivOne.setImage(imageG);
					} else if(sequenceNumber == 2){
						ivTwo.setImage(imageG);
					}else{
						ivThree.setImage(imageG);
					}
				}
			}
		});
	}

}
