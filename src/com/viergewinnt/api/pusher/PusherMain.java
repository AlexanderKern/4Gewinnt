package com.viergewinnt.api.pusher;

import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;

import org.codehaus.jackson.map.ObjectMapper;
import com.pusher.client.Pusher;
import com.pusher.client.channel.PrivateChannel;
import com.pusher.client.connection.Connection;
import com.pusher.client.connection.ConnectionState;
import com.viergewinnt.api.common.util.Function;
import com.viergewinnt.api.common.util.Message;

import Database.Database;
import Database.ReuseableSatz;
import Database.ReuseableSpiel;
import Database.Satz;
import Database.Spiel;
import Database.Zug;
import GUI.ControllerField;
import ki.KI2;

public class PusherMain {
	ControllerField cf;

	public PusherMain(ControllerField cf) {
		this.cf = cf;
	}

	public void pusher(String team, int sequenceNumber) {
		KI2 ki = new KI2();

		final PusherConnectionHandler pch = new PusherConnectionHandler().registerHandler("MoveToAgent",
				new Function<Pusher, PrivateChannel, String>() {
					public void execute(Pusher pusher, PrivateChannel channel, String data) throws IOException {
						// Parsen
						ObjectMapper mapper = new ObjectMapper();
						Message message = mapper.readValue(data, Message.class);
						String[] messageParts = message.getMessage().split(" # ");
						message.setFreigabe(Boolean.valueOf(messageParts[0]));
						message.setSatzstatus(messageParts[1]);
						message.setGegnerzug(messageParts[2]);
						message.setSieger(messageParts[3]);
						if (message.getFreigabe() && message.getSatzstatus().equals("Satz spielen")
								&& message.getSieger().equals("offen")) {
							if (message.getGegnerzug() < 0) {
 
								// KI berechnet Zug
								ki.berechne();

								// Zug an Server senden
								channel.trigger("client-event", "{\"move\":\"" + ki.get_spalte() + "\"}");
								System.out.println(ki.get_spalte());
								// Stein in KI setzen
								ki.setStein(ki.get_spalte(), false);

								int[] zug = ki.getletzter_zug();
								// ControllerField cf = new ControllerField();
								cf.setStone(zug[0], zug[1], false);
/*_________________________________________________________________________________________________*/
								//Zug von uns
								Database db = new Database();
								ReuseableSatz reSatz = new ReuseableSatz();
								//(int satz_id, boolean gegner, int spalte,int zeile, Database db)
								try {
									Zug zugGegner = new Zug(reSatz.id ,false , zug[1], zug[0], db );
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
/*_________________________________________________________________________________________________*/
								

							} else {
								// Gegnerzug in KI setzen
								ki.setStein(message.getGegnerzug(), true);
								
								//Gegnerzug in GUI setzen
								int[] zug = ki.getletzter_zug();
								cf.setStone(zug[0], zug[1], true);
								
/*_________________________________________________________________________________________________*/								
								Database db = new Database();
								ReuseableSatz reSatz = new ReuseableSatz();
								//(int satz_id, boolean gegner, int spalte,int zeile, Database db)
								try {
									Zug zugGegner = new Zug(reSatz.id ,true , zug[1], zug[0], db );
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
/*_________________________________________________________________________________________________*/

								// Berechne nächsten Zug
								ki.berechne();

								// Zug in KI setzen
								ki.setStein(ki.get_spalte(), false);
								
								//Zug in GUI setzen
								zug = ki.getletzter_zug();
								cf.setStone(zug[0], zug[1], false);
/*_________________________________________________________________________________________________*/
								//(int satz_id, boolean gegner, int spalte,int zeile, Database db)
								try {
									Zug zugGegner = new Zug(reSatz.id ,false , zug[1], zug[0], db );
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
/*_________________________________________________________________________________________________*/
								// Nächsten Zug an Server senden
								channel.trigger("client-event", "{\"move\":\"" + ki.get_spalte() + "\"}");
							}

						} else {
							cf.setResult(message.getSieger(), sequenceNumber);
/*_________________________________________________________________________________________________*/
							// Wer gewonnen hat : message.getSieger()
							// Update satz (gewonnen, reSatz.iD
							ReuseableSatz reSatz = new ReuseableSatz();
							Satz satz = new Satz(reSatz.id);
							try {
								satz.updateSatz(message.getSieger(),reSatz.id);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							//Methode Anzahl Sätze
							ReuseableSpiel reSpiel = new ReuseableSpiel();
							int anzahlSaetze = 0;
							try {
								anzahlSaetze = satz.getAnzahl(reSpiel.id);
								
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							//Methode wer welchen Satz gewonnen hat
							String[] werGewonnen = new String[2];
							try {
								werGewonnen = satz.getGewonneneSaetze(reSpiel.id);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							//bei 2 gewonnenen Sätzen oder 3 Sätzen Methode update Spiel 
							int wirGewonnen = 0;
							int gegnerGewonnen = 0;
							for( int i = 1 ; i < werGewonnen.length; i++){
								switch(3){
								case 1: werGewonnen[i].equals("gewonnen");
								wirGewonnen = wirGewonnen +1;
								break;
								case 2: werGewonnen[i].equals("verloren");
								gegnerGewonnen = gegnerGewonnen +1; 
								break;
								case 3: werGewonnen[i].equals("offen");
								break;
								}
							}
						 if(wirGewonnen == 2 || gegnerGewonnen == 2 || anzahlSaetze == 3 ){
							 //Wie werden die Punkte vergeben? 
								Spiel spiel = new Spiel(reSpiel.getName());
								try {
									spiel.spielende(reSpiel.id, wirGewonnen);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					
							 
						 }
												
							
/*_________________________________________________________________________________________________*/
							pusher.disconnect();
						}
						Toolkit.getDefaultToolkit().beep();

					}
				});

		final Connection con = pch.connect();

		// Connection zu Pusher offen halten.
		new Thread(new Runnable() {

			public void run() {
				do {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} while (con.getState() == ConnectionState.CONNECTED || con.getState() == ConnectionState.CONNECTING);
				
			}
		}).start();
		

	}

}
