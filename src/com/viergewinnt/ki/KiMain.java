package com.viergewinnt.ki;

public class KiMain {
	
	/**
	 * Die Klasse KiMain berechenet den Spielzug der kuenstlichen Intellegenz Claire
	 * @author Bjoern Korioth
	 */

	int ergebnis = 99;
	int zeile;
	int spalte;	
	private int [][] feld = new int [6][7];	
	private int spalte_rueckgabe;
	private int [] letzter_zug = new int [2];
	private int akt_zeile [] = new int [7];
	
	// Methodenbeginn
	
	/**
	 * Konstrucktor
	 */
	public KiMain(){
		
		//Feld einstellen
		for (spalte = 0; spalte < 7; spalte++){
			akt_zeile[spalte] = 5;
			for (zeile = 0; zeile < 6; zeile++){
				feld[zeile][spalte]= 0;
			}			
		}				
	}// end Konstruktor
	
	
	// Setter (feld: 0=unbelegt,1=spieler,2=gegner)
	/**
	 * Setzt den Stein in eine Spalte  (feld: 0=unbelegt,1=spieler,2=gegner)
	 * @param spalte Spalte 
	 */
		public void setGegnerStein(int spalte) {
			
			spalte_rueckgabe = spalte;
			feld [akt_zeile[spalte]][spalte_rueckgabe] = 2;
			letzter_zug[1] = spalte_rueckgabe;
			letzter_zug[0] = akt_zeile[spalte];
			akt_zeile[spalte]--;
		}// end setter
		
		/**
		 * 
		 * @param spalte Spalte 
		 */
		public void setEigenerStein(int spalte) {

			spalte_rueckgabe = spalte;
			if(akt_zeile[spalte] > 0){
				feld [akt_zeile[spalte]][spalte_rueckgabe] = 1; //TODO: Array Index out of bound
				letzter_zug[1] = spalte_rueckgabe;
				letzter_zug[0] = akt_zeile[spalte];
				akt_zeile[spalte]--;
			} else {
				for(spalte = 0; spalte < 7; spalte++){
					if(akt_zeile[spalte] > 0){
						feld [akt_zeile[spalte]][spalte] = 1; //TODO: Array Index out of bound
						letzter_zug[1] = spalte;
						letzter_zug[0] = akt_zeile[spalte];
						akt_zeile[spalte]--;
						break;
					}
				}
			}
		}// end setter

		/**
		 * 
		 * @return ergebnis 
		 */
	public int get_spalte(){		
		return ergebnis;	
	}// end get spalte
	
	/**
	 * gibt den letzen Zug zurueck
	 * @return letzer Zug
	 */
	public int[] getletzter_zug(){	
		return letzter_zug;
	}// get letzter zug
	
