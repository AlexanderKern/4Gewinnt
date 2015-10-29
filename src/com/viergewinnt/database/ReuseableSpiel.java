package com.viergewinnt.database;


public class ReuseableSpiel {
	
	private static String name;
	public static int id;
	public static boolean farbe;
	
	public void setName(String name){
		ReuseableSpiel.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setId( int id){
		ReuseableSpiel.id = id; 
	}
	
	public int getId(){
		return id;
	}
	
	public void setFarbe(boolean farbe){
		this.farbe = farbe;
	}
	
	public boolean getFarbe(){
		return this.farbe;
	}

}
