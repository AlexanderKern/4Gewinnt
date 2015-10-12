package Datenbank;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Database.Database;


public class Spieler {
	
	public static String name;
	
	public Spieler(String name){
			Spieler.name = name;
		}
		
	
	public void createSpieler(Database db) throws SQLException{
		
		//Abfrage, ob Spielr bereits in Datenbank angelegt worden ist 
		//Wenn ja, denn kein neues anlegen eines Spielers
		
		PreparedStatement spieler = Database.conn.prepareStatement("INSERT INTO person(name) VALUES( ? )");
		spieler.setString(1, Spieler.name);
		spieler.execute();
		spieler.close();
		System.out.println("Spieler angelegt");
		
	}

}
