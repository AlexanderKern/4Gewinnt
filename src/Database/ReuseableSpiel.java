package Database;

public class ReuseableSpiel {
	
	private static String name;
	
	public void setName(String name){
		ReuseableSpiel.name = name;
	}
	
	public String getName(){
		return name;
	}

}
