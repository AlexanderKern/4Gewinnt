package com.viergewinnt.database;


public class ValueClass {
	
	/**
	 * Die Klasse ValueClass dient zur Herstellung der Tabellenueberischt aller Spiele und aller Saetze
	 * Mit Hilfe dieser Klasse werden die Spaltennamen gesetzt und die Spalteninhalte herausgeholt werden 
	 * @author MajkenPlugge
	 *
	 */
	
	
	  private String column1;
	  private String column2;
	  private String column3;
	  private String column4;
	  private String column5;

	  /**
	   * Setzt die Namen der Spalten 
	   * @param column1 Name der Spalte 1
	   * @param column2 Name der Spalte 2
	   * @param column3 Name der Spalte 3
	   * @param column4 Name der Spalte 4
	   * @param column5 Name der Spalte 5
	   */
	  public ValueClass(String column1, String column2, String column3, String column4 ,String column5) {
	    this.column1 = column1;
	    this.column2 = column2;
	    this.column3 = column3;
	    this.column4 = column4;
	    this.column5 = column5;
	  }

	  /**
	   * Gibt den Wert der Spalte 1 zurueck 
	   * @return Wert der Spalte 1
	   */
	  public String getColumn1() {
	    return column1;
	  }

	  /**
	   * Gibt den Wert der Spalte 2 zurueck 
	   * @return Wert der Spalte 2
	   */
	  public String getColumn2() {
	    return column2;
	  }

	  /**
	   * Setzen des Wert der Spalte 2
	   * @param column2 Wert der Spalte 2
	   */
	  public void setColumn2(String column2) {
		    this.column2 = column2;
		  }
	  
	  
	  /**
	   * Gibt den Wert der Spalte 3 zurueck 
	   * @return Wert der Spalte 3
	   */
	  public String getColumn3() {
	    return column3;
	  }
	  
	  /**
	   * Gibt den Wert der Spalte 4 zurueck 
	   * @return Wert der Spalte 4
	   */
	  public String getColumn4() {
		    return column4;
		  }
	  /**
	   * Gibt den Wert der Spalte 5 zurueck 
	   * @return Wert der Spalte 5
	   */
	  public String getColumn5() {
		    return column5;
		  }
	


}
