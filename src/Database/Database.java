package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Die Klasse Database beinhaltet alle notwendigen Methoden, die fuer die Erstellung der Datenbank und der Ausfuehrung von Operationen notwendig sind
 * @author MajkenPlugge
 *
 */

public class Database {

		public static Connection conn; // Verbindung zur Datenbank besteht zur gesamten Laufzeit des Programms 
		 
	/**Konstruktor: 
	 * laedt den HSQL Database Engine JDBC driver
	 * und stellt eine Verbindung zur Datenbank her (laeddt die benoetigten Datenbank-Files und startet die Datenbank, falls diese nocht nicht laeuft) 
	 */
		public Database(){
			
	
			try {
				Class.forName("org.hsqldb.jdbcDriver"); // Load the HSQL Database Engine JDBC driver
				conn = DriverManager.getConnection( //Connect to the database, load the db files and start database if it isn't already running
						"jdbc:hsqldb:MyDatabase" , // filenames
						"sa", //username
						""); //password
			}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			}// catch
			
		}
		
		/**
		 * Erstellt die alle Tabellen, d.h. die Tabelle Spieler, Spiel, Satz und Zug 
		 * @param db Datenbankverbindung
		 * @throws SQLException
		 */
		//create table person, spiel, satz, zug 
		public void createTable(Database db) throws SQLException{
			
			
			String person_table = ("CREATE TABLE spieler ( id INTEGER IDENTITY PRIMARY KEY,  name VARCHAR(256) UNIQUE )");
			db.update(person_table);
			String spiel_table = ("CREATE TABLE spiel ( id INTEGER IDENTITY PRIMARY KEY, punkte VARCHAR(256) , gegner VARCHAR(256), date DATE, farbe BOOLEAN )") ;
			db.update(spiel_table);
			String satz_table = ("CREATE TABLE satz ( id INTEGER IDENTITY PRIMARY KEY, spiel_id INTEGER , FOREIGN KEY (spiel_id) REFERENCES spiel(id) , gewonnen VARCHAR(256)) ");
			db.update(satz_table);
			String zug_table = ("CREATE TABLE zug (id INTEGER IDENTITY PRIMARY KEY , satz_id INTEGER,  spalte Integer, zeile Integer, gegner BOOLEAN )");
			db.update(zug_table);
			
		}
		
		public void deleteTable (Database db) throws SQLException{
			
			db.update("DROP table person");
			db.update("DROP table spiel");
			db.update("DROP table satz");
			db.update("DROP table zug");
			
		}
		
		/**
		 * führt die SQL-Statements CREATE, DROP, INSERT und Update aus 
		 * @param sql_command SQL-Befehl 
		 * @throws SQLException
		 */
		public synchronized void update(String sql_command) throws SQLException{
			Statement stmt = null;
			stmt = conn.createStatement();
			stmt.executeUpdate(sql_command);
			stmt.close();
		}
		
		/**
		 * führt die SQL-Abfragen aus und gibt ein das Ergebnis in Form eines ResultSets zurück
		 * @param stmnt SQL-Abfrage 
		 * @return Result Set Ergebnis der SQL-Abfrage 
		 * @throws SQLException
		 */
		public ResultSet doQueryPrepStmnt( PreparedStatement stmnt) throws SQLException{
		ResultSet rs = 	stmnt.executeQuery();
		return rs;
		}
		

		/**
		 * fährt die Datenbankverbindung sauber herunter und schließt die Datenbankverbindung
		 * @throws SQLException
		 */
		public void shutdown() throws SQLException{
			Statement stmt = conn.createStatement();
			stmt.executeQuery("SHUTDOWN");
			conn.close();
		}
		
		/**
		 * erstellt einen neuen Spieler in der Datenbank
		 * @param Name des gegnerischen Spielers
		 */
		public void createSpieler(String spielerName) throws SQLException{
			
			PreparedStatement st = conn.prepareStatement("SELECT COUNT(*) FROM spieler WHERE name = ?");
			st.setString(1, spielerName);
			ResultSet rs = st.executeQuery();
			rs.next();
			
			 if (rs.getInt(1) == 0){ //Existiert bereits ein Spieler mit dem Namen in der Datenbank? 
					PreparedStatement stSpieler = conn.prepareStatement("INSERT INTO spieler(name) VALUES( ? )");
					stSpieler.setString(1, spielerName);
					stSpieler.execute();
					stSpieler.close();
					System.out.println("Spieler angelegt"+ spielerName);
			 }else {
				System.out.println("Spieler existiert bereits in der Datenbank");
			}
			
		}
		
		/**
		 * erstellt einen Eintrag eines Spiels in der Datenbank
		 * und setzt die Werte gegner und farbe von der Instanz Spiels 
		 * @param gegner Name des gegnerischen Spielers 
		 * @param farbe Steinfarbe
		 * @throws SQLException
		 */
		public void createSpiel(String gegner, boolean farbe) throws SQLException{
			
			int spielId;
			
			//Datum 
			 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 GregorianCalendar now = new GregorianCalendar(); 
			 String dateString = dateFormat.format(now.getTime());
			 java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			 dateString =  sqlDate.toString();
			 
			 PreparedStatement stSpiel = Database.conn.prepareStatement("INSERT INTO spiel( gegner, date, farbe) VALUES( ? , ? , ?);");
			 stSpiel.setString(1, gegner);
			 stSpiel.setString(2,  dateString);
			 stSpiel.setBoolean(3, farbe);
			
			 stSpiel.executeUpdate();
			 
			//Get Id for actual spiel
			 PreparedStatement callId = Database.conn.prepareStatement("CALL IDENTITY()");
		     ResultSet rsId = callId.executeQuery();
		     rsId.next();
		     spielId = rsId.getInt(1);
		     rsId.close();
			
			
			//Speichern der notwendigen Information für einen globalen Zugriff
			ReuseableSpiel reusespiel = new ReuseableSpiel();
			reusespiel.setId(spielId);
			reusespiel.setName(gegner);
			
		}
		
		/**
		 * speichert den Ausgang des Spiels in der Datenbank 
		 * @param spielId Id des Spiels
		 * @param punkte Punkte des Spiels 
		 * @throws SQLException
		 */
		public void spielEnde (int spielId ,int punkte) throws SQLException {
			
			PreparedStatement stSpielende = conn.prepareStatement("UPDATE spiel SET punkte = ? WHERE id = ?");
			stSpielende.setInt(1, punkte);
			stSpielende.setInt(2, spielId);
			stSpielende.executeUpdate();
		}
		
		/**
		 * gibt erreichten Punkte eines Spiels zurück
		 * @param spielId Id des Spiels
		 * @return Punkte des Spiels 
		 * @throws SQLException
		 */
		public int getSpielPkt(int spielId) throws SQLException{

			 int punkte = 0; 
			 PreparedStatement st = conn.prepareStatement("SELECT * FROM satz WHERE spiel_Id = ? AND gewonnen = ?");
			 st.setInt(1, spielId);
			 st.setString(2, "gewonnen");
			 ResultSet rs = st.executeQuery();
			 
			  if (rs.next()) {
			        rs.last();
			        punkte = rs.getRow();
			        System.out.println("total rows is : " + punkte);
			    } else {
			        System.out.println("No Data");
			    }

				return punkte;
		}
		
		
		/**
		 * gibt alle Eintraege aus der Datenbank zurück
		 * @throws SQLException
		 */
		public ResultSet getSpiele() throws SQLException{
		
					
			PreparedStatement stGet = Database.conn.prepareStatement("SELECT * FROM spiel");
			ResultSet rs = stGet.executeQuery();
			//TODO wie sollen die Spiel an Damm übergeben werden? 		
			// SpielId Gegner Punkte 
			
			return rs;
			
			/*
			if(rs.next()){
				for( int i = 1 ; i <= rs.getMetaData().getColumnCount(); i++){
					System.out.println(rs.getString(i));
				}
				
				
			}
			*/
		}
		
		/**
		 * erstellt einen einen Satz in der Datenbank
		 * und setz die SatzId bei der Instanz Satz
		 * @param spielId
		 * @throws SQLException
		 */
		public void createSatz( int spielId ) throws SQLException{
		
			int satzId;
			
			PreparedStatement stSatz = conn.prepareStatement("INSERT INTO satz(spiel_id) VALUES(?)");
			stSatz.setInt(1,spielId);
			stSatz.execute();
			stSatz.close();
			
			PreparedStatement callId = conn.prepareStatement("CALL IDENTITY();");
			
			ResultSet rsId = callId.executeQuery();
		     rsId.next();
		     satzId = rsId.getInt(1);
		     rsId.close();
						
			
			ReuseableSatz reuseSatz = new ReuseableSatz();
			reuseSatz.setId(satzId);

		}
		
		/**
		 * speichert den Ausgang des Satzes in die Datenbank
		 * @param gewonnen
		 * @param satzId
		 * @throws SQLException
		 */
		public void updateSatz(String gewonnen, int satzId) throws SQLException{
			PreparedStatement stSatzende = conn.prepareStatement("UPDATE satz SET gewonnen = ? WHERE id = ?");
			stSatzende.setString(1, gewonnen);
			stSatzende.setInt(2, satzId);
			stSatzende.executeUpdate();
		}
		
		/**
		 * gibt die Anzahl der gespielten Sätzen zu einem Spiel zurueck
		 * @param spielId
		 * @return anzahlSaetze
		 * @throws SQLException
		 */
		public int getAnzahlSaetze(int spielId) throws SQLException{
			int anzahlSaetze;
			
			PreparedStatement dritterSatz = conn.prepareStatement("SELECT * FROM satz WHERE spiel_id = ? ");
			dritterSatz.setInt(1, spielId);
			anzahlSaetze =  dritterSatz.getMaxRows();
			 System.out.println("Anzahl"+ anzahlSaetze );
			
			return anzahlSaetze;
		}
		
		/**
		 * gibt eine Tabelle zurück, die anzeigt wie die Saetze zu einem Spiel ausgegangen sind 
		 * @param spielId
		 * @return gewonnen ; bsp. // gewonnen[0] => Satz 1 usw. ; "gewonnen" = wir gewonnen ; "offen" = Unentschieden; "verloren" = Gegner gewonnen
		 * @throws SQLException
		 */
		public String[] getGewonneneSaetze(int spielId) throws SQLException{
			
			PreparedStatement gewonneneSaetze = conn.prepareStatement("SELECT gewonnen FROM satz WHERE spiel_id = ?");
			gewonneneSaetze.setInt(1 , spielId);
			ResultSet rsGewSa = gewonneneSaetze.executeQuery();
			
			int i = 0; 
			int j =1; 
			
			String[] gewonnen = new String[2];
			if(rsGewSa.next()){
				gewonnen[i] = rsGewSa.getString(j);
				i= i+1;  
				j = j+1;
			}// endif
			
			return gewonnen;

		}
		
		/**
		 * aktualisiert den Eintrag Satz in der Datenbank nach Beendigung des Satzes 
		 * @param satzId
		 * @param gewonnen
		 * @throws SQLException
		 */
		public void satzende(int satzId , String gewonnen ) throws SQLException{
			int pktSpiel ; 
		    int anzahlSaetze ;
		     
		    PreparedStatement stSatzende = Database.conn.prepareStatement("UPDATE satz SET gewonnen = ? WHERE id = ?");
		    stSatzende.setString(1, gewonnen);
		    stSatzende.setInt(2, satzId);
		    
		    stSatzende.executeUpdate();
		  
			
		}
		
		public void getSaetze(int spielId) throws SQLException{
			
			PreparedStatement st = conn.prepareStatement("SELECT * FROM saetze WHERE spiel_ID = ?");
			st.setInt(1, spielId);
		}
		
		
		/**
		 * erstellt einen neuen Zug in der Datenbank
		 * @param satzId
		 * @param gegner
		 * @param spalte
		 * @param zeile
		 * @throws SQLException
		 */
		public void Zug(int satzId, boolean gegner, int spalte,int zeile) throws SQLException{
		
			PreparedStatement stZug = conn.prepareStatement("INSERT INTO zug(satz_id, spalte, zeile, gegner) VALUES( ? , ? , ? , ?)");
			stZug.setInt(1 ,satzId);
			stZug.setInt(2 , spalte);
			stZug.setInt(3 , zeile);
			stZug.setBoolean(4, gegner );
			
			stZug.execute();
			stZug.close();
			
		}
		
		public void getZuege(int satzId) throws SQLException{
			
			PreparedStatement st = conn.prepareStatement("SELECT spalte, zeile, gegner FROM zug WHERE satz_id = ?");
			st.setInt(1, satzId);
			ResultSet rs = st.executeQuery();
			
		}
		
		
	
}