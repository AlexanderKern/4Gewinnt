package com.viergewinnt.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import com.viergewinnt.api.common.util.ReuseServermethode;

/**
 * Die Klasse verwaltet die Datenbank von der Erstellung ueber den
 * Verbindungsaufbau bis hin aller Zugriffe auf die Datenbank
 * 
 * @author MajkenPlugge
 *
 */
public class Database {

	public static Connection conn; // Verbindung zur Datenbank besteht zur
									// gesamten Laufzeit des Programms

	/**
	 * Der Konstruktor stellt die Verbindung zur Datenbank her
	 */
	public Database() {
		try {
			Class.forName("org.hsqldb.jdbcDriver"); // Load the HSQL Database
													// Engine JDBC driver
			conn = DriverManager.getConnection( // Connect to the database, load
												// the db files and start
												// database if it isn't already
												// running
					"jdbc:hsqldb:MyDatabase", // filenames
					"sa", // username
					""); // password
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Erstellt die notwendigen Tabellen(Spieler, Spiel, Satz und Zug)
	 * 
	 * @param db
	 *            Datenbankverbindung
	 * 
	 * @throws SQLException
	 */
	public void createTable(Database db) throws SQLException {

		String person_table = ("CREATE TABLE spieler ( id INTEGER IDENTITY PRIMARY KEY,  name VARCHAR(256) UNIQUE )");
		db.update(person_table);
		String spiel_table = ("CREATE TABLE spiel ( id INTEGER IDENTITY PRIMARY KEY, punkte VARCHAR(256) , gegner VARCHAR(256), date DATE, farbe BOOLEAN )");
		db.update(spiel_table);
		String satz_table = ("CREATE TABLE satz ( id INTEGER IDENTITY PRIMARY KEY, spiel_id INTEGER , FOREIGN KEY (spiel_id) REFERENCES spiel(id) , gewonnen VARCHAR(256)) ");
		db.update(satz_table);
		String zug_table = ("CREATE TABLE zug (id INTEGER IDENTITY PRIMARY KEY , satz_id INTEGER,  spalte Integer, zeile Integer, gegner BOOLEAN )");
		db.update(zug_table);
	}

	/**
	 * loescht die Datenbank Tabellen und Eintraege
	 * 
	 * @param db Datenbankverbindung
	 * @throws SQLException  Datenbankfehler
	 */
	public void deleteTable(Database db) throws SQLException {
		db.update("DROP table person");
		db.update("DROP table spiel");
		db.update("DROP table satz");
		db.update("DROP table zug");
	}

	/**
	 * fuehrt die SQL-Statements CREATE, DROP, INSERT und UPDATE aus
	 * 
	 * @param sql_command
	 *            SQL-Befehl
	 * @throws SQLException
	 */
	public synchronized void update(String sql_command) throws SQLException {
		Statement stmt = null;
		stmt = conn.createStatement();
		stmt.executeUpdate(sql_command);
		stmt.close();
	}

	/**
	 * fuehrt die SQL-Abfragen aus und gibt ein das Ergebnis in Form eines
	 * ResultSets zurück
	 * 
	 * @param stmnt
	 *            SQL-Abfrage
	 * @return Result Set Ergebnis der SQL-Abfrage
	 * @throws SQLException
	 */
	public ResultSet doQueryPrepStmnt(PreparedStatement stmnt) throws SQLException {
		ResultSet rs = stmnt.executeQuery();
		return rs;
	}

	/**
	 * faehrt die Datenbankverbindung ordentlich herunter und schliesst die
	 * Datenbankverbindung
	 * 
	 * @throws SQLException
	 */
	public void shutdown() throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.executeQuery("SHUTDOWN");
		conn.close();
	}

	/**
	 * legt ein Spieler in der Datenbank an
	 * 
	 * @param spielerName  des gegnerischen Spielers
	 */
	public void createSpieler(String spielerName) throws SQLException {

		PreparedStatement st = conn.prepareStatement("SELECT COUNT(*) FROM spieler WHERE name = ?");
		st.setString(1, spielerName);
		ResultSet rs = st.executeQuery();
		rs.next();

		if (rs.getInt(1) == 0) { // Existiert bereits ein Spieler mit dem Namen
									// in der Datenbank?
			PreparedStatement stSpieler = conn.prepareStatement("INSERT INTO spieler(name) VALUES( ? )");
			stSpieler.setString(1, spielerName);
			stSpieler.execute();
			stSpieler.close();
		}
	}

	/**
	 * legt ein Spiel in der Datenbank an 
	 * @throws SQLException
	 */
	public void createSpiel() throws SQLException {

		int spielId;

		// Datum
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar now = new GregorianCalendar();
		String dateString = dateFormat.format(now.getTime());
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		dateString = sqlDate.toString();

		PreparedStatement stSpiel = Database.conn.prepareStatement("INSERT INTO spiel(date) VALUES(?);");
		stSpiel.setString(1, dateString);
		stSpiel.executeUpdate();

		// Get Id for actual spiel
		PreparedStatement callId = Database.conn.prepareStatement("CALL IDENTITY()");
		ResultSet rsId = callId.executeQuery();
		rsId.next();
		spielId = rsId.getInt(1);
		rsId.close();
		ReuseableSpiel.setId(spielId);
	}

	/**
	 * speichert den Gegnername zur aktuellen Spiele Id in die Datenbank
	 * 
	 * @param gegner
	 *            Gegnername
	 * @throws SQLException
	 */
	public void updateSpiel(String gegner, boolean farbe) throws SQLException {

		PreparedStatement stSpiel = Database.conn
				.prepareStatement("UPDATE spiel SET gegner = ? , farbe = ?  WHERE id = ?");
		stSpiel.setString(1, gegner);
		stSpiel.setBoolean(2, farbe);
		stSpiel.setInt(3, ReuseableSpiel.getId());
		stSpiel.executeUpdate();
		ReuseableSpiel.setName(gegner);
	}

	/**
	 * speichert den Ausgang des Spiels in der Datenbank
	 * 
	 * @param spielId
	 *            Id des Spiels
	 * @param punkte
	 *            Punkte des Spiels
	 * @throws SQLException
	 */
	public void spielEnde(int spielId, int punkte) throws SQLException {

		PreparedStatement stSpielende = conn.prepareStatement("UPDATE spiel SET punkte = ? WHERE id = ?");
		stSpielende.setInt(1, punkte);
		stSpielende.setInt(2, spielId);
		stSpielende.executeUpdate();
	}

	/**
	 * gibt erreichten Punkte eines Spiels zurueck
	 * 
	 * @param spielId Id des Spiels
	 * @return Punkte des Spiels
	 * @throws SQLException  Datenbankfehler
	 */
	public int getSpielPkt(int spielId) throws SQLException {

		int punkte = 0;
		PreparedStatement st = conn.prepareStatement("SELECT COUNT(*) FROM satz WHERE spiel_Id = ? AND gewonnen = ?");
		st.setInt(1, spielId);
		st.setString(2, "gewonnen");
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			punkte = rs.getInt(1);
		}
		return punkte;
	}

