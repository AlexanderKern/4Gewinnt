package Database;

import java.sql.SQLException;

public class person {
	
	public String name;
	public String passwort;
	
	// constructor
	public person(String name, String passwort){
		this.name = name;
		this.passwort = passwort;
	}
	
	//Create a person
	//First call constructor and than create for insert a new database entry
	public void create(person person, db db) throws SQLException{
		
		String stmt_person;
		stmt_person = "INSERT INTO person(name, password) VALUES( '" +person.name +"' , '"+ person.passwort +"' )";
		db.update(stmt_person);
	}
	
	public void delete(person person, db db) throws SQLException{
		String stmt_person;
		stmt_person = "DELETE FROM person WHERE name = '" +person.name + "' AND password = '" +person.passwort+"'";
		System.out.println(stmt_person);
		
		db.update(stmt_person);
		System.out.println("User"+ person.name +"wurde gelöscht");
		
	}

	public String show_all(){
		// Show all users
		String stmt = "SELECT * FROM person";
		System.out.println(stmt);
		return stmt;
	}
}
