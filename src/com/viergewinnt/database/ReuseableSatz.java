package com.viergewinnt.database;

/**
 * Wiederverwendung der zugewiesenen Werte zur Instanz Satz im Laufe des Programms 
 * @author MajkenPlugge
 *
 */

public class ReuseableSatz {
	
	public static int id;
	public static int spielId;
	public static String gewonnen;

	public void setId( int id){
		ReuseableSatz.id = id; 
	}
	
	public int getId(){
		return id;
	}
	
	public void setSpielId( int spielId){
		ReuseableSatz.spielId = spielId; 
	}
	
	public int getSpielId(){
		return spielId;
	}
	
	public void setGewonnen( String gewonnen){
		ReuseableSatz.gewonnen = gewonnen; 
	}
	
	public String getGewonnen(){
		return gewonnen;
	}
	
}
