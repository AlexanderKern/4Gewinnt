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
			
		
		//Pruefe ob 3 eigene in einer Reihe
		for(zeile = 0; zeile < 6; zeile++){
			for(spalte = 0; spalte < 5; spalte++){				// nur bis fuenf, weil danach nicht relevant, da sonst auch spalte 5&6 voll sind
				
				switch (spalte) {
					
				case 0:
					if (zeile < 5){
						if(feld[zeile][spalte] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile][spalte+3] == 0 && feld[zeile+1][spalte+3] != 0){
							ergebnis = spalte + 3;
						}
					} else if(feld[zeile][spalte] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile][spalte+3] == 0){
						ergebnis = spalte + 3;
					}
					break;
				
				case 4:
					if (zeile < 5){
						if(feld[zeile][spalte] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile][spalte-1] == 0 && feld[zeile+1][spalte-1] != 0){
							ergebnis = spalte - 1;
						}
					} else if(feld[zeile][spalte] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile][spalte-1] == 0){
						ergebnis = spalte - 1;
					}
					break;
				
				default:
					if (zeile < 5){
						if(feld[zeile][spalte] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1){
							if(feld[zeile][spalte+3] == 0 && feld[zeile+1][spalte+3] != 0){
								ergebnis = spalte + 3;
							} else if(feld[zeile][spalte-1] == 0 && feld[zeile+1][spalte-1] != 0){
								ergebnis = spalte - 1;
							}					
						}
					} else if(feld[zeile][spalte] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile][spalte+3] == 0){
						if(feld[zeile][spalte+3] == 0){
							ergebnis = spalte + 3;
						} else if(feld[zeile][spalte-1] == 0){
							ergebnis = spalte - 1;
						}		
					}
					break;

				} // end switch
			} // end for spalte
			
		} //end for zeile
		
		
		if(ergebnis != 99){ 
			break;
		}//valides Ergebnis?
		
		
		
		//Pruefe ob drei eigene in einer Spalte
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
		
		
		//pruefe ob drei oder 2_1 oder 1_2 eigene diagonal sind
		
		
		
		//Pruefe ob 3 eigene diagonal
		
		for(spalte = 0; spalte < 7; spalte++){
			for(zeile = 0; zeile < 6; zeile++){
				switch(spalte){
				
				case 0:
					
					if (zeile < 2){
						if(feld[zeile][spalte] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 0 && feld[zeile+4][spalte+3]!= 0 ){
							ergebnis = spalte +3;
						} else if(feld[zeile][spalte] == 0 && feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1 && feld[zeile+1][spalte]!= 0){
							ergebnis = spalte;
						}
					} else if(zeile == 2){
						
						if(feld[zeile][spalte] == 1 && feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 0){
							ergebnis = spalte +3;
						} else if(feld[zeile][spalte] == 0 && feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1 && feld[zeile+1][spalte]!= 0){
							ergebnis = spalte;
						}
						
					} else if(zeile > 2){
						if(feld[zeile][spalte] == 1 && feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-3][spalte+3] == 0 && feld[zeile-2][spalte+3]!= 0 ){
							ergebnis = spalte +3;
						} else if(feld[zeile][spalte] == 0 && feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-3][spalte+3] == 1 && feld[zeile+1][spalte]!= 0){
							ergebnis = spalte;
						}
					}
					
					
				case 1:
					
					switch(zeile){
					
					case 0:
						if(feld[zeile][spalte] == 1){
							if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 0 && feld[zeile+4][spalte+3] != 0 ){
								ergebnis = spalte+3;
							}
						} else if(feld[zeile][spalte] == 0){
							if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){
								ergebnis = spalte;
							} 							
						}
					case 1:
						if(feld[zeile][spalte] == 1){
							if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 0 && feld[zeile+4][spalte+3] != 0 ){
								ergebnis = spalte+3;
							}
						} else if(feld[zeile][spalte] == 0){
							if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){
								ergebnis = spalte;
							} else if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile-1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){
								ergebnis = spalte;
							}
						}
					case 2:
						if(feld[zeile][spalte] == 1){
							if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 0 ){
								ergebnis = spalte+3;
							}
						} else if(feld[zeile][spalte] == 0){
							if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){
								ergebnis = spalte;
							} else if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile-1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){
								ergebnis = spalte;
							} else if(feld[zeile+1][spalte-1] == 1 && feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile+1][spalte] != 0 ){
								ergebnis = spalte;
							}	
						}
					case 3:
						if(feld[zeile][spalte] == 1){
							if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-3][spalte+3] == 0 && feld[zeile-2][spalte+3] != 0 ){
								ergebnis = spalte+3;
							}
						} else if(feld[zeile][spalte] == 0){//////////bearbeiten
							if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1 && feld[zeile+1][spalte] != 0 ){
								ergebnis = spalte;
							} else if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile-1][spalte-1] == 1 && feld[zeile+1][spalte] != 0 ){
								ergebnis = spalte;
							} else if(feld[zeile+1][spalte-1] == 1 && feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile+1][spalte] != 0 ){
								ergebnis = spalte;
							}	
						}
					case 4:
					case 5:
					
					}
					
					
				case 2:	
					
					
					
				case 3:
					
					
					
				case 4:
					
					
					
				case 5:	
					
					
					
				case 6:
					
					
					
				
				}//end switch spalte
			}//end for zeile
		}//end for spalte
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//________________________________________________________________________________________________________________________________________________________________________
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//Pr�fe ob 3 FREMDE in einer Reihe
		for(zeile = 0; zeile < 6; zeile++){
			for(spalte = 0; spalte < 5; spalte++){				// nur bis f�nf, weil danach nicht relevant, da sonst auch spalte 5&6 voll sind
				
				switch (spalte) {
					
				case 0:
					if (zeile < 5){
						if(feld[zeile][spalte] == 2 && feld[zeile][spalte+1] == 2 && feld[zeile][spalte+2] == 2 && feld[zeile][spalte+3] == 0 && feld[zeile+1][spalte+3] != 0){
							ergebnis = spalte + 3;
						}
					} else if(feld[zeile][spalte] == 2 && feld[zeile][spalte+1] == 2 && feld[zeile][spalte+2] == 2 && feld[zeile][spalte+3] == 0){
						ergebnis = spalte + 3;
					}
					break;
				
				case 4:
					if (zeile < 5){
						if(feld[zeile][spalte] == 2 && feld[zeile][spalte+1] == 2 && feld[zeile][spalte+2] == 2 && feld[zeile][spalte-1] == 0 && feld[zeile+1][spalte-1] != 0){
							ergebnis = spalte - 1;
						}
					} else if(feld[zeile][spalte] == 2 && feld[zeile][spalte+1] == 2 && feld[zeile][spalte+2] == 2 && feld[zeile][spalte-1] == 0){
						ergebnis = spalte - 1;
					}
					break;
				
				default:
					if (zeile < 5){
						if(feld[zeile][spalte] == 2 && feld[zeile][spalte+1] == 2 && feld[zeile][spalte+2] == 2){
							if(feld[zeile][spalte+3] == 0 && feld[zeile+1][spalte+3] != 0){
								ergebnis = spalte + 3;
							} else if(feld[zeile][spalte-1] == 0 && feld[zeile+1][spalte-1] != 0){
								ergebnis = spalte - 1;
							}					
						}
					} else if(feld[zeile][spalte] == 2 && feld[zeile][spalte+1] == 2 && feld[zeile][spalte+2] == 2 && feld[zeile][spalte+3] == 0){
						if(feld[zeile][spalte+3] == 0){
							ergebnis = spalte + 3;
						} else if(feld[zeile][spalte-1] == 0){
							ergebnis = spalte - 1;
						}		
					}
					break;

				} // end switch
			} // end for spalte
			
		} //end for zeile
		
		
		if(ergebnis != 99){
			break;
		}//valides Ergebnis?
		
		
		
		//Pr�fe ob drei FREMDE in einer Spalte
		for(spalte = 0; spalte < 7; spalte++){
			for(zeile = 5; zeile > 2; zeile--){
				
				if(feld[zeile][spalte] == 2 && feld[zeile-1][spalte] == 2 && feld[zeile-2][spalte] == 2 && feld[zeile-3][spalte] == 0){
					ergebnis = spalte;
				}
				
			}//end of Zeile
			
		}//end of Spalte
		
		if(ergebnis != 99){
			break;
		}//valides Ergebnis?
		
		}//end endlosschleife	
		
	} //end berechne

}//end class
