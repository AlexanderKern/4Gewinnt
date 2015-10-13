package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class Database {

	// connection to the database, presist fpr the life of the programm
		public static Connection conn;
		 
	// constructor
		public Database(){
			
	// Load the HSQL Database Engine JDBC driver
			try {
				Class.forName("org.hsqldb.jdbcDriver");
				//Connect to the database, load the db files and start database if it isn't already running
				conn = DriverManager.getConnection(
						"jdbc:hsqldb:MyDatabase" , // filenames
						"sa", //username
						""); //password
			}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			}// catch
			
		}
		
		//create table person, spiel, satz, zug 
		public void createTable(Database db) throws SQLException{
			
			
			String person_table = ("CREATE TABLE person ( id INTEGER IDENTITY PRIMARY KEY,  name VARCHAR(256) UNIQUE )");
			db.update(person_table);
			String spiel_table = ("CREATE TABLE spiel ( id INTEGER IDENTITY PRIMARY KEY, punkte VARCHAR(256) , gegner VARCHAR(256), date DATE, farbe BOOLEAN )") ;
			db.update(spiel_table);
			String satz_table = ("CREATE TABLE satz ( id INTEGER IDENTITY PRIMARY KEY, spiel_id INTEGER , FOREIGN KEY (spiel_id) REFERENCES spiel(id) , gewonnen VARCHAR(256)) ");
			db.update(satz_table);
			String zug_table = ("CREATE TABLE zug (id INTEGER IDENTITY PRIMARY KEY , satz_id INTEGER,  spalte Integer, zeile Integer, gegner BOOLEAN )");
			db.update(zug_table);
			
		}
		
		public void deleteTable (Database db) throws SQLException{
			
			//String[] dropTable = {"DROP table person", "DROP table spiel" ,"DROP table satz" , "DROP table zug"};
			db.update("DROP table person");
			db.update("DROP table spiel");
			db.update("DROP table satz");
			db.update("DROP table zug");
			
			/*
			for(int i = 0; i< dropTable.length; i++ ){
				db.update(dropTable[i]);
				
				 System.out.println("Gelöscht");
			}
			*/
			
		}
		
		public synchronized void update(String sql_command) throws SQLException{
			
			//use for SQL commands CREATE, DROP, INSERT and UPDATE
			Statement stmt = null;
			stmt = conn.createStatement();
			stmt.executeUpdate(sql_command);
			stmt.close();

		}
		
		//Prepared Stmnt
		public ResultSet doQueryPrepStmnt( PreparedStatement stmnt) throws SQLException{
		ResultSet rs = 	stmnt.executeQuery();
		return rs;
		}
		

		public void shutdown() throws SQLException{
			// performs a clean shutdown and close the database connection
			
			Statement stmt = conn.createStatement();
			stmt.executeQuery("SHUTDOWN");
			conn.close();
		}
	
}
