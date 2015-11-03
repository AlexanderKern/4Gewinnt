package com.viergewinnt.database;

/**
 * Die Klasse ReusableSpiel ermoeglicht die Attribute des Spiels in einer Instanz der Klasse zu speichern 
 * und im Laufe des Programms von allen Packeten daruaf zuzugreifen
 * @author MajkenPlugge
 *
 */

public class ReuseableSpiel {
	
	private static String name;
	public static int id;
	public static boolean farbe;
	
	/**
	 * Setzt den Namen des Gegners eines Spiels
	 * @param name Name des Gegner
	 */
	public static void setName(String name){
		ReuseableSpiel.name = name;
	}
	
	/**
	 * Gibt den Namen des Gegner zurueck
	 * @return Name des Gegner
	 */
	public static String getName(){
		return name;
	}
	
	/**
	 * Setzt die Id des Spiels
	 * @param id Id des Spiels
	 */
	public static void setId( int id){
		ReuseableSpiel.id = id; 
	}
	
	/**
	 * Gibt die Id des Spiels zurueck
	 * @return Id des Spiels
	 */
	public static int getId(){
		return id;
	}
	
	/**
	 * Setzt die Steinfarbe der Intellegenz Claire
	 * @param farbe Steinfarbe der Intellegenz Claire
	 */
	public void setFarbe(boolean farbe){
		ReuseableSpiel.farbe = farbe;
	}
	/**
	 * Gibt die Steinfarbe der Intellegenz Claire zurueck
	 * @return Steinfarbe der Intellegenz Claire
	 */
	public boolean getFarbe(){
		return ReuseableSpiel.farbe;
	}

}
