package com.viergewinnt.database;

/**
 * Wiederverwendung der zugewiesenen Werte zur Instanz Satz im Laufe des Programms 
 * @author MajkenPlugge
 *
 */

public class ReuseableSatz {
	
	public static int id;
	public static int spielId;

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
	
}
