package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.sql.rowset.spi.XmlWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Ksiazka;
import model.Osoba;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		importContacts();
		Parent parent = (Parent)FXMLLoader.load(getClass().getResource("/view/MainWindow.fxml"));
		Scene scene = new Scene(parent);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Ksi¹¿ka adresowa");
		primaryStage.show();
	}
	public void stop(){
		exportContacts();
	}

	public static void main(String[] args) {
		launch(args);
	}
	public void importContacts(){
		String fileName = "res/contacts.xml";
		File file = new File(fileName);
		boolean fileExists = file.exists();
		if(!fileExists){
			try {
				fileExists = file.createNewFile();
			}
			catch(IOException e){
				System.out.println("Nie uda³o siê utworzyæ pliku.");
			}
		}
	}
	public void exportContacts(){
		String fileName = "res/contacts.xml";
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));){
			StringWriter stringWriter = new StringWriter();

			XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);

			xMLStreamWriter.writeStartDocument();
			xMLStreamWriter.writeStartElement("contacts");

			for(Osoba contact: Ksiazka.getInstance().getKsiazka()){
				xMLStreamWriter.writeStartElement("contact");

				xMLStreamWriter.writeStartElement("firstname");
				xMLStreamWriter.writeCharacters(contact.getImie());
				xMLStreamWriter.writeEndElement();

				xMLStreamWriter.writeStartElement("lastname");
				xMLStreamWriter.writeCharacters(contact.getNazwisko());
				xMLStreamWriter.writeEndElement();

				xMLStreamWriter.writeStartElement("phone");
				xMLStreamWriter.writeCharacters(contact.getNumerTelefonu());
				xMLStreamWriter.writeEndElement();

				xMLStreamWriter.writeStartElement("email");
				xMLStreamWriter.writeCharacters(contact.getEmail());
				xMLStreamWriter.writeEndElement();

				xMLStreamWriter.writeEndElement();
			}

			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeEndDocument();

			xMLStreamWriter.flush();
			xMLStreamWriter.close();

			String xmlString = stringWriter.getBuffer().toString();
			stringWriter.close();

			System.out.println(xmlString);
			writer.write(xmlString);

		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e){
			System.err.println("Nie udalo sie zapisac do pliku");
		}

	}
}
