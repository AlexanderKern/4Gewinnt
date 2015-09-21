package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Satz {
	int id;
	int spiel_id;
	Boolean gewonnen;

	//constructor
	public Satz(db db, int spiel_id) throws SQLException{
		//benötigt spiel id des zugehörigen Spiels um erzeugt zuwerden
		this.spiel_id = spiel_id;
		String stmt = "INSERT INTO satz(spiel_id) VALUES( '" +this.spiel_id+"')";
		db.update(stmt);
		
		ResultSet rs =db.doQuery("CALL IDENTITY();");
		int id;
		if (rs.next()){
			id = rs.getInt(1);
			System.out.println("Spiel Id ist" + spiel_id);
			System.out.println("Satz id ist" + id);
			this.id = id;
			
			}	
	}// end of constructor
	
	//setWinner /or change winner =) 
	public void changeWinner(Spiel spiel, db db, Satz satz ,boolean gewonnen) throws SQLException{
		int gewonneneSätze ; 
	    int AnzahlSätze ;
		ResultSet rs = null;
		String stmt = "Update satz SET gewonnen = " +gewonnen +" WHERE id = " +satz.id;
		
		db.update(stmt);
	
		//Check ob 3 Sätze bereitsgespielt sind
		stmt = "SELECT * FROM satz WHERE spiel_id =  "+ satz.spiel_id;
		rs = db.doQuery(stmt);
		
		while (rs.next()){
			AnzahlSätze = (rs.getRow());
			System.out.println("Anzahl der gespielten Sätze:"+ AnzahlSätze+"zum SPiel "+ satz.spiel_id);
			
			if(AnzahlSätze == 3){ // wenn 3 STätze gespielt, dann update das Spiel ergebnis
			// Gewonnene Sätze zählen
			stmt = "SELECT COUNT(*)  FROM satz WHERE spiel_id = " +satz.spiel_id + " AND gewonnen = true ";
				rs = db.doQuery(stmt);
				rs.next();
				 gewonneneSätze = rs.getInt(1);
				 System.out.println(gewonneneSätze);	
				 
				 spiel.update_Spiel(spiel, gewonneneSätze , db, satz);
	 
				}//end of if
		}// end of while

	}//end of changeWinner

	
}

