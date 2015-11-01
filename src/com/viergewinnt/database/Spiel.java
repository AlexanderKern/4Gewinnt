package com.viergewinnt.database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.chart.PieChart.Data;

public class Spiel {
	
	/**
	 * Die Klasse Spiel bildet das Objekt Spiel mit allen zugehoerigen Attributen 
	 * Verwendung findet diese Klasse bei der Erstellung der Uebersicht aller Spiele
	 * @author MajkenPlugge
	 *
	 */
	
	private String gegner;
	private String farbe;
	private boolean bFarbe;
	private String id;
	private String punkte;
	private String datum; 
	
public Spiel(){};

			/**
			 * Gibt die Id des Spiels zurueck
			 * @return Id des Spiels
			 */
	        public String getId() {
	            return this.id;
	        }
	        /**
	         * Setzt die Id des Spiels
	         * @param id Id des Spiels
	         */
	        public void setId(String id) {
	          this.id = id;
	        }

	        /**
	         * Setzt die erreichten Punkte 
	         * @param punkte Punkte
	         */
	        public void setPunkte(String punkte) {
	            this.punkte = punkte;
	        }
	        
	        /**
	         * Gibt die erreichten Punkte zurueck
	         * @return Punkte
	         */
	        public String getPunkte(){
	        	return this.punkte;
	        }
	       
	       /**
	        * Setzt den Namen des Gegner
	        * @param gegner Namen des Gegner
	        */
	        public void setGegner(String gegner) {
	           this.gegner = gegner;
	        }
	
	        /**
	         * Gibt den Namen des Gegner zurueck
	         * @return Namen des Gegner
	         */ 
	        public String getGegener(){
	        	return this.gegner;
	        }
	        
	        /**
	         * Setzt das Datum an dem das Spiel gespielt stattgefunden hat 
	         * @param datum Datum 
	         */
	        public void setDatum(String datum){
	        	this.datum = datum;
	        }
	        
	        /**
	         * Gibt das Datum an dem das Spiel gespielt stattgefunden hat 
	         * @return Datum 
	         */
	        public String getDatum(){
	        	return this.datum;
	        }
	
	        /**
	         * Setzt die Spielsteinfarbe und wandelt den boolschen Wert in einen String um 
	         * @param bfarbe Spielsteinfarbe
	         */
	        public void setFarbe(boolean bfarbe){
	        	this.bFarbe = bfarbe;
	        	if (bFarbe == false){
	        		this.farbe = "blau";
	        	}else {
					this.farbe = "grün";
				}
	        }
	        
	        /**
	         * Gibt den die Spielsteinfarbe zurueck
	         * @return Spielsteinfarbe 
	         */
	        public String getFarbe(){
	        	
	        	return this.farbe;
	        }

}
