package com.viergewinnt.api.pusher;

import java.awt.Toolkit;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
import com.pusher.client.Pusher;
import com.pusher.client.channel.PrivateChannel;
import com.pusher.client.connection.Connection;
import com.pusher.client.connection.ConnectionState;
import com.viergewinnt.api.common.util.Function;
import com.viergewinnt.api.common.util.Message;

import Database.DatabaseCreate;
import Database.ReuseableSatz;
import Database.Satzgewinner;
import Database.Zug;
import ki.KI2;

public class PusherMain {
//	private String team;
//
//	public PusherMain(String team){
//		this.team = team;
//	}

	public String pusher() {
		KI2 ki = new KI2();
		
		System.out.println("Pusher");
		
		final PusherConnectionHandler pch = new PusherConnectionHandler()
				.registerHandler("MoveToAgent", new Function <Pusher,PrivateChannel,String>(){
					public void execute(Pusher pusher,PrivateChannel channel,String data) throws IOException {
						//Parsen
						ObjectMapper mapper = new ObjectMapper();
						Message message = mapper.readValue(data, Message.class);
						if(message.getFreigabe() && message.getSatzstatus().equals("Satz spielen") && message.getSieger().equals("offen") ){
							if(Integer.parseInt(message.getGegnerzug())< 0){
								
								//KI berechnet Zug
								ki.berechne();
								
								//Zug an Server senden
								channel.trigger("client-event", "{\"move\":\"" + ki.get_spalte() + "\"}");
								
								// Aktuellen Zug in Datenbank speichern
								ReuseableSatz reuseSatz = new ReuseableSatz();
								int [] letzterZug = ki.getletzter_zug();
								DatabaseCreate db = new DatabaseCreate();
								Zug zug;
								try {
									zug = new Zug(reuseSatz.id, false, letzterZug[1], letzterZug[0], db);
									System.out.println("Der angelegte Zug hat die Id" + zug.id);
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								//Stein in KI setzen
								ki.setStein(ki.get_spalte(), false);
								
							}
								else{
									//Gegnerzug in KI setzen
									ki.setStein(Integer.parseInt(message.getGegnerzug()), true);
									
									// Aktuellen Zug in Datenbank speichern
									ReuseableSatz reuseSatz = new ReuseableSatz();
									int [] letzterZug = ki.getletzter_zug();
									DatabaseCreate db = new DatabaseCreate();
									Zug zug;
									try {
										zug = new Zug(reuseSatz.id, true, letzterZug[1], letzterZug[0], db);
										System.out.println("Der angelegte Zug hat die Id" + zug.id);
									} catch (Exception e) {
										e.printStackTrace();
									}
									
									//Berechne nächsten Zug
									ki.berechne();
									
									//Stein in KI setzen
									ki.setStein(ki.get_spalte(), false);
									
									//Nächsten Zug an Server senden
									channel.trigger("client-event", "{\"move\":\"" + ki.get_spalte() + "\"}");
									
									//Zug in Datenbank speichern
									letzterZug = ki.getletzter_zug();
									try {
										zug = new Zug(reuseSatz.id, false, letzterZug[1], letzterZug[0], db);
										System.out.println("Der angelegte Zug hat die Id" + zug.id);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
								
							
						}else{
							Satzgewinner gewinner = new Satzgewinner();
							gewinner.setGewinner(message.getSieger());
							pusher.disconnect();
						}
						System.out.println(message.getFreigabe());
						Toolkit.getDefaultToolkit().beep();
						
					}
				});
		
		final Connection con = pch.connect();

		System.out.println(con.getState().name());

		// Connection zu Pusher offen halten.
		do {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (con.getState() == ConnectionState.CONNECTED || con.getState() == ConnectionState.CONNECTING);
		
		Satzgewinner gewinner = new Satzgewinner();
		
		return gewinner.getGewinner();

	}

}
