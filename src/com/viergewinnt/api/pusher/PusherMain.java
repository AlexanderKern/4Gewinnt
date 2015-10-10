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

								// Stein in KI setzen
								ki.setStein(ki.get_spalte(), false);

								int[] zug = ki.getletzter_zug();
								// ControllerField cf = new ControllerField();
								cf.setStone(zug[0], zug[1], false);

							} else {
								// Gegnerzug in KI setzen
								ki.setStein(message.getGegnerzug(), true);
								
								//Gegnerzug in GUI setzen
								int[] zug = ki.getletzter_zug();
								cf.setStone(zug[0], zug[1], true);

								// Berechne nächsten Zug
								ki.berechne();

								// Zug in KI setzen
								ki.setStein(ki.get_spalte(), false);
								
								//Zug in GUI setzen
								zug = ki.getletzter_zug();
								cf.setStone(zug[0], zug[1], false);

								// Nächsten Zug an Server senden
								channel.trigger("client-event", "{\"move\":\"" + ki.get_spalte() + "\"}");
							}

						} else {
							cf.setResult(message.getSieger(), sequenceNumber);
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
