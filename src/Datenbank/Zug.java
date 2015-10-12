package Datenbank;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.Database;



public class Zug {
	
	public int id;
	int satz_id;
	boolean gegner; 
	int spalte; 
	int zeile;
	
	public Zug(int satz_id, boolean gegner, int spalte,int zeile, Database db) throws SQLException{
		this.satz_id = satz_id; 
		this.gegner = gegner;
		this.spalte = spalte;
		this.zeile = zeile;
		
		//("CREATE TABLE zug (id INTEGER IDENTITY PRIMARY KEY, satz_id INTEGER, FOREIGN KEY (satz_id) REFERENCES satz(id) , spalte Integer, zeile Integer, gegner BOOLEAN )");
		// "INSERT INTO zug(satz_id, spalte, zeile, gegner) VALUES( '" +this.satz_id+"' , '"+ this.spalte +"' , '"+this.zeile+ "' , '"+ this.gegner + "')";
		PreparedStatement stZug = Database.conn.prepareStatement("INSERT INTO zug(satz_id, spalte, zeile, gegner) VALUES( ? , ? , ? , ?)");
		stZug.setInt(1 ,this.satz_id);
		stZug.setInt(2 , this.spalte);
		stZug.setInt(3 ,this.zeile);
		stZug.setBoolean(4, this.gegner );
		
		stZug.execute();
		
		
	
	
	//Get Id for actual/ this zug
	/*
	ResultSet rs =db.doQuery("CALL IDENTITY();");
	int id;
	if (rs.next()){
		id = rs.getInt(1);
		System.out.println("Zug id ist" + id); 
		this.id = id;
		}
		*/
	
	}

}
