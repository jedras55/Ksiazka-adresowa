package controller;
/*
 * Klasa odpowiedzialna za importowanie i eksportowanie kontaktów miêdzy plikami, do wskazanego przy tworzeniu obiektu pliku
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Ksiazka;
import model.Osoba;

public class XMLFileController {
	String fileName;
	File file;
	public XMLFileController(String fileName){
		this.fileName = fileName;
		this.file = new File(fileName);
	}
	public void importContacts(){
		Ksiazka.getInstance().getKsiazka().clear();
		boolean fileExists = file.exists();
		if(!fileExists){
			try {
				fileExists = file.createNewFile();
			}
			catch(IOException e){
				System.out.println("Nie uda³o siê utworzyæ pliku.");
			}
		} else{
			boolean bFirstName = false;
		    boolean bLastName = false;
		    boolean bPhone = false;
		    boolean bEmail = false;

		    String firstName = "";
		    String lastName = "";
		    String phone = "";
		    String email = "";
			try{
				XMLInputFactory factory = XMLInputFactory.newInstance();
				XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(fileName));

				while (eventReader.hasNext()){
					XMLEvent event = eventReader.nextEvent();
					switch(event.getEventType()){
	                  case XMLStreamConstants.START_ELEMENT:
	                     StartElement startElement = event.asStartElement();
	                     String qName = startElement.getName().getLocalPart();
	                     if (qName.equalsIgnoreCase("student")) {
	                        System.out.println("Start Element : conctact");
	                        Iterator<Attribute> attributes = startElement.getAttributes();
	                        String rollNo = attributes.next().getValue();
	                        System.out.println("Roll No : " + rollNo);
	                     } else if (qName.equalsIgnoreCase("firstname")) {
	                        bFirstName = true;
	                     } else if (qName.equalsIgnoreCase("lastname")) {
	                        bLastName = true;
	                     } else if (qName.equalsIgnoreCase("phone")) {
	                        bPhone = true;
	                     }
	                     else if (qName.equalsIgnoreCase("email")) {
	                        bEmail = true;
	                     }
	                     break;
	                  case XMLStreamConstants.CHARACTERS:
	                     Characters characters = event.asCharacters();
	                     if(bFirstName){
	                        firstName = characters.getData();
	                        bFirstName = false;
	                     }
	                     if(bLastName){
	                        lastName = characters.getData();
	                        bLastName = false;
	                     }
	                     if(bPhone){
	                        phone = characters.getData();
	                        bPhone = false;
	                     }
	                     if(bEmail){
	                        email = characters.getData();
	                        bEmail = false;
	                     }

	                     break;
	                  case  XMLStreamConstants.END_ELEMENT:
	                     EndElement endElement = event.asEndElement();
	                     if(endElement.getName().getLocalPart().equalsIgnoreCase("contact")){
	                        Ksiazka.getInstance().addKsiazka(new Osoba(firstName, lastName, phone, email));
	                        firstName = "";
	                        lastName = "";
	                        phone = "";
	                        email = "";
	                     }
	                     break;
	               }
				}
			}
			catch(XMLStreamException e){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("B³¹d");
				alert.setHeaderText("Plik uszkodzony");
				alert.setContentText("Wygl¹da na to, ¿e Twój plik z kontaktami jest uszkodzony lub niepoprawny");

				alert.showAndWait();
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
			}
		}
	}


	public void exportContacts(){
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));){
			PrintWriter printwriter = new PrintWriter(file);
			printwriter.write("");
			printwriter.close();

			StringWriter stringWriter = new StringWriter();

			XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);

			xMLStreamWriter.writeStartDocument();
			xMLStreamWriter.writeStartElement("contacts");

			for(Osoba contact: Ksiazka.getInstance().getKsiazka()){
				xMLStreamWriter.writeStartElement("contact");

				if(!contact.getImie().isEmpty()){
					xMLStreamWriter.writeStartElement("firstname");
					xMLStreamWriter.writeCharacters(contact.getImie());
					xMLStreamWriter.writeEndElement();
				}

				if(!contact.getNazwisko().isEmpty()){
					xMLStreamWriter.writeStartElement("lastname");
					xMLStreamWriter.writeCharacters(contact.getNazwisko());
					xMLStreamWriter.writeEndElement();
				}

				if(!contact.getNumerTelefonu().isEmpty()){
					xMLStreamWriter.writeStartElement("phone");
					xMLStreamWriter.writeCharacters(contact.getNumerTelefonu());
					xMLStreamWriter.writeEndElement();
				}

				if(!contact.getEmail().isEmpty()){
					//System.out.println(contact.getEmail().);
					xMLStreamWriter.writeStartElement("email");
					xMLStreamWriter.writeCharacters(contact.getEmail());
					xMLStreamWriter.writeEndElement();
				}

				xMLStreamWriter.writeEndElement();
			}

			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeEndDocument();

			xMLStreamWriter.flush();
			xMLStreamWriter.close();

			String xmlString = stringWriter.getBuffer().toString();
			stringWriter.close();

			writer.write(xmlString);

		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

	}
}
