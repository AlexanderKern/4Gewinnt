package Database;

import java.sql.SQLException;

public class Start {

	public static void main(String[] args) throws SQLException {
	
		//needed for database connection
		DatabaseCreate db = new DatabaseCreate();
			
		//creates all database, if they doesnt exist
		try {
			db.create_table(db);
			System.out.println("datenbanken angelegt");
		} catch (SQLException e2) {
		// database tables allready exist--> nothing happens
		}
		System.out.println("check");
		
		//Neuen Person eintrag erstellen
		/*
		person spieler = new person("SuperBrian");
		 try {
			spieler.create(spieler, db);
			System.out.println("user: "+ spieler.name + " konnte angelegt werden");
		/*	
			
		//Delete user
			//user.delete(user , db);
			// Alle personen anzeigen
		//	System.out.println(db.print_out(spieler.show_all(db)).toString()); 
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		*/
		
		
		//Neues Spiel Anlegen
				Spiel spiel = new Spiel("blödes anderes Team");
				spiel.create(db, spiel);
		//Neuen Satz anlegen:
			Satz satz = new Satz(db, spiel.id);
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
			
		
		//Datenbank Connection schließen
		try {	
			db.shutdown();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		


		
	}

}
