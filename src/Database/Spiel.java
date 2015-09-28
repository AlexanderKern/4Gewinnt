package Database;

/**
*
* @author Majken Pluegge
*/

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Spiel {
	
	int id;
	String gegner;
	String date;
	int punkte;
	
	//Constructor
	public Spiel(String gegner){
	this.gegner = gegner;
	//this.date = date.toString();	
	}
	
	public void create(DatabaseCreate db , Spiel spiel) throws SQLException{
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		  GregorianCalendar now = new GregorianCalendar(); 
		 String dateString = dateFormat.format(now.getTime());
		 java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		 dateString = "'"+ sqlDate.toString()+"'";
		// dateString = sqlDate.toString();
		 //DATE '9999-12-31'
		 
		//INSERT INTO spiel(gegner, date) VALUES(   'Feind' , '2015-09-16') 
	    String stmt = "INSERT INTO spiel(gegner, date) VALUES( " + "  '" +spiel.gegner +"' , "+dateString+")";
	    System.out.println(stmt);
		db.update(stmt);	
		
	
		//Get Id for actual/ this game
		ResultSet rs =db.doQuery("CALL IDENTITY();");
		int id;
		if (rs.next()){
			id = rs.getInt(1);
			System.out.println("SPiel id ist" + id);
			spiel.id = id;
			}
		
	
	}

	//Spielausgang eintragen
	public void update_Spiel(Spiel spiel ,int punkte, DatabaseCreate db, Satz satz) throws SQLException{
		
		String stmt = "UPDATE spiel SET punkte =" + punkte + "WHERE id = " +satz.spiel_id;
		System.out.println(stmt);
		db.update(stmt);
		
		System.out.println(spiel.id +"geupdatet");
		
		
	}

}
