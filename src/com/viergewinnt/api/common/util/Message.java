package com.viergewinnt.api.common.util;

public class Message {
	
	private boolean freigabe;
	private String satzstatus;
	private String gegnerzug;
	private String sieger;
	
	public Message(boolean freigabe, String satzstatus, String gegnerzug, String sieger){
		this.setFreigabe(freigabe);
		this.setSatzstatus(satzstatus);
		this.setGegnerzug(gegnerzug);
		this.setSieger(sieger);
	}
	
	public Message(){
		
	}
	
	public boolean getFreigabe() {
		return freigabe;
	}
	public void setFreigabe(boolean freigabe) {
		this.freigabe = freigabe;
	}
	public String getSatzstatus() {
		return satzstatus;
	}
	public void setSatzstatus(String satzstatus) {
		this.satzstatus = satzstatus;
	}
	public String getGegnerzug() {
		return gegnerzug;
	}
	public void setGegnerzug(String gegnerzug) {
		this.gegnerzug = gegnerzug;
	}
	public String getSieger() {
		return sieger;
	}
	public void setSieger(String sieger) {
		this.sieger = sieger;
	}
}
