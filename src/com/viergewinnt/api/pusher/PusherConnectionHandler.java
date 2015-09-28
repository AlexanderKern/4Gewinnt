package com.viergewinnt.api.pusher;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.pusher.client.AuthorizationFailureException;
import com.pusher.client.Authorizer;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.PrivateChannel;
import com.pusher.client.channel.PrivateChannelEventListener;
import com.pusher.client.connection.Connection;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionStateChange;
import com.viergewinnt.api.common.util.Function;

public class PusherConnectionHandler implements ConnectionEventListener, PrivateChannelEventListener {

	private static final String CHANNEL_NAME = "private-channel";
	private static final String EVENT_NAME = "private-event";
	private static final String API_KEY = "7f0b8e49143ae72cc42c";
	private static final String APPSECRET = "fe6c6faf981f752c36f3";
	private static final Function <Pusher,PrivateChannel,String> DEFAULT_HANDLER = new Function <Pusher,PrivateChannel,String>() {
		@Override
		public void execute (Pusher pusher,PrivateChannel channel,String data) {
			System.out.println("Missing Handler!");	
		}
	};
	private final Map<String, Function <Pusher,PrivateChannel,String>> handlers;
	private final Pusher pusher;
	private PrivateChannel channel;
	
	public PusherConnectionHandler() {
		this.handlers = new HashMap<>();
		
		this.pusher = new Pusher(API_KEY, new PusherOptions().setAuthorizer(new Authorizer() {
			public String authorize(String channelName, String socketId) throws AuthorizationFailureException {
				System.out.println("The clients channelName: " + channelName);
				System.out.println("The clients socketId: " + socketId);

				String message = socketId + ":" + channelName;

				String hash = HmacSHA256.getHmacSHA256HexDigest(APPSECRET, message);

				String signature = "{\"auth\":\"" + API_KEY + ":" + hash + "\"}";

				return signature;
			}
		}));
	}

	public Connection connect() {
		pusher.connect(this);
		
		channel = pusher.subscribePrivate(CHANNEL_NAME, this, EVENT_NAME);
		// if connected.equals dann channel.trigger("event","message");	
		return pusher.getConnection();
	}
	
	public PusherConnectionHandler registerHandler(String name, Function <Pusher,PrivateChannel,String> handler){
		handlers.put(name, handler);
		
		return this;
	}

	/* ConnectionEventListener implementation */

	@Override
	public void onConnectionStateChange(final ConnectionStateChange change) {

		System.out.println(String.format("Connection state changed from [%s] to [%s]", change.getPreviousState(),
				change.getCurrentState()));
	}

	@Override
	public void onError(final String message, final String code, final Exception e) {

		System.out.println(
				String.format("An error was received with message [%s], code [%s], exception [%s]", message, code, e));
	}

	/* PrivateChannelEventListener implementation */

	@Override
	public void onEvent(final String channelName, final String eventName, final String data) {

		System.out.println(
				String.format("Received event [%s] on channel [%s] with data [%s]", eventName, channelName, data));
		
		if(!handlers.containsKey(eventName)){
			registerHandler(eventName, DEFAULT_HANDLER);
		}
		
		try {
			handlers.get(eventName).execute(pusher,channel,data);
		} catch (IOException e) {
			System.out.println("Kritischer Fehler!");
			e.printStackTrace();
		}
	}

	@Override
	public void onSubscriptionSucceeded(final String channelName) {

		System.out.println(String.format("Subscription to channel [%s] succeeded", channel.getName()));
	}

	@Override
	public void onAuthenticationFailure(final String message, final Exception e) {

		System.out.println(String.format("Authentication failure due to [%s], exception was [%s]", message, e));
	}

}