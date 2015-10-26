package com.viergewinnt.database;


public class ReusableZuege {
	
	public static int spalte;
	public static int zeile;
	public static boolean gegner;

	public void setSpalte( int spalte){
		ReusableZuege.spalte = spalte;
	}
	
	public int getSpalte(){
		return ReusableZuege.spalte;
	}
	
	public void setZeile( int zeile){
		ReusableZuege.zeile = zeile;
	}
	
	public int getZeile(){
		return ReusableZuege.zeile;
	}
	
	public void setGegner( boolean gegner){
		ReusableZuege.gegner = gegner; 
	}
	
	public boolean getGegner(){
		return ReusableZuege.gegner;
	}
}
