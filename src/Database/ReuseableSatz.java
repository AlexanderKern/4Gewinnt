package Database;

/**
 * Wiederverwendung der zugewiesenen Werte zur Instanz Satz im Laufe des Programms 
 * @author MajkenPlugge
 *
 */

public class ReuseableSatz {
	
	public static int id;

	public void setId( int id){
		ReuseableSatz.id = id; 
	}
	
	public int getId(){
		return id;
	}
	
}
