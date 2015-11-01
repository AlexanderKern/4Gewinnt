package com.viergewinnt.database;

/**
 * Die Klasse ReusableSatz ermoeglicht die Attribute des Satzes in einer Instanz der Klasse zu speichern 
 * und im Laufe des Programms von allen Packeten daruaf zuzugreifen
 * @author MajkenPlugge
 *
 */

public class ReuseableSatz {
	
	public static int id;
	public static int spielId;
	public static String gewonnen;

	/**
	 * Setzt das Attribut Id
	 * @param id Id des Satz
	 */
	public static void setId( int id){
		ReuseableSatz.id = id; 
	}
	
	/**
	 * Gibt das Attribut Id zurueck
	 * @return Id des Satzes
	 */
	public static int getId(){
		return id;
	}
	
	/**
	 * Setzt die zugehoerige Spiel Id 
	 * @param Id des zugehoerigen Spiels
	 */
	public static void setSpielId( int spielId){
		ReuseableSatz.spielId = spielId; 
	}
	
	/**
	 * Gibt die zugehorige Spiel Id zurueck
	 * @return Id des zugehoerigen Spiels
	 */
	public static int getSpielId(){
		return spielId;
	}
	
	/**
	 * Setzt das Attribut gewonnen auf "gewonnen", "offen" oder "verloren"
	 * Das Attribut gewonnen gibt Auskunft über das Ergebnis des Satzes
	 * @param Gewonnen
	 */
	public static void setGewonnen( String gewonnen){
		ReuseableSatz.gewonnen = gewonnen; 
	}
	
	/**
	 * Gibt das Attribut gewonnen zurueck
	 * @return Gewonnen
	 */
	public static String getGewonnen(){
		return gewonnen;
	}
	
}
