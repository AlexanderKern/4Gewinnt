package com.viergewinnt.database;

/**
 * Die Klasse ReusableZuege ermoeglicht die Attribute des Zugs in einem Objekt der Klasse zu speichern 
 * und aus allen Klassen des Programms darauf zuzugreifen
 * @author MajkenPlugge
 *
 */

public class ReusableZuege {
	
	public static int spalte;
	public static int zeile;
	public static boolean gegner;

	/**
	 * Setzt das Attribut Spalte
	 * @param spalte Spalte
	 */
	public void setSpalte( int spalte){
		ReusableZuege.spalte = spalte;
	}
	
	/**
	 * Gibt das Attribut Spalte zurueck
	 * @return Spalte des aktuellen Zugs
	 */
	public int getSpalte(){
		return ReusableZuege.spalte;
	}
	
	/**
	 * Setzt das Attribut Zeile
	 * @param zeile Zeile
	 */
	public void setZeile( int zeile){
		ReusableZuege.zeile = zeile;
	}
	
	/**
	 * Gibt das Attribut Zeile des aktuellen Zugs zurueck
	 * @return Zeile
	 */
	public int getZeile(){
		return ReusableZuege.zeile;
	}
	
	/**
	 * Setzt das Attribut Gegner auf true oder false
	 * und gibt an, ob der Zug vom Gegner oder der eigen KI gespielt wurden ist 
	 * @param gegner Gegner
	 */
	public void setGegner( boolean gegner){
		ReusableZuege.gegner = gegner; 
	}
	
	/**
	 * Gibt das Attribut Gegner zurueck
	 * @return Gegner
	 */
	public boolean getGegner(){
		return ReusableZuege.gegner;
	}
}