	/**
	 * gibt alle Eintraege aus der Datenbank zurueck
	 * 
	 * @return alle Spiele als ResultSet
	 * @throws SQLException 
	 */
	public ResultSet getSpiele() throws SQLException {

		PreparedStatement stGet = Database.conn.prepareStatement("SELECT * FROM spiel");
		ResultSet rs = stGet.executeQuery();
		return rs;
	}

	/**
	 *legt einen Statz in der Datenbank an
	 * 
	 * @param spielId Id des Spiels
	 * @throws SQLException
	 */
	public void createSatz(int spielId) throws SQLException {

		PreparedStatement stSatz = conn.prepareStatement("INSERT INTO satz (spiel_id) VALUES(?);");
		stSatz.setInt(1, spielId);
		stSatz.executeUpdate();
		PreparedStatement callId = conn.prepareStatement("CALL IDENTITY();");
		ResultSet rsId = callId.executeQuery();
		rsId.next();
		ReuseableSatz.setId(rsId.getInt(1));
		rsId.close();
	}

	/**
	 * speichert den Ausgang des Satzes in die Datenbank
	 * 
	 * @param gewonnen "gewonnen", "verloren", "offen"
	 * @param satzId Id des SAtzes
	 * @throws SQLException  Datenbankfehler
	 */
	public void updateSatz(String gewonnen, int satzId) throws SQLException {
		PreparedStatement stSatzende = conn.prepareStatement("UPDATE satz SET gewonnen = ? WHERE id = ?");
		stSatzende.setString(1, gewonnen);
		stSatzende.setInt(2, satzId);
		stSatzende.execute();
	}

	/**
	 * gibt die Anzahl der gespielten Saetzen zu einem Spiel zurueck
	 * 
	 * @param spielId
	 * @return anzahlSaetze
	 * @throws SQLException
	 */
	public int getAnzahlSaetze(int spielId) throws SQLException {
		int anzahlSaetze = 0;

		PreparedStatement dritterSatz = conn.prepareStatement("SELECT COUNT(*) FROM satz WHERE spiel_id = ? ");
		dritterSatz.setInt(1, spielId);
		ResultSet rs = dritterSatz.executeQuery();
		if (rs.next()) {
			anzahlSaetze = rs.getInt(1);
		}
		return anzahlSaetze;
	}

	/**
	 * gibt den Ausgang der einzelnen Saetze zurueck
	 * 
	 * @param spielId Id des Spiels
	 * @return gewonnen ; X; O ; oder "offen"
	 * @throws SQLException Datenbankfehler
	 */
	public String[] getGewonneneSaetze(int spielId) throws SQLException {

		PreparedStatement gewonneneSaetze = conn.prepareStatement("SELECT gewonnen FROM satz WHERE spiel_id = ?");
		gewonneneSaetze.setInt(1, spielId);
		ResultSet rsGewSa = gewonneneSaetze.executeQuery();
		int i = 0;
		String[] gewonnen = new String[3];
		while (rsGewSa.next()) {
			if (rsGewSa.getString(1) != null) {
				if (rsGewSa.getString(1).equals("verloren")) {
					if (ReuseServermethode.getGegnerfarbe() == false) {
						gewonnen[i] = "O";
					} else {
						gewonnen[i] = "X";
					}
				} else if (rsGewSa.getString(1).equals("gewonnen")) {
					gewonnen[i] = ReuseServermethode.getTeam();
				} else {
					gewonnen[i] = "offen";
				}
			}
			i = i + 1;
		}
		return gewonnen;

	}

