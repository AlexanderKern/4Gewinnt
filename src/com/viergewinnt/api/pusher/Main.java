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

public class Main {

	public static void main(String[] args) {
		final PusherConnectionHandler pch = new PusherConnectionHandler()
				.registerHandler("private-event", new Function <Pusher,PrivateChannel,String>(){
					public void execute(Pusher pusher,PrivateChannel channel,String data) throws IOException {
						//Parsen
						ObjectMapper mapper = new ObjectMapper();
						Message message = mapper.readValue(data, Message.class);
						System.out.println(message.getFreigabe());
						Toolkit.getDefaultToolkit().beep();
						channel.trigger("client-myEvent", "{\"myName\":\"Bob\"}");
						pusher.disconnect();
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
		
		System.out.println("Hallo");

	}

}
