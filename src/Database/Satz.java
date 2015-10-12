package Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Satz {
	
	public int id;
	public int spielId;
	public String gewonnen;
	
	public Satz( int spielId){
		this.spielId = spielId;
	}

	public void createSatz( Satz satz ) throws SQLException{
	
		int satzId;
		
		PreparedStatement stSatz = Database.conn.prepareStatement("INSERT INTO satz(spiel_id) VALUES(?)");
		stSatz.setInt(1, satz.spielId);
		stSatz.execute();
		stSatz.close();
		
		PreparedStatement callId = Database.conn.prepareStatement("CALL IDENTITY();");
		
		ResultSet rsId = callId.executeQuery();
	     rsId.next();
	     satzId = rsId.getInt(1);
	     satz.id = spielId;
	     System.out.println("Satz Id"+satz.id);
	     rsId.close();
					
		
		ReuseableSatz reuseSatz = new ReuseableSatz();
		reuseSatz.setId(this.id);
		
		
	}
	
	public void updateSatz(String gewonnen, int satzId) throws SQLException{
		PreparedStatement stSatzende = Database.conn.prepareStatement("UPDATE satz SET gewonnen = ? WHERE id = ?");
		stSatzende.setString(1, gewonnen);
		stSatzende.setInt(2, satzId);
		stSatzende.executeUpdate();
	}
	
	public int getAnzahl(int spielId) throws SQLException{
		int anzahlSaetze;
		
		PreparedStatement dritterSatz = Database.conn.prepareStatement("SELECT * FROM satz WHERE spiel_id = ? ");
		dritterSatz.setInt(1, spielId);
		anzahlSaetze =  dritterSatz.getMaxRows();
		 System.out.println("Anzahl"+ anzahlSaetze );
		
		return anzahlSaetze;
	}
	
	public void getGewonneneSaetze(int spielId){
		//Wer welchen Satz gewonnen hat 
		/*int pktSpiel ; 
		
		PreparedStatement countGewonneneSaetze = Database.conn.prepareStatement("SELECT COUNT(*)  FROM satz WHERE spiel_id = ? AND gewonnen = true ");
		countGewonneneSaetze.setInt(1 , spielId);
		pktSpiel = countGewonneneSaetze.getMaxRows();
		System.out.println(pktSpiel);
		
		return pktSpiel;
		*/
	}
	
	
	public int satzende(Satz satz) throws SQLException{
		int pktSpiel ; 
	    int anzahlSaetze ;
	     
	    PreparedStatement stSatzende = Database.conn.prepareStatement("UPDATE satz SET gewonnen = ? WHERE id = ?");
	    stSatzende.setString(1, satz.gewonnen);
	    stSatzende.setInt(2, satz.id);
	    
	    stSatzende.executeUpdate();
	  
	    
	
		//Check ob 3 Sätze bereits gespielt sind
	    
	    PreparedStatement dritterSatz = Database.conn.prepareStatement("SELECT * FROM satz WHERE spiel_id = ? ");
	    dritterSatz.setInt(1, satz.spielId);
	   anzahlSaetze =  dritterSatz.getMaxRows();
	   System.out.println("Anzahl"+ anzahlSaetze );
	   
	   
	 /*  ResultSet rsDritterSatz = (ResultSet) dritterSatz;
		while (rsDritterSatz.next()){
			anzahlSaetze = (rsDritterSatz.getRow());
		}// end of while
		*/
			
		if(anzahlSaetze == 3){ // wenn 3 STätze gespielt, dann update das Spiel ergebnis
			
				// Gewonnene Sätze zählen
				PreparedStatement countGewonneneSaetze = Database.conn.prepareStatement("SELECT COUNT(*)  FROM satz WHERE spiel_id = ? AND gewonnen = true ");
				countGewonneneSaetze.setInt(1 , satz.spielId);
				pktSpiel = countGewonneneSaetze.getMaxRows();
				System.out.println(pktSpiel);
			
				}
			else{ // noch keine 3 Saetze gespielt
				 pktSpiel = -1;
			}//end of if
	
		/* If pktSpiel = -1 --> nothing 
		 * If pktSPiel = 0-3 --> call method spielende
		*/
		return pktSpiel;   
		
	}
	
	
	
}
