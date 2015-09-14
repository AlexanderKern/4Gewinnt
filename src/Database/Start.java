package Database;

import java.sql.SQLException;

public class Start {

	public static void main(String[] args) {
	
		db db = new db();
		String sql_stmt;
		
		//Neuen Person eintrag erstellen
		person user = new person("fenja" , "hi");
		 try {
			user.create(user, db);
			System.out.println("user: "+ user.name + " konnte angelegt werden");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		

		
		try {
			//Delete user
			//user.delete(user , db);
			// Alle personen anzeigen
			// noch in die Methode show rein
			System.out.println(db.print_out(db.doQuery(user.show_all())).toString());
			
			db.shutdown();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
