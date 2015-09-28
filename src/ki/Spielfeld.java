package ki;

public class Spielfeld {
	
	//Variablen
	public int [][] feld = new int [6][7];
	private int spalte_rueckgabe;
	private boolean gegner;
	private int [] letzter_zug = new int [2];


	//Konstruktor
	public Spielfeld( int[][] feld ){
		
		this.feld = feld;
		
	}
	
	
	// Setter (feld: 0=unbelegt,1=spieler,2=gegner)
	public void setStein(int spalte, boolean gegner) {
		
		spalte_rueckgabe = spalte;
		this.gegner = gegner;
		
		if (this.gegner = true) {
			for (int zeile = 5; zeile >= 0; zeile--){
				if ( feld [zeile][spalte_rueckgabe] == 0){	
					feld [zeile][spalte_rueckgabe] = 2;
					letzter_zug[1] = spalte_rueckgabe;
					letzter_zug[0] = zeile;
					break;
				}//endif
			}//endfor
		} else {
			for (int zeile = 5; zeile >= 0; zeile--){
				if ( feld [zeile][spalte_rueckgabe] == 0){	
					feld [zeile][spalte_rueckgabe] = 1;
					letzter_zug[1] = spalte_rueckgabe;
					letzter_zug[0] = zeile;
					break;
				}//endif
			}//endfor
			
		}
		
				
	}
	
	//getter
	public int getStein() {
		
		return spalte_rueckgabe;
		
	}
	
	public int[] getletzter_zug(){
		
		return letzter_zug;
		
	}
	
	
}
