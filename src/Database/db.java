package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class db {
	
// connection to the database, presist fpr the life of the programm
	public static Connection conn;
	 
// constructor
	public db(){
		
// Load the HSQL Database Engine JDBC driver
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			//Connect to the database, load the db files and start database if it isn't already running
			conn = DriverManager.getConnection(
					"jdbc:hsqldb:DB" , // filenames
					"sa", //username
					""); //password
		}catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
		}// catch
	}
	
	public void shutdown() throws SQLException{
		// performs a clean shutdown and close the database connection
		Statement stmt = conn.createStatement();
		stmt.executeQuery("SHUTDOWN");
		conn.close();
	}
	
	public ResultSet doQuery( String query) throws SQLException{
		// use for SQL command SELECT
		Statement st = null;
		ResultSet rs = null;
		
		st = conn.createStatement();
		rs = st.executeQuery(query); // run the query
		st.close();
		return rs; // gibt das result von der Query zurück, falls dies angezeigt werden soll--> db.printout(Result)
		
	}
	
	public ArrayList<String> print_out(ResultSet rs) throws SQLException{
		
		//Save result of the query in a String Array List
	
		ArrayList <String> query_result = new ArrayList<String>();
		
		ResultSetMetaData meta = rs.getMetaData();
		int colmax = meta.getColumnCount();
		int i;
		Object o = null;

			while(rs.next()){ // setz Zeiger auf nächste Zeile
				
				for (i = 0; i < colmax; ++i) { //setzt Zeiger auf nächste Spalte
					o = rs.getObject(i + 1); // Is SQL the first column is indexed
					query_result.add( o.toString() );
				}
			}
			
			rs.close();
			return query_result;
			
	}
	
	public synchronized void update(String sql_command) throws SQLException{
		
		//use for SQL commands CREATE, DROP, INSERT and UPDATE
		Statement stmt = null;
		stmt = conn.createStatement();
		//run sql_command
		
		stmt.executeUpdate(sql_command);
		stmt.close(); 
		
	}

	

}
