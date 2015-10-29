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
	
	private String gegner;
	private String farbe;
	private boolean bFarbe;
	private String id;
	private String punkte;
	private String datum; 
	
public Spiel(){};

	        public String getId() {
	            return this.id;
	        }
	        public void setId(String id) {
	          this.id = id;
	        }

	        public void setPunkte(String punkte) {
	            this.punkte = punkte;
	        }
	        
	        public String getPunkte(){
	        	return this.punkte;
	        }
	       

	        public void setGegner(String gegner) {
	           this.gegner = gegner;
	        }
	
	        public String getGegener(){
	        	return this.gegner;
	        }
	        
	        public void setDatum(String datum){
	        	this.datum = datum;
	        }
	        
	        public String getDatum(){
	        	return this.datum;
	        }
	
	        public void setFarbe(boolean bfarbe){
	        	this.bFarbe = bfarbe;
	        	if (bFarbe == true){
	        		this.farbe = "blau";
	        	}else {
					this.farbe = "grün";
				}
	        }
	        
	        public String getFarbe(){
	        	
	        	return this.farbe;
	        }

}
