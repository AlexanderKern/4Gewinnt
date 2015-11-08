package com.viergewinnt.api.file;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.viergewinnt.api.common.util.Message;

/**
 * Die Klasse FileConnectionHandler liest die XML Files ein und setzt die Attribute in das Objekt Message.
 * 
 * @author Alexander Kern
 *
 */
public class FileConnectionHandler {
	/**
	 * Einlesen der XML-Files
	 * 
	 */
	
	public static Message handleXml(DocumentBuilderFactory factory, String directorypath, String serverFilename) {
		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document doc = builder.parse(directorypath + serverFilename);
			final NodeList list = doc.getElementsByTagName("content");
			final Message message = new Message();
			
			for (int i = 0; i < list.getLength(); i++) {
				final Node p = list.item(i);
				if (p.getNodeType() == Node.ELEMENT_NODE) {
					final Element content = (Element) p;
					final NodeList attributes = content.getChildNodes();
					for (int j = 0; j < attributes.getLength(); j++) {
						final Node n = attributes.item(j);
						if (n.getNodeType() == Node.ELEMENT_NODE) {
							set(message, (Element) n);
						}
					}
				}
			}
			
			return message;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Methode zum setzen der Attribute durch Switch Case.
	 * 
	 */
	
	private static void set(Message message, Element values) {
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
	}
}
