package Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


import javafx.scene.chart.PieChart.Data;

public class Spiel {
	
	public  String gegner;
	public  boolean farbe;
	public int id;
	public int punkte;
	
	public Spiel(String gegner ){
		this.gegner = gegner;	
		
		}

	public void createSpiel(Spiel spiel,  Database db) throws SQLException{
		
		int spielId;
		
		//Datum 
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 GregorianCalendar now = new GregorianCalendar(); 
		 String dateString = dateFormat.format(now.getTime());
		 java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		 dateString =  sqlDate.toString();
		 
		//INSERT INTO spiel(id, gegner, date) VALUES(  NULL 'Feind' , '2015-09-16') 
		 PreparedStatement stSpiel = Database.conn.prepareStatement("INSERT INTO spiel( gegner, date, farbe) VALUES( ? , ? , ?);");
		 stSpiel.setString(1, spiel.gegner);
		 stSpiel.setString(2,  dateString);
		 stSpiel.setBoolean(3, spiel.farbe);
		
		 stSpiel.executeUpdate();
		 
		//Get Id for actual spiel
		 PreparedStatement callId = Database.conn.prepareStatement("CALL IDENTITY()");
	     ResultSet rsId = callId.executeQuery();
	     rsId.next();
	     spielId = rsId.getInt(1);
	     spiel.id = spielId;
	     rsId.close();
		
		
		//Speichern der notwendigen Information für einen globalen Zugriff
		ReuseableSpiel reusespiel = new ReuseableSpiel();
		reusespiel.setId(spiel.id);
		reusespiel.setName(spiel.gegner);
	
	}
	
	
	//Spielausgang eintragen 
	public void spielende (int spielId ,int punkte) throws SQLException {
		
		PreparedStatement stSpielende = Database.conn.prepareStatement("UPDATE spiel SET punkte = ? WHERE id = ?");
		stSpielende.setInt(1, punkte);
		stSpielende.setInt(2, spielId);
	}
	
	public int getpunkte (Spiel spiel, Satz satz){
	
		
		return spiel.punkte;
		
	}
	
	//Alle Spiele aus der Datenbank holen 
		public void getSpiele() throws SQLException{
			
			PreparedStatement stGet = Database.conn.prepareStatement("SELECT COUNT(*) FROM spiel");
			ResultSet rsGet = (ResultSet) stGet; 
			
		}
}
