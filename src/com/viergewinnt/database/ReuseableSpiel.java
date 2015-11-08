package com.viergewinnt.database;

/**
 * Die Klasse ermoeglicht die Attribute des Spiels in einem Objekt zu halten und
 * im gesamten Programm waehrend der Laufzeit darauf zuzugreifen
 * 
 * @author MajkenPlugge
 *
 */

public class ReuseableSpiel {

	private static String name;
	public static int id;
	public static boolean farbe;
	public static String farbeString;


	/**
	 * Setzt den Namen des Gegners eines Spiels
	 * 
	 * @param name
	 *            Name des Gegner
	 */
	public static void setName(String name) {
		ReuseableSpiel.name = name;
	}

	/**
	 * Gibt den Namen des Gegner zurueck
	 * 
	 * @return Name des Gegner
	 */
	public static String getName() {
		return name;
	}

	/**
	 * Setzt die Id des Spiels
	 * 
	 * @param id
	 *            Id des Spiels
	 */
	public static void setId(int id) {
		ReuseableSpiel.id = id;
	}

	/**
	 * Gibt die Id des Spiels zurueck
	 * 
	 * @return Id des Spiels
	 */
	public static int getId() {
		return id;
	}

	/**
	 * Setzt die Steinfarbe der Intellegenz Claire
	 * 
	 * @param farbe
	 *            Steinfarbe der Intellegenz Claire
	 */
	public void setFarbe(boolean farbe) {
		ReuseableSpiel.farbe = farbe;
	}
	/**
	 * Gibt die Steinfarbe der Intellegenz Claire zurueck
	 * 
	 * @return Steinfarbe der Intellegenz Claire
	 */
	public boolean getFarbe() {
		return ReuseableSpiel.farbe;
	}
	
	/**
	 * Setzt die Steinfarbe der Intellegenz Claire als String
	 * @param farbeString 
	 * 
	 * @param farbe
	 *            Steinfarbe der Intellegenz Claire
	 */
	public static void setFarbeString(String farbeString) {
		ReuseableSpiel.farbeString = farbeString;
	}

	/**
	 * Gibt die Steinfarbe der Intellegenz Claire als String zurueck
	 * 
	 * @return Steinfarbe der Intellegenz Claire
	 */
	public static String getFarbeString() {
		return ReuseableSpiel.farbeString;
	}

	

}
