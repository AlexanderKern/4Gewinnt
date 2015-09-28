package com.viergewinnt.api.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.viergewinnt.api.common.util.Message;

public class Main {
	private static final String DIRECTORYPATH = "D:\\test\\";
	private static final String TEAM = "x";
	private static final String CLIENT_FILENAME = "spieler" + TEAM + "2server.txt";
	private static final String SERVER_FILENAME = "server2spieler" + TEAM + ".xml";
	private static FileWriter writer;

	public static void main(String[] args) {
		try {
			final File serverFile = new File(DIRECTORYPATH + SERVER_FILENAME);
			final File clientFile = new File(DIRECTORYPATH + CLIENT_FILENAME);
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			while (true) {
				if (serverFile.exists()) {

					final DocumentBuilder builder = factory.newDocumentBuilder();
					final Document doc = builder.parse(DIRECTORYPATH + SERVER_FILENAME);
					final NodeList list = doc.getElementsByTagName("content");
					for (int i = 0; i < list.getLength(); i++) {
						final Node p = list.item(i);
						if (p.getNodeType() == Node.ELEMENT_NODE) {
							final Element content = (Element) p;
							final NodeList attributes = content.getChildNodes();
							for (int j = 0; j < attributes.getLength(); j++) {
								final Node n = attributes.item(j);
								if (n.getNodeType() == Node.ELEMENT_NODE) {
									final Element values = (Element) n;
									System.out.println(values.getTagName() + " - " + values.getTextContent());
									final Message message = new Message();
									switch (values.getTagName()) {
									case "freigabe":
										message.setFreigabe(Boolean.valueOf(values.getTextContent()));
										break;

									case "satzstatus":
										message.setSatzstatus(values.getTextContent());
										break;

									case "gegnerzug":
										message.setGegnerzug(values.getTextContent());
										break;

									case "sieger":
										message.setSieger(values.getTextContent());
										break;
									}

									if (message.getFreigabe()) {
										if (!clientFile.exists()) {
											clientFile.createNewFile();
										}
										writer = new FileWriter(clientFile);
										writer.write("3");
										writer.close();
									}
								}
							}
						}
					}
					serverFile.delete();
				}
			}
		} catch (

		ParserConfigurationException e)

		{
			e.printStackTrace();
		} catch (

		SAXException e)

		{
			e.printStackTrace();
		} catch (

		IOException e)

		{
			e.printStackTrace();
		}

	}

}
