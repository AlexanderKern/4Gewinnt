package com.viergewinnt.api.common.util;

/**
 * Die Klasse Message beinhaltet Getter und Setter Methode für die Attribute,
 * die über die File oder Push-Schnittstelle gesendet werden.
 * @author Alexander Kern
 *
 */

public class Message {
	private String message;
	private boolean freigabe;
	private String satzstatus;
	private int gegnerzug;
	private String sieger;
	
	public Message(boolean freigabe, String satzstatus, String gegnerzug, String sieger){
		this.setFreigabe(freigabe);
		this.setSatzstatus(satzstatus);
		this.setGegnerzug(gegnerzug);
		this.setSieger(sieger);
	}
	
	public Message(){
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getFreigabe() {
		return freigabe;
	}
	
	/**
	 * 
	 * @param freigabe
	 */
	public void setFreigabe(boolean freigabe) {
		this.freigabe = freigabe;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSatzstatus() {
		return satzstatus;
	}
	
	/**
	 * 
	 * @param satzstatus
	 */
	public void setSatzstatus(String satzstatus) {
		this.satzstatus = satzstatus;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getGegnerzug() {
		return gegnerzug;
	}
	
	/**
	 * 
	 * @param gegnerzug
	 */
	public void setGegnerzug(String gegnerzug) {
		this.gegnerzug = Integer.parseInt(gegnerzug);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSieger() {
		return sieger;
	}
	
	/**
	 * 
	 * @param sieger
	 */
	public void setSieger(String sieger) {
		this.sieger = sieger;
	}
}
