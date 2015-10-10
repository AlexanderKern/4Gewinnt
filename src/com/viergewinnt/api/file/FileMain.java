package com.viergewinnt.api.file;

import java.io.File;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import com.viergewinnt.api.common.util.Message;

import Database.ReuseServermethode;
import GUI.ControllerField;
import ki.KI2;

public class FileMain extends Thread {
	private final ControllerField cf;
	private final int sequenceNumber;
	private Message message;

	public FileMain(ControllerField cf, int sequenceNumber) {
		this.cf = cf;
		this.sequenceNumber = sequenceNumber;
	}

	public void run() {
		// Variables
		final KI2 ki = new KI2();
		final String team = ReuseServermethode.getTeam();
		final String filePath = ReuseServermethode.getPfad();
		final String clientFilename = "spieler" + team + "2server.txt";
		final String serverFilename = "server2spieler" + team + ".xml";
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		final File serverFile = new File(filePath + serverFilename);
		final File clientFile = new File(filePath + clientFilename);
		int[] zug;
		
		while (!serverFile.exists() || isRunning(factory, filePath, serverFilename)) {
			if (message == null) {
				continue;
			}
			
			System.out.println(message.getGegnerzug() + " - " + message.getSatzstatus() + " - " + message.getFreigabe() + " - " + message.getSieger());

			if (message.getGegnerzug() >= 0) {
				// Gegnerzug in KI setzen
				ki.setStein(message.getGegnerzug(), true);

				zug = ki.getletzter_zug();

				// Gegnerzug in GUI setzen
				cf.setStone(zug[0], zug[1], true);
			}
			
			// Berechne nächsten Zug
			ki.berechne();

			// Zug in KI setzen
			ki.setStein(ki.get_spalte(), false);

			// Zug in GUI setzen
			zug = ki.getletzter_zug();
			cf.setStone(zug[0], zug[1], false);

			try {
				// Nächsten Zug an Server senden
				// Zug an Server senden
				final FileWriter writer = new FileWriter(clientFile);
				writer.write(String.valueOf(ki.get_spalte()));
				writer.flush();
				writer.close();
				serverFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Message ist gelesen worden.
			message = null;
		}
		cf.setResult(message.getSieger(), sequenceNumber);
		System.out.println("Gewonnen!");
	}

	private boolean isRunning(DocumentBuilderFactory factory, String filePath, String serverFilename) {
		this.message = FileConnectionHandler.handleXml(factory, filePath, serverFilename);
		System.out.println("neues Objeckt");
		return message.getFreigabe() && message.getSatzstatus().equals("Satz spielen");
	}
}
