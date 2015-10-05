package Database;

import java.net.ConnectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Start {

	public static void main(String[] args) throws SQLException {
	
		//needed for database connection
		DatabaseCreate db = new DatabaseCreate();
			
		//creates all database, if they doesnt exist
		try {
			db.create_table(db);
			System.out.println("datenbanken angelegt");
		} catch (SQLException e2) {
			System.out.println("db existieren");
		// database tables allready exist--> nothing happens
		}
		System.out.println("check");
		
		//Neuen Person eintrag erstellen
		
		/*Spieler spieler = new Spieler("SuperHirnii");
		 try {
			spieler.create(spieler, db);
			System.out.println("user: "+ spieler.name + " konnte angelegt werden");
	
			
		//Delete user
			//user.delete(user , db);
			// Alle personen anzeigen
		//	System.out.println(db.print_out(spieler.show_all(db)).toString()); 
		
		} catch (SQLException e1) {
		e1.printStackTrace();
		}
		*/
		
		
		
		
		
		//Neues Spiel Anlegen
				Spiel spiel = new Spiel("blödes anderes Team", true);
				spiel.create(db, spiel);
		//Neuen Satz anlegen:
		/*	Satz satz = new Satz(db, spiel.id);
			System.out.println("Satz id main" + satz.id);
			
		// Neuen Zug anlegen --> int satz_id, boolean gegner, int spalte, db db
		// TODO wie wird gesagt, ob das unserer Zug ist oder der Zug des gegners
			Boolean gegner = true; 
			int spalte = 1; 
			Zug zug = new Zug(satz.id, gegner,  spalte, db);
			System.out.println(zug.id);
			
			
			
			
			//Boolean gewonnen = true; //
			// satz.changeWinner(spiel, db, satz, gewonnen);
		 /* Test ob EIntrag erzeigt wird, wenn drei sätze gespielt
		  * satz = new Satz(db, spiel.id);
			satz.changeWinner(spiel, db, satz, gewonnen);
			 satz = new Satz(db, spiel.id);
			 satz.changeWinner(spiel, db, satz, gewonnen);
			 */
		 
		 /*
		  * Select Staments
		  * 1. Alle gespielten Spiele anzeigen 
		  */
		/*PreparedStatement pstmtCount = db.conn.prepareStatement("SELECT COUNT(*) FROM spiel");
		ResultSet rsCount = db.doQueryPrepStmnt(pstmtCount);
		//rsCount.getInt(i);
		int count = 0;
		while(rsCount.next()){
			count = rsCount.getInt(1);
			
		}
		
		System.out.println(count);
		 
		 PreparedStatement pstmt = db.conn.prepareStatement("SELECT punkte, gegner, date FROM spiel");
		// pstmt.setNString(1, "*" );
		 
		ResultSet rs = db.doQueryPrepStmnt(pstmt);
		System.out.println("rs erfolgrecih");
		
		try {
			db.outputResultSet(rs, count);	
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*2. Alle Züge eines Spiels*/
		
		/*3. Wie viel % mit Gelben wie viel % mit roten Steinen gewonnen ( Auf Satz oder SPiel bezogen?)*/
		
		//Datenbank Connection schließen
		try {	
			db.shutdown();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		


		
	}

}
