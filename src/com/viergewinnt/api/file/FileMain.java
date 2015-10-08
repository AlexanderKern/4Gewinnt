package com.viergewinnt.api.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import com.viergewinnt.api.common.util.Message;

import Database.ReuseServermethode;
import GUI.ControllerField;
import ki.KI2;

public class FileMain {
	ControllerField cf;

	public FileMain(ControllerField cf) {
		this.cf = cf;
	}

	private static FileWriter writer;

	public void file(int sequenceNumber) throws IOException {
		KI2 ki = new KI2();

		// Variables
		System.out.println("halo");
		final String team = ReuseServermethode.getTeam();
		final String filePath = ReuseServermethode.getPfad();
		final String clientFilename = "spieler" + team + "2server.txt";
		final String serverFilename = "server2spieler" + team + ".xml";
		final File serverFile = new File(filePath + serverFilename);
		final File clientFile = new File(filePath + clientFilename);
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		//Neuer Thread um GUI nicht einzufrieren
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					if (serverFile.exists()) {
						Message message = FileConnectionHandler.handleXml(factory, filePath, serverFilename);

						if (message.getFreigabe() && message.getSatzstatus().equals("Satz spielen")
								&& message.getSieger().equals("offen")) {
							if (Integer.parseInt(message.getGegnerzug()) < 0) {

								// KI berechnet Zug
								ki.berechne();

								// Zug an Server senden
								try {
									if (!clientFile.exists()) {
										clientFile.createNewFile();
										writer = new FileWriter(clientFile);
										writer.write(ki.get_spalte());
										writer.close();
										serverFile.delete();
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								// Stein in KI setzen
								ki.setStein(ki.get_spalte(), false);

								int[] zug = ki.getletzter_zug();
								cf.setStone(zug[0], zug[1], false);

							} else {
								// Gegnerzug in KI setzen
								ki.setStein(Integer.parseInt(message.getGegnerzug()), true);

								// Gegnerzug in GUI setzen
								int[] zug = ki.getletzter_zug();
								cf.setStone(zug[0], zug[1], true);

								// Berechne nächsten Zug
								ki.berechne();

								// Zug in KI setzen
								ki.setStein(ki.get_spalte(), false);

								// Zug in GUI setzen
								zug = ki.getletzter_zug();
								cf.setStone(zug[0], zug[1], false);

								// Nächsten Zug an Server senden
								// Zug an Server senden
								try {
									if (!clientFile.exists()) {
										clientFile.createNewFile();
										writer = new FileWriter(clientFile);
										writer.write(ki.get_spalte());
										writer.close();
										serverFile.delete();
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}

						} else {
							cf.setResult(message.getSieger(), sequenceNumber);
							// Schleife verlassen
							break;
						}
					}
				}
			}
		}).start();
	}
}