	/**
	 * gibt den Gewinner eines Spiels zurueck
	 * 
	 * @return Gewinner
	 * @throws SQLException
	 */
	public String spielGewinner() throws SQLException {
		int pkt = 0;
		 String[] gewonneneSaetze= getGewonneneSaetze(ReuseableSpiel.getId());

		System.out.println("Arraylänge"+gewonneneSaetze.length);
		for (int i = 0; i < gewonneneSaetze.length; i++) {
			System.out.println("i"+i);
			if (gewonneneSaetze[i] == null) {
			} else if (gewonneneSaetze[i].equals("gewonnen")) {
				System.out.println("buhu");
				pkt = pkt + 1;
			} else if (gewonneneSaetze[i].equals("verloren")) {
				pkt = pkt - 1;
			}
		}

		if (pkt > 0) {
			return "Claire";
		} else if (pkt < 0) {
			return ReuseServermethode.getGegner();
		} else {
			return "unentschieden";
		}
	}

	/**
	 * speichert den Satzausgang in die Datenbank
	 * 
	 * @param satzId Id des Satzes
	 * @param gewonnen "gewonnen", "verloren", "offen"
	 * @throws SQLException Datenbankfehler
	 */
	public void satzende(int satzId, String gewonnen) throws SQLException {
		PreparedStatement stSatzende = Database.conn.prepareStatement("UPDATE satz SET gewonnen = ? WHERE id = ?");
		stSatzende.setString(1, gewonnen);
		stSatzende.setInt(2, satzId);
		stSatzende.executeUpdate();
	}

	/**
	 * gibt alle Saetze zu einem ausgewaehlten Spiel zurueck
	 * 
	 * @param spielId
	 *            Id des Spiels
	 * @return alle Saetze zu eines Spiels inform eines ResultSets
	 * @throws SQLException
	 */
	public ResultSet getSaetze(int spielId) throws SQLException {

		PreparedStatement st = conn.prepareStatement("SELECT * FROM satz WHERE spiel_ID = ?");
		st.setInt(1, spielId);
		ResultSet rs = st.executeQuery();
		return rs;
	}

	/**
	 * loescht einen Satz aus der Datenbank
	 * 
	 * @param satzId Id des Satzes
	 * @throws SQLException Datenbankfehler
	 */
	public void satzloeschen(int satzId) throws SQLException {
		PreparedStatement st = Database.conn.prepareStatement("DELETE FROM satz WHERE id = ?");
		st.setInt(1, satzId);
		st.executeUpdate();
	}

	/**
	 * loescht ein Spiel aus der Datenbank
	 * 
	 * @param spielId
	 *            Id des Spiels
	 * @throws SQLException
	 */
	public void spielloeschen(int spielId) throws SQLException {
		PreparedStatement st = Database.conn.prepareStatement("DELETE FROM spiel WHERE id = ?");
		st.setInt(1, spielId);
		st.executeUpdate();
	}

	/**
	 *legt einen Zug in der Datenbank an
	 * 
	 * @param satzId Id des Satzes
	 * @param gegner gibt an, ob es sich um den gegnerischen Zug oder eigenen Zug handelt
	 * @param spalte Spalte
	 * @param zeile Zeile
	 * @throws SQLException Datenbankfehler
	 */
	public void Zug(int satzId, boolean gegner, int spalte, int zeile) throws SQLException {
		PreparedStatement stZug = conn
				.prepareStatement("INSERT INTO zug(satz_id, spalte, zeile, gegner) VALUES( ? , ? , ? , ?)");
		stZug.setInt(1, satzId);
		stZug.setInt(2, spalte);
		stZug.setInt(3, zeile);
		stZug.setBoolean(4, gegner);
		stZug.execute();
		stZug.close();
	}

	/**
	 * gibt alle Zuege zu einem Satz wieder
	 * 
	 * @param satzId
	 *            Id des Satzes
	 * @return alle Zuege in Form eines ResultSets
	 * @throws SQLException Datenbankfehler
	 */
	public ResultSet getZuege(int satzId) throws SQLException {

		PreparedStatement st = conn.prepareStatement("SELECT spalte, zeile, gegner FROM zug WHERE satz_id = ?");
		st.setInt(1, satzId);
		ResultSet rs = st.executeQuery();
		return rs;
	}

	/**
	 * loescht alle Zuege eines Satz aus der Datenbank
	 * 
	 * @param satzId Id des Satzes
	 * @throws SQLException Datenbankfehler
	 */
	public void loeschenZuege(int satzId) throws SQLException {
		PreparedStatement st = conn.prepareStatement("DELETE FROM zug WHERE satz_id = ?");
		st.setInt(1, satzId);
		st.executeUpdate();
	}

}
