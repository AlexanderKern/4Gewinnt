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
import com.viergewinnt.api.common.util.ReuseServermethode;
import com.viergewinnt.database.Database;
import com.viergewinnt.database.ReuseableSatz;
import com.viergewinnt.database.ReuseableSpiel;
import com.viergewinnt.gui.ControllerField;
import com.viergewinnt.ki.KiMain;

/**
 * Die Klasse PusherMain ermoeglicht die Kommunikation mittels Websockets
 * 
 * @author Alexander Kern
 *
 */
public class PusherMain {
	ControllerField cf;

	/**
	 * 
	 * @param cf
	 */
	public PusherMain(ControllerField cf) {
		this.cf = cf;
	}

	/**
	 * 
	 * @param team
	 *            Name des Teams
	 * @param sequenceNumber
	 *            Nummer des Satzes
	 */
	public void pusher(String team, int sequenceNumber) {
		KiMain ki = new KiMain();
		
		/**
		 * Beim Erhalt einer Nachricht wird diese Methode (Interface) aufgerufen.
		 * Hier wird dann mit der Nachricht umgegangen.
		 */

		final PusherConnectionHandler pch = new PusherConnectionHandler().registerHandler("MoveToAgent",
				new Function<Pusher, PrivateChannel, String>() {
					public void execute(Pusher pusher, PrivateChannel channel, String data) throws IOException {
						// Datenbank---------------------------------------------------------------------------------------------
						Database db = new Database();

						try {
							if (sequenceNumber != 1) {
								cf.setGespielteSaetze(db.getGewonneneSaetze(ReuseableSpiel.getId()));
							}

						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// ------------------------------------------------------------------------------------------------------
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

								// Stein in KI setzen
								ki.setEigenerStein(ki.get_spalte());

								int[] zug = ki.getletzter_zug();
								// Zug in
								// Datenbank---------------------------------------------------------------------------------------------
								try {
									db.Zug(ReuseableSatz.getId(), false, zug[1], zug[0]);
								} catch (SQLException e) {
									e.printStackTrace();
								}
								// -------------------------------------------------------------------------------------------------------

								// ControllerField cf = new ControllerField();
								cf.setStone(zug[0], zug[1], ReuseServermethode.getTeamfarbe());

								// Zug an Server senden
								channel.trigger("client-event", "{\"move\":\"" + zug[1] + "\"}");
							} else {
								// Gegnerzug in KI setzen
								ki.setGegnerStein(message.getGegnerzug());

								int[] zug = ki.getletzter_zug();
								// Zug in
								// Datenbank---------------------------------------------------------------------------------------------
								try {
									db.Zug(ReuseableSatz.getId(), true, zug[1], zug[0]);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								// -------------------------------------------------------------------------------------------------------
								// Gegnerzug in GUI setzen
								cf.setStone(zug[0], zug[1], ReuseServermethode.getGegnerfarbe());

								// Berechne nächsten Zug
								ki.berechne();

								// Zug in KI setzen
								ki.setEigenerStein(ki.get_spalte());

								zug = ki.getletzter_zug();
								// Zug in
								// Datenbank---------------------------------------------------------------------------------------------
								try {
									db.Zug(ReuseableSatz.getId(), false, zug[1], zug[0]);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								// -------------------------------------------------------------------------------------------------------
								// Zug in GUI setzen
								cf.setStone(zug[0], zug[1], ReuseServermethode.getTeamfarbe());

								// N�chsten Zug an Server senden
								channel.trigger("client-event", "{\"move\":\"" + zug[1] + "\"}");
							}

						} else {
							cf.setResult(message.getSieger(), sequenceNumber);
							pusher.disconnect();
							// Satzende
							// X = gr�n // 0 blau
							if (("Spieler " + ReuseServermethode.getTeam()).equals(message.getSieger())) {
								ReuseableSatz.setGewonnen("Claire");
							} else {
								ReuseableSatz.setGewonnen(ReuseServermethode.getGegner());
							}
							String status = "";

							if (ReuseableSatz.getGewonnen().equals("Claire")) {
								status = "gewonnen";
							} else {
								status = "verloren";
							}
							try {
								db.updateSatz(status, ReuseableSatz.getId());
								db.getAnzahlSaetze(ReuseableSpiel.getId());
								if (db.getAnzahlSaetze(ReuseableSpiel.getId()) == 3) {
									db.spielEnde(ReuseableSpiel.getId(), db.getSpielPkt(ReuseableSpiel.getId()));
									db.spielGewinner();
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}

							cf.sichtbar(true, ReuseableSatz.getGewonnen());
							// -------------------------------------------------------------------------------------------------------
						}
						Toolkit.getDefaultToolkit().beep();

					}
				});

		final Connection con = pch.connect();

		/**
		 * Connection zu Pusher offen halten.
		 */
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
