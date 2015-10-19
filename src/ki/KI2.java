package ki;

public class KI2 {

	int ergebnis = 99;
	int zeile;
	int spalte;	
	private int [][] feld = new int [6][7];	
	private int spalte_rueckgabe;
	private int [] letzter_zug = new int [2];	
	
	// Methodenbeginn
	
	//Konstruktor
	public KI2(){
		
		//Feld einstellen
		for (spalte = 0; spalte < 7; spalte++){
			for (zeile = 0; zeile < 6; zeile++){
				feld[zeile][spalte]= 0;
			}			
		}				
	}// end Konstruktor
	
	// Setter (feld: 0=unbelegt,1=spieler,2=gegner)
		public void setGegnerStein(int spalte) {
			
			spalte_rueckgabe = spalte;
			
				for (int zeile = 5; zeile >= 0; zeile--){
					if ( feld [zeile][spalte_rueckgabe] == 0){	
						feld [zeile][spalte_rueckgabe] = 2;
						letzter_zug[1] = spalte_rueckgabe;
						letzter_zug[0] = zeile;
						break;	
					}//endif
				}//endfor	
		}// end setter
		
		public void setEigenerStein(int spalte) {
			
			spalte_rueckgabe = spalte;
			
				for (int zeile = 5; zeile >= 0; zeile--){
					if ( feld [zeile][spalte_rueckgabe] == 0){	
						feld [zeile][spalte_rueckgabe] = 1;
						letzter_zug[1] = spalte_rueckgabe;
						letzter_zug[0] = zeile;
						break;	
					}//endif
				}//endfor	
		}// end setter

		
	public int get_spalte(){
		
		return ergebnis;
		
	}// end get spalte
	
	public int[] getletzter_zug(){	
		return letzter_zug;
	}// get letzter zug
	
