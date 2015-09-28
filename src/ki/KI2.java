package ki;



public class KI2 {

	int ergebnis = 99;
	int zeile;
	int spalte;
	
	public int [][] feld = new int [6][7];
	
	
	
	//Konstruktor
	public KI2(Spielfeld feld){
	
		for (spalte = 0; spalte <= 6; spalte++){
			for (zeile = 0; zeile <= 5; zeile++){
				
				 this.feld[zeile][spalte] = feld.feld[zeile][spalte] ;
				
			}
		}
		
		
	}// end Konstruktor
	
	public int gib_spalte(){
		
		return ergebnis;
		
	}
	
	public void berechne(){
		while (true){
			for (spalte = 0; spalte < 7; spalte++){
				for (zeile = 0; zeile < 6; zeile++){
				
					try{
						// 3 vertikal
						if(feld[zeile][spalte] == 0 && feld[zeile+1][spalte] == 1 && feld[zeile+2][spalte] == 1 && feld[zeile+3][spalte] == 1){
							ergebnis = spalte;
							
						}
						
						
						// 3 horizontal
						if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte-2] == 1 && feld[zeile][spalte-3] == 1){ //0111 links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile][spalte+3] == 1){ //0111 rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1){ //1011 rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile][spalte-2] == 1 && feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1){ //1101 rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						}
						
						
						
						// 3 diagonal
						if(feld[zeile-1][spalte-1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+1][spalte+1] == 1){ //1011 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+1][spalte-1] == 1){ //1011 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1){ //0111 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile+1][spalte+1] == 1){ //1101 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile+1][spalte-1] == 1){ //1101 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile+1][spalte-1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+3][spalte-3] == 1){ // 0111 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-3][spalte+3] == 1){ //0111 oben rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile-3][spalte-3] == 1){ // 0111 oben links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						}
					
					
					} catch (ArrayIndexOutOfBoundsException e){}
				
				} // end zeile
			
			} //end spalte
			
			if(ergebnis != 99){
				break;
			}//valides Ergebnis?
			
			///Gegner
			
			for (spalte = 0; spalte < 7; spalte++){
				for (zeile = 0; zeile < 6; zeile++){
				
					try{
						// 3 vertikal
						if(feld[zeile][spalte] == 0 && feld[zeile+1][spalte] == 2 && feld[zeile+2][spalte] == 2 && feld[zeile+3][spalte] == 2){
							ergebnis = spalte;
							
						}
						
						
						// 3 horizontal
						if(feld[zeile][spalte-1] == 2 && feld[zeile][spalte-2] == 2 && feld[zeile][spalte-3] == 2){ //0111 links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile][spalte+1] == 2 && feld[zeile][spalte+2] == 2 && feld[zeile][spalte+3] == 2){ //0111 rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile][spalte-1] == 2 && feld[zeile][spalte+1] == 2 && feld[zeile][spalte+2] == 2){ //1011 rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile][spalte-2] == 2 && feld[zeile][spalte-1] == 2 && feld[zeile][spalte+1] == 2){ //1101 rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						}
						
						
						
						// 3 diagonal
						if(feld[zeile-1][spalte-1] == 2 && feld[zeile+2][spalte+2] == 2 && feld[zeile+1][spalte+1] == 2){ //1011 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile-1][spalte+1] == 2 && feld[zeile+2][spalte-2] == 2 && feld[zeile+1][spalte-1] == 2){ //1011 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile+1][spalte+1] == 2 && feld[zeile+2][spalte+2] == 2 && feld[zeile+3][spalte+3] == 2){ //0111 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile-1][spalte-1] == 2 && feld[zeile-2][spalte-2] == 2 && feld[zeile+1][spalte+1] == 2){ //1101 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile-1][spalte+1] == 2 && feld[zeile-2][spalte+2] == 2 && feld[zeile+1][spalte-1] == 2){ //1101 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile+1][spalte-1] == 2 && feld[zeile+2][spalte-2] == 2 && feld[zeile+3][spalte-3] == 2){ // 0111 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile-1][spalte+1] == 2 && feld[zeile-2][spalte+2] == 2 && feld[zeile-3][spalte+3] == 2){ //0111 oben rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						} else if(feld[zeile-1][spalte-1] == 2 && feld[zeile-2][spalte-2] == 2 && feld[zeile-3][spalte-3] == 2){ // 0111 oben links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
							}
							ergebnis = spalte;
							
						}
					
					
					} catch (ArrayIndexOutOfBoundsException e){}
				
									
				} // end zeile
			
			} //end spalte
			
			
			if(ergebnis != 99){
				break;
			}//valides Ergebnis?
			
			System.out.println("ergebnis: " + ergebnis);
			
			ergebnis = (int)(Math.random()*7);
			break;
		
		} //end while
	} // end berechne
	
	
} // end class
