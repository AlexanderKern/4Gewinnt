package ki;

public class KI {
	
	//Variablen
	int ergebnis = 99;
	int zeile;
	int spalte;
	private int [][] feld;
	
	
	//Konstruktor
	public KI(int[][] feld){
		
		feld = this.feld;
		
	}
	
	//Berechnungsalgorithmus
	public void berechne(){
		
		while(ergebnis == 99){ //beginn endlosschleife
			
		
		//Prüfe ob 3 eigene in einer Reihe
		for(zeile = 0; zeile < 6; zeile++){
			for(spalte = 0; spalte < 7; spalte++){				
				if(feld[zeile][spalte] == 0){
					if (zeile < 5){
						switch (spalte){
						
						case 0:
							if(feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile][spalte+3] == 1 && feld[zeile+1][spalte] != 0){ //0111 rechts
								ergebnis = spalte;
								break;
							}
						case 1:
							if(feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile][spalte+3] == 1 && feld[zeile+1][spalte] != 0){ //0111 rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile+1][spalte] != 0){ //1011 rechts
								ergebnis = spalte;
								break;
							}
						case 2:
							if(feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile][spalte+3] == 1 && feld[zeile+1][spalte] != 0){ //0111 rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile+1][spalte] != 0){ //1011 rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte-2] == 1 && feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile+1][spalte] != 0){ //1101 rechts
								ergebnis = spalte;
								break;
							}
						case 3:
							if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte-2] == 1 && feld[zeile][spalte-3] == 1 && feld[zeile+1][spalte] != 0){ //0111 links
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile][spalte+3] == 1 && feld[zeile+1][spalte] != 0){ //0111 rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile+1][spalte] != 0){ //1011 rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte-2] == 1 && feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile+1][spalte] != 0){ //1101 rechts
								ergebnis = spalte;
								break;
							}
						case 4:
							if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte-2] == 1 && feld[zeile][spalte-3] == 1 && feld[zeile+1][spalte] != 0){ //0111 links
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile+1][spalte] != 0){ //1011 rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte-2] == 1 && feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile+1][spalte] != 0){ //1101 rechts
								ergebnis = spalte;
								break;
							}
						case 5:
							if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte-2] == 1 && feld[zeile][spalte-3] == 1 && feld[zeile+1][spalte] != 0){ //0111 links
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte+1] == 1 && feld[zeile][spalte-1] == 1 && feld[zeile][spalte-2] == 1 && feld[zeile+1][spalte] != 0){ //1011 links
								ergebnis = spalte;
								break;
							}
						case 6:
							if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte-2] == 1 && feld[zeile][spalte-3] == 1 && feld[zeile+1][spalte] != 0){ //0111 links
								ergebnis = spalte;
								break;
							}
					
					
						} //end switch spalte
					} else {
						switch (spalte){
						
						case 0:
							if(feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile][spalte+3] == 1){ //0111 rechts
								ergebnis = spalte;
								break;
							}
						case 1:
							if(feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile][spalte+3] == 1){ //0111 rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1){ //1011 rechts
								ergebnis = spalte;
								break;
							}
						case 2:
							if(feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile][spalte+3] == 1){ //0111 rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1){ //1011 rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte-2] == 1 && feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1){ //1101 rechts
								ergebnis = spalte;
								break;
							}
						case 3:
							if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte-2] == 1 && feld[zeile][spalte-3] == 1){ //0111 links
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile][spalte+3] == 1){ //0111 rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1){ //1011 rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte-2] == 1 && feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1){ //1101 rechts
								ergebnis = spalte;
								break;
							}
						case 4:
							if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte-2] == 1 && feld[zeile][spalte-3] == 1){ //0111 links
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1){ //1011 rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte-2] == 1 && feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1){ //1101 rechts
								ergebnis = spalte;
								break;
							}
						case 5:
							if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte-2] == 1 && feld[zeile][spalte-3] == 1){ //0111 links
								ergebnis = spalte;
								break;
							} else if(feld[zeile][spalte+1] == 1 && feld[zeile][spalte-1] == 1 && feld[zeile][spalte-2] == 1){ //1011 links
								ergebnis = spalte;
								break;
							}
						case 6:
							if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte-2] == 1 && feld[zeile][spalte-3] == 1){ //0111 links
								ergebnis = spalte;
								break;
							}
					
					
						} //end switch spalte
						
						
					} // end if zeile < 5
				
				}// end if 0

			} // end for spalte
			
		} //end for zeile
		
		
		if(ergebnis != 99){
			break;
		}//valides Ergebnis?
		
		
		
		//Prüfe ob drei eigene in einer Spalte
		for(spalte = 0; spalte < 7; spalte++){
			for(zeile = 5; zeile > 2; zeile--){
				
				if(feld[zeile][spalte] == 1 && feld[zeile-1][spalte] == 1 && feld[zeile-2][spalte] == 1 && feld[zeile-3][spalte] == 0){
					ergebnis = spalte;
				}
				
			}//end of Zeile
			
		}//end of Spalte
		
		if(ergebnis != 99){
			break;
		}//valides Ergebnis?
		
		
		//prüfe ob drei oder 2_1 oder 1_2 eigene diagonal sind
		
		for(spalte = 0; spalte < 7; spalte++){
			for(zeile = 0; zeile < 6; zeile++){
				if(feld[zeile][spalte] == 0){
				
				switch(spalte){
				
				case 0:
					
					if (zeile < 3){
						if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1 && feld[zeile+1][spalte]!= 0){
							ergebnis = spalte;
							break;
						}
					} else if(zeile > 5){
						if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-3][spalte+3] == 1 && feld[zeile+1][spalte]!= 0){
							ergebnis = spalte;
							break;
						} 
					}else if (zeile == 5){
							if(feld[zeile+1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-3][spalte+3] == 1 ){
								ergebnis = spalte;
								break;
							}
					}
					
					
				case 1:
					
					switch(zeile){
					
					case 0:
						
							if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){ // 0111 unten rechts
								ergebnis = spalte;
								break;
							} 							
						
					case 1:
						
							if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){ // 0111 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile-1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ // 1011 unten rechts
								ergebnis = spalte;
								break;
							}
						
					case 2:
						
							if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){ // 0111 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte-1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011schräg unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile+1][spalte-1] == 1 && feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile+1][spalte] != 0 ){ //1011schräg oben rechts
								ergebnis = spalte;
								break;
							}	
						
					case 3:
						
							if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){ //0111 oben rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte-1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011schräg unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile+1][spalte-1] == 1 && feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile+1][spalte] != 0 ){ //1011schräg oben recht
								ergebnis = spalte;
								break;
							}	
						
					case 4:
						
							if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){ //0111 oben rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile+1][spalte-1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 oben rechts
								ergebnis = spalte;
								break;
							}
						
					case 5:
						
							if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-3][spalte+3] == 1 ){ //0111oben rechts
								ergebnis = spalte;
								break;
							} 							
						
					} // end zeile
					
					
				case 2:	
					
					switch (zeile){
					
					case 0:
						
							if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){ // 0111 unten rechts
								ergebnis = spalte;
								break;
							} 							
						
					case 1:
						
							if(feld[zeile-1][spalte-1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten links
								ergebnis = spalte;
								break;
							} else if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){ //0111 unten rechts
								ergebnis = spalte;
								break;
							}
						
						
						
						
					case 2:
						
							if(feld[zeile-1][spalte-1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten links
								ergebnis = spalte;
								break;
							} else if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){ //0111 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten links
								ergebnis = spalte;
								break;
							}
						
						
						
					case 3:
						
							if(feld[zeile-1][spalte-1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten links
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){ //0111 oben rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten links
								ergebnis = spalte;
								break;
							}
						
						
						
					case 4:
						
							if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){ //0111 oben rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten links
								ergebnis = spalte;
								break;
							}
						
						
						
					case 5:
						
							if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-3][spalte+3] == 1 ){ //0111oben rechts
								ergebnis = spalte;
								break;
							} 							
						
					
					} // end zeile
					
					
					
				case 3:
					switch(zeile) {
					
					case 0:
						
							if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){ // 0111 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile+1][spalte-1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+3][spalte-3] == 1 && feld[zeile+1][spalte] != 0 ){ // 0111 unten links
								ergebnis = spalte;
								break;
							}						
						
						
						
					case 1:
						
							if(feld[zeile-1][spalte-1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten links
								ergebnis = spalte;
								break;
							} else if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){ //0111 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile+1][spalte-1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+3][spalte-3] == 1 && feld[zeile+1][spalte] != 0 ){ // 0111 unten links
								ergebnis = spalte;
								break;
							}	
						
						
						
					case 2:
						
							if(feld[zeile-1][spalte-1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten links
								ergebnis = spalte;
								break;
							} else if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){ //0111 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten links
								ergebnis = spalte;
								break;
							} else if(feld[zeile+1][spalte-1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+3][spalte-3] == 1 && feld[zeile+1][spalte] != 0 ){ // 0111 unten links
								ergebnis = spalte;
								break;
							}	
						
						
						
					case 3:
						
							if(feld[zeile-1][spalte-1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten links
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){ //0111 oben rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten links
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile-3][spalte-3] == 1 && feld[zeile+1][spalte] != 0 ){ // 0111 oben links
								ergebnis = spalte;
								break;
							}	
						
						
						
					case 4:
						
							if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){ //0111 oben rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten links
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile-3][spalte-3] == 1 && feld[zeile+1][spalte] != 0 ){ // 0111 oben links
								ergebnis = spalte;
								break;
							}	
						
						
						
					case 5:
						
							if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-3][spalte+3] == 1 ){ // 0111 oben rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile-3][spalte-3] == 1 ){ // 0111 oben links
								ergebnis = spalte;
								break;
							}						
						
					
					} // end switch zeile
										
					
				case 4:
					switch (zeile){
					
					case 0:
						
							if(feld[zeile+1][spalte-1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+3][spalte-3] == 1 && feld[zeile+1][spalte] != 0 ){ // 0111 unten links
								ergebnis = spalte;
								break;
							} 							
						
					case 1:
						
							if(feld[zeile-1][spalte-1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten links
								ergebnis = spalte;
								break;
							} else if(feld[zeile+1][spalte-1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+3][spalte-3] == 1 && feld[zeile+1][spalte] != 0 ){ //0111 unten links
								ergebnis = spalte;
								break;
							}
						
						
						
						
					case 2:
						
							if(feld[zeile-1][spalte-1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten links
								ergebnis = spalte;
								break;
							} else if(feld[zeile+1][spalte-1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+3][spalte-3] == 1 && feld[zeile+1][spalte] != 0 ){ //0111 unten links
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten links
								ergebnis = spalte;
								break;
							}
						
						
						
					case 3:
						
							if(feld[zeile-1][spalte-1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 unten links
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile-3][spalte-3] == 1 && feld[zeile+1][spalte] != 0 ){ //0111 oben links
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten links
								ergebnis = spalte;
								break;
							}
						
						
						
					case 4:
						
							if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile-3][spalte-3] == 1 && feld[zeile+1][spalte] != 0 ){ //0111 oben links
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten rechts
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1101 unten links
								ergebnis = spalte;
								break;
							}
						
						
						
					case 5:
						
							if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile-3][spalte-3] == 1 ){ //0111oben links
								ergebnis = spalte;
								break;
							} 							
						
					
					} // end zeile		
					
					
				case 5:	
					switch(zeile){
					
					case 0:
						
							if(feld[zeile+1][spalte-1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+3][spalte-3] == 1 && feld[zeile+1][spalte] != 0 ){ // 0111 unten links
								ergebnis = spalte;
								break;
							} 							
						
					case 1:
						
							if(feld[zeile+1][spalte-1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+3][spalte-3] == 1 && feld[zeile+1][spalte] != 0 ){ // 0111 unten links
								ergebnis = spalte;
								break;
							} else if(feld[zeile+1][spalte-1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile-1][spalte+1] == 1 && feld[zeile+1][spalte] != 0 ){ // 1011 unten links
								ergebnis = spalte;
								break;
							}
						
					case 2:
						
							if(feld[zeile+1][spalte-1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+3][spalte-3] == 1 && feld[zeile+1][spalte] != 0 ){ // 0111 unten links
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011schräg unten links
								ergebnis = spalte;
								break;
							} else if(feld[zeile+1][spalte+1] == 1 && feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile+1][spalte] != 0 ){ //1011schräg oben links
								ergebnis = spalte;
								break;
							}	
						
					case 3:
						
							if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile-3][spalte-3] == 1 && feld[zeile+1][spalte] != 0 ){ //0111 oben links
								ergebnis = spalte;
								break;
							} else if(feld[zeile-1][spalte+1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011schräg unten links
								ergebnis = spalte;
								break;
							} else if(feld[zeile+1][spalte+1] == 1 && feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile+1][spalte] != 0 ){ //1011schräg oben links
								ergebnis = spalte;
								break;
							}	
						
					case 4:
						
							if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile-3][spalte-3] == 1 && feld[zeile+1][spalte] != 0 ){ //0111 oben links
								ergebnis = spalte;
								break;
							} else if(feld[zeile+1][spalte+1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile-1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){ //1011 oben links
								ergebnis = spalte;
								break;
							}
						
					case 5:
						
							if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile-3][spalte-3] == 1 ){ //0111oben links
								ergebnis = spalte;
								break;
							} 							
						
					} // end zeile
					
					
				case 6:
					if (zeile < 3){
						if(feld[zeile+1][spalte-1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+3][spalte-3] == 1 && feld[zeile+1][spalte]!= 0){
							ergebnis = spalte;
							break;
						}
					} else if(zeile > 5){
						if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile-3][spalte-3] == 1 && feld[zeile+1][spalte]!= 0){
							ergebnis = spalte;
							break;
						} 
					}else if (zeile == 5){
							if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile-3][spalte-3] == 1 ){
								ergebnis = spalte;
								break;
							}
					}
				
					
					
					
				
				}//end switch spalte
				}//end if 0
			}//end for zeile
		}//end for spalte
		
		if(ergebnis != 99){
			break;
		}//valides Ergebnis?
		
		
		
		
		
		
		
		
		
		
		}//end endlosschleife	
		
	} //end berechne

}//end class
