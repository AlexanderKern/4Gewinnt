package Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Spieler {
	
	public String name;
	
	//Create a person: 1. Call Constructor, 2. call create for new database entry 

	// constructor
	public Spieler(String name){
		this.name = name;
	}
	
	public void create(Spieler spieler, DatabaseCreate db) throws SQLException{
		
		String stmt_person;
		//Check if a data entry already exists
		
		//If false
		stmt_person = "INSERT INTO person(name) VALUES( '" +spieler.name +"');";
		db.update(stmt_person);
		// if true return;
	}
	
	public void delete(Spieler person, DatabaseCreate db) throws SQLException{
		String stmt_person;
		stmt_person = "DELETE FROM person WHERE name = '" +person.name + "'";
		System.out.println(stmt_person);
		db.update(stmt_person);
		System.out.println("User"+ person.name +"wurde gelöscht");
		
	}

	public ResultSet show_all(DatabaseCreate db ) throws SQLException{
		// Show all spieler
		String stmt = "SELECT * FROM person";
		ResultSet rs = null; 
		System.out.println(stmt);
		rs = db.doQuery(stmt);
		return rs;
	}
}
