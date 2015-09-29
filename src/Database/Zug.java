package Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Zug {
	public int id;
	int satz_id;
	boolean gegner; 
	int spalte; 
	
	public Zug(int satz_id, boolean gegner, int spalte, DatabaseCreate db) throws SQLException{
		this.satz_id = satz_id; 
		this.gegner = gegner;
		this.spalte = spalte;
	
	String stmt = "INSERT INTO zug(satz_id, spalte, gegner) VALUES( '" +this.satz_id+"' , '"+ this.spalte +"' , '"+ this.gegner + "')";
	db.update(stmt);
	
	//Get Id for actual/ this zug
	ResultSet rs =db.doQuery("CALL IDENTITY();");
	int id;
	if (rs.next()){
		id = rs.getInt(1);
		System.out.println("Zug id ist" + id); 
		this.id = id;
		}
	
	}
	
	// TODO Check ob schon 7*8 = 56 Züge für diesen Satz_id = satz.id gespielt  worden sind 
	
	
	

}