	public void berechne(){
		ergebnis = 99;
		while (true){
			for (spalte = 0; spalte < 7; spalte++){
				for (zeile = 0; zeile < 6; zeile++){
					
					if(feld[zeile][spalte] == 0){
				
					try{
						// 3 vertikal
						if(feld[zeile+1][spalte] == 1 && feld[zeile+2][spalte] == 1 && feld[zeile+3][spalte] == 1){
							ergebnis = spalte;	
							System.out.println(ergebnis + " Muster vertikal");
							break;
						}
					} catch (ArrayIndexOutOfBoundsException e){}
						
						// 3 horizontal
					try{
						if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte-2] == 1 && feld[zeile][spalte-3] == 1){ //0111 links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 links horizontal");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis +  "Muster 0111 links horizontal");
								break;
							}
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1 && feld[zeile][spalte+3] == 1){ //0111 rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 rechts horizontal");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 rechts horizontal");
								break;
							}	
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1 && feld[zeile][spalte+2] == 1){ //1011 rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1011 rechts horizontal");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1011 rechts horizontal");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile][spalte-2] == 1 && feld[zeile][spalte-1] == 1 && feld[zeile][spalte+1] == 1){ //1101 links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1011 links horizontal");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1011 links horizontal");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
												
						// 3 diagonal
					try{
						if(feld[zeile-1][spalte-1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+1][spalte+1] == 1){ //1011 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1011 unten rechts");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1011 unten rechts");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile-1][spalte+1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+1][spalte-1] == 1){ //1011 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1011 unten links");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1011 unten links");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile+1][spalte+1] == 1 && feld[zeile+2][spalte+2] == 1 && feld[zeile+3][spalte+3] == 1){ //0111 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 unten rechts");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 unten rechts");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile+1][spalte+1] == 1){ //1101 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1101 unten rechts");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1101 unten rechts");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
						
					try{
						if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile+1][spalte-1] == 1){ //1101 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1101 unten links");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1101 unten links");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
						
					try{
						if(feld[zeile+1][spalte-1] == 1 && feld[zeile+2][spalte-2] == 1 && feld[zeile+3][spalte-3] == 1){ // 0111 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 unten links");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 unten links");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile-1][spalte+1] == 1 && feld[zeile-2][spalte+2] == 1 && feld[zeile-3][spalte+3] == 1){ //0111 oben rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 oben rechts");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 oben rechts");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile-1][spalte-1] == 1 && feld[zeile-2][spalte-2] == 1 && feld[zeile-3][spalte-3] == 1){ // 0111 oben links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 oben links");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 oben links");
								break;
							}							
						}			
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					} //end if null
				} // end zeile
			
			} //end spalte
			
			if(ergebnis != 99){
				break;
			}//valides Ergebnis? Sonst wird der Gegner überprüft
			
			///Gegner
			
			for (spalte = 0; spalte < 7; spalte++){
				for (zeile = 0; zeile < 6; zeile++){
					
					if(feld[zeile][spalte] == 0){
				
					try{
						// 3 vertikal
						if(feld[zeile][spalte] == 0 && feld[zeile+1][spalte] == 2 && feld[zeile+2][spalte] == 2 && feld[zeile+3][spalte] == 2){
							ergebnis = spalte;
							System.out.println(ergebnis + " Muster vertikal");
							break;
						}
					} catch (ArrayIndexOutOfBoundsException e){}		
						
						// 3 horizontal
					try{
						if(feld[zeile][spalte-1] == 2 && feld[zeile][spalte-2] == 2 && feld[zeile][spalte-3] == 2){ //0111 links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 links horizontal");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 links horizontal");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile][spalte+1] == 2 && feld[zeile][spalte+2] == 2 && feld[zeile][spalte+3] == 2){ //0111 rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 rechts horizontal");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 rechts horizontal");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
						
					try{
						if(feld[zeile][spalte-1] == 2 && feld[zeile][spalte+1] == 2 && feld[zeile][spalte+2] == 2){ //1011 rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1011 rechts horizontal");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1011 rechts horizontal");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
						
					try{
						if(feld[zeile][spalte-2] == 2 && feld[zeile][spalte-1] == 2 && feld[zeile][spalte+1] == 2){ //1101 links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1011 links horizontal");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1011 links horizontal");
								break;
							}						
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
											
						// 3 diagonal
					try{
						if(feld[zeile-1][spalte-1] == 2 && feld[zeile+2][spalte+2] == 2 && feld[zeile+1][spalte+1] == 2){ //1011 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1011 unten rechts");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1011 unten rechts");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile-1][spalte+1] == 2 && feld[zeile+2][spalte-2] == 2 && feld[zeile+1][spalte-1] == 2){ //1011 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1011 unten links");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1011 unten links");
								break;
							}		
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
						
					try{
						if(feld[zeile+1][spalte+1] == 2 && feld[zeile+2][spalte+2] == 2 && feld[zeile+3][spalte+3] == 2){ //0111 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 unten rechts");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 unten rechts");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile-1][spalte-1] == 2 && feld[zeile-2][spalte-2] == 2 && feld[zeile+1][spalte+1] == 2){ //1101 unten rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1101 unten rechts");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1101 unten rechts");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
						
					try{
						if(feld[zeile-1][spalte+1] == 2 && feld[zeile-2][spalte+2] == 2 && feld[zeile+1][spalte-1] == 2){ //1101 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1101 unten links");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 1101 unten links");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
					
					try{	
						if(feld[zeile+1][spalte-1] == 2 && feld[zeile+2][spalte-2] == 2 && feld[zeile+3][spalte-3] == 2){ // 0111 unten links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 unten links");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 unten links");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
						
					try{
						if(feld[zeile-1][spalte+1] == 2 && feld[zeile-2][spalte+2] == 2 && feld[zeile-3][spalte+3] == 2){ //0111 oben rechts
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 oben rechts");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 oben rechts");
								break;
							}							
						}
					} catch (ArrayIndexOutOfBoundsException e){}	
						
					try{
						if(feld[zeile-1][spalte-1] == 2 && feld[zeile-2][spalte-2] == 2 && feld[zeile-3][spalte-3] == 2){ // 0111 oben links
							if (zeile < 5 && feld[zeile+1][spalte] != 0){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 oben links");
								break;
							} else if (zeile ==5 ){
								ergebnis = spalte;
								System.out.println(ergebnis + " Muster 0111 oben links");
								break;
							}							
						}										
					} catch (ArrayIndexOutOfBoundsException e){}
					
				} // end if 0								
				} // end zeile
			} //end spalte
			
			if(ergebnis == 99){
				System.out.println("ergebnis: " + ergebnis);
				ergebnis = (int)(Math.random()*7);
				System.out.println("ergebnis: " + ergebnis);
				break;
			}//valides Ergebnis? Sonst Zufall
	
			System.out.println("ergebnis: " + ergebnis);
			
		} //end while
	} // end berechne
		
} // end class