	/**
	 * Berechnet die zu setzende Spalte.
	 * 
	 * Der Parameter ergebnis ist standardmaessig zuerst auf 99 gesetzt, um so ueberpruefen zu koennen ob noch kein sinnvoller Zug berechnet worden ist.
	 * Wird ein sinnvolles Ergebnis gefunden, wird die Berechnungsschleife abgebrochen, ansonsten wird eine Zufallszahl bestimmt.
	 * Die Schleife geht von links nach rechts die Spalten durch und berechnet vom aktuell obersten Feld der Spalte aus.
	 * 
	 * Das Muster welches gesucht wird ist: 3 in einer Reihe.
	 * 
	 * suche zuerst beim Spieler(Wert = 1) und falls kein Ergebnis vorliegt, dann bei Gegner(Wert = 2)
	 */
	public void berechne(){
		ergebnis = 99;
		while (true){
			for (spalte = 0; spalte < 7; spalte++){
				zeile = akt_zeile[spalte];
				
					try{
						// 3 vertikal
						if(feld[zeile+1][spalte] == 1 && feld[zeile+2][spalte] == 1 && feld[zeile+3][spalte] == 1){
							ergebnis = spalte;	
							break;
						}
					} catch (ArrayIndexOutOfBoundsException e){}
						
						// 3 horizontal
					try{
						if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte-2] == 1 && feld[zeile][spalte-3] == 1){ //0111 links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile][spalte+3] == 1){ //0111 rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}	
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1){ //1011 rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile][spalte-2] == 1 && feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1){ //1101 links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
												
						// 3 diagonal
					try{
						if(feld[zeile-1][spalte-1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+1][spalte+1] == 1){ //1011 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile-1][spalte+1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+1][spalte-1] == 1){ //1011 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1){ //0111 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile+1][spalte+1] == 1){ //1101 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
						
					try{
						if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile+1][spalte-1] == 1){ //1101 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
						
					try{
						if(feld[zeile+1][spalte-1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+3][spalte-3] == 1){ // 0111 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-3][spalte+3] == 1){ //0111 oben rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile-3][spalte-3] == 1){ // 0111 oben links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}			
					} catch (ArrayIndexOutOfBoundsException e){}				
			} //end spalte
			
			if(ergebnis != 99){
				break;
			}//valides Ergebnis? Sonst wird der Gegner �berpr�ft
		
			///Gegner
			
			for (spalte = 0; spalte < 7; spalte++){
				
				zeile = akt_zeile[spalte];
				
					try{
						// 3 vertikal
						if(feld[zeile][spalte] == 0 && feld[zeile+1][spalte] == 2 && feld[zeile+2][spalte] == 2 && feld[zeile+3][spalte] == 2){
							ergebnis = spalte;
							break;
						}
					} catch (ArrayIndexOutOfBoundsException e){}		
						
						// 3 horizontal
					try{
						if(feld[zeile][spalte-1] == 2 && feld[zeile][spalte-2] == 2 && feld[zeile][spalte-3] == 2){ //0111 links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile][spalte+1] == 2 && feld[zeile][spalte+2] == 2 && feld[zeile][spalte+3] == 2){ //0111 rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
						
					try{
						if(feld[zeile][spalte-1] == 2 && feld[zeile][spalte+1] == 2 && feld[zeile][spalte+2] == 2){ //1011 rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
						
					try{
						if(feld[zeile][spalte-2] == 2 && feld[zeile][spalte-1] == 2 && feld[zeile][spalte+1] == 2){ //1101 links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}						
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
											
						// 3 diagonal
					try{
						if(feld[zeile-1][spalte-1] == 2 && feld[zeile+2][spalte+2] == 2 && feld[zeile+1][spalte+1] == 2){ //1011 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile-1][spalte+1] == 2 && feld[zeile+2][spalte-2] == 2 && feld[zeile+1][spalte-1] == 2){ //1011 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}		
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
						
					try{
						if(feld[zeile+1][spalte+1] == 2 && feld[zeile+2][spalte+2] == 2 && feld[zeile+3][spalte+3] == 2){ //0111 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile-1][spalte-1] == 2 && feld[zeile-2][spalte-2] == 2 && feld[zeile+1][spalte+1] == 2){ //1101 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
						
					try{
						if(feld[zeile-1][spalte+1] == 2 && feld[zeile-2][spalte+2] == 2 && feld[zeile+1][spalte-1] == 2){ //1101 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile+1][spalte-1] == 2 && feld[zeile+2][spalte-2] == 2 && feld[zeile+3][spalte-3] == 2){ // 0111 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
						
					try{
						if(feld[zeile-1][spalte+1] == 2 && feld[zeile-2][spalte+2] == 2 && feld[zeile-3][spalte+3] == 2){ //0111 oben rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
						
					try{
						if(feld[zeile-1][spalte-1] == 2 && feld[zeile-2][spalte-2] == 2 && feld[zeile-3][spalte-3] == 2){ // 0111 oben links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								break;
							}							
						}										
					} catch (ArrayIndexOutOfBoundsException e){}									
			} //end spalte
			
			if(ergebnis == 99){				
				ergebnis = (int)(Math.random()*7);
				break;
			}//valides Ergebnis? Sonst Zufall			
		} //end while
	} // end berechne		
} // end class