package com.viergewinnt.api.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import com.viergewinnt.api.common.util.Message;

public class FileMain {
	private static final String DIRECTORYPATH = "D:\\test\\";
	private static final String TEAM = "x";
	private static final String CLIENT_FILENAME = "spieler" + TEAM + "2server.txt";
	private static final String SERVER_FILENAME = "server2spieler" + TEAM + ".xml";
	private static FileWriter writer;

	public static void main(String[] args) throws IOException {

		final File serverFile = new File(DIRECTORYPATH + SERVER_FILENAME);
		final File clientFile = new File(DIRECTORYPATH + CLIENT_FILENAME);
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		while (true) {
			if (serverFile.exists()) {

				Message message = FileConnectionHandler.handleXml(factory, DIRECTORYPATH, SERVER_FILENAME);

				if (message.getFreigabe()) {
					try {
						if (!clientFile.exists()) {
							clientFile.createNewFile();
							writer = new FileWriter(clientFile);
							writer.write("3");
							writer.close();
							//cf.setStone();
							serverFile.delete();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
