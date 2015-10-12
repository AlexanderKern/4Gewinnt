package Database;


public class ReuseableSpiel {
	
	private static String name;
	public static int id;
	
	public void setName(String name){
		ReuseableSpiel.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setId( int id){
		ReuseableSpiel.id = id; 
	}

}
