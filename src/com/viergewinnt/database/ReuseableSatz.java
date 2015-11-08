package com.viergewinnt.database;

/**
 * Die Klasse ermoeglicht die Attribute des Satz in einem Objekt zu halten und
 * im gesamten Programm waehrend der Laufzeit darauf zuzugreifen
 * 
 * @author MajkenPlugge
 *
 */

public class ReuseableSatz {

	public static int id;
	public static int spielId;
	public static String gewonnen;

	/**
	 * Setzt das Attribut Id
	 * 
	 * @param id
	 *            Id des Satz
	 */
	public static void setId(int id) {
		ReuseableSatz.id = id;
	}

	/**
	 * Gibt das Attribut Id zurueck
	 * 
	 * @return Id des Satzes
	 */
	public static int getId() {
		return id;
	}

	/**
	 * Setzt die zugehoerige Spiel Id
	 * 
	 * @param spielId des zugehoerigen Spiels
	 */
	public static void setSpielId(int spielId) {
		ReuseableSatz.spielId = spielId;
	}

	/**
	 * Gibt die zugehorige Spiel Id zurueck
	 * 
	 * @return Id des zugehoerigen Spiels
	 */
	public static int getSpielId() {
		return spielId;
	}

	/**
	 * Setzt den Ausgang des Satzes im Attribut gewonnen
	 * 
	 * @param gewonnen "gewonnen", "verloren", "offen"
	 */
	public static void setGewonnen(String gewonnen) {
		ReuseableSatz.gewonnen = gewonnen;
	}

	/**
	 * Gibt das Attribut gewonnen zurueck
	 * 
	 * @return Gewonnen
	 */
	public static String getGewonnen() {
		return gewonnen;
	}

}
