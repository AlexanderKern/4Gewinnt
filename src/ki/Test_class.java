package ki;



public class Test_class {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int [][] feld = new int [6][7];
		
		feld [0][0] = 0;
		feld [1][0] = 0;
		feld [2][0] = 0;
		feld [3][0] = 0;
		feld [4][0] = 0;
		feld [5][0] = 1;
		
		feld [0][1] = 0;
		feld [1][1] = 0;
		feld [2][1] = 0;
		feld [3][1] = 0;
		feld [4][1] = 1;
		feld [5][1] = 2;
		
		feld [0][2] = 0;
		feld [1][2] = 0;
		feld [2][2] = 0;
		feld [3][2] = 1;
		feld [4][2] = 2;
		feld [5][2] = 2;
		
		feld [0][3] = 0;
		feld [1][3] = 0;
		feld [2][3] = 0;
		feld [3][3] = 1;
		feld [4][3] = 1;
		feld [5][3] = 2;
		
		feld [0][4] = 0;
		feld [1][4] = 0;
		feld [2][4] = 0;
		feld [3][4] = 0;
		feld [4][4] = 0;
		feld [5][4] = 0;
		
		feld [0][5] = 0;
		feld [1][5] = 0;
		feld [2][5] = 0;
		feld [3][5] = 0;
		feld [4][5] = 0;
		feld [5][5] = 0;
		
		feld [0][6] = 0;
		feld [1][6] = 0;
		feld [2][6] = 0;
		feld [3][6] = 0;
		feld [4][6] = 0;
		feld [5][6] = 0;
		
	
		
		
		
		
		
		KI2 ki = new KI2();
		ki.berechne();
		System.out.println(ki.get_spalte());
		
		
		
		
		
		

	}

}
