package ki;

public class Spielfeld {
	
	//Variablen
	private int [][] feld = new int [7][6];
	private int spalte;
	private boolean gegner;
	private int [] letzter_zug = new int [2];
	
	
	

	

	//Konstruktor
	public Spielfeld(){
		
		for (int a = 7; a >= 0; a--){
			for (int b = 6; b >= 0; b--){
				feld [a][b] = 0;
			}
		}
		
	}
	
	
	// Setter (feld: 0=unbelegt,1=spieler,2=gegner)
	public void setStein(int spalte, boolean gegner) {
		
		spalte = this.spalte;
		gegner = this.gegner;
		
		if (gegner = true) {
			for (int zeile = 5; zeile >= 0; zeile--){
				if ( feld [spalte][zeile] == 0){	
					feld [spalte][zeile] = 2;
					letzter_zug[0] = spalte;
					letzter_zug[1] = zeile;
					break;
				}//endif
			}//endfor
		} else {
			for (int zeile = 5; zeile >= 0; zeile--){
				if ( feld [spalte][zeile] == 0){	
					feld [spalte][zeile] = 1;
					letzter_zug[0] = spalte;
					letzter_zug[1] = zeile;
					break;
				}//endif
			}//endfor
			
		}
		
				
	}
	
	//getter
	public int getStein() {
		
		return spalte;
		
	}
	
	public int[] getletzter_zug(){
		
		return letzter_zug;
		
	}
	
	
}
