package Database;

public class ReuseServermethode {
	private static String methode;
	private static String team;
	private static String pfad;

	public static void setMethode(String methode) {
		ReuseServermethode.methode = methode;
	}

	public static String getMethode() {
		return methode;
	}
	
	public static void setTeam(String team) {
		ReuseServermethode.team = team;
	}

	public static String getTeam() {
		return team;
	}
	
	public static void setPfad(String pfad) {
		ReuseServermethode.pfad = pfad;
	}

	public static String getPfad() {
		return pfad;
	}
}