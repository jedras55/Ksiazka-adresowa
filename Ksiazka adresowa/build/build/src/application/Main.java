package application;

import java.io.IOException;

import controller.XMLFileController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	private String fileName = "res/contacts.xml"; // Œcie¿ka do wewnêtrznego pliku, który przechowuje kontakty
	@Override
	public void start(Stage primaryStage) throws IOException {
		XMLFileController xMLFileController = new XMLFileController(fileName);
		xMLFileController.importContacts(); // Przy starcie programu importujê z pliku xml listê kontaktów
		Parent parent = (Parent)FXMLLoader.load(getClass().getResource("/view/MainWindow.fxml"));
		Scene scene = new Scene(parent);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Ksi¹¿ka adresowa");
		primaryStage.show();
	}
	public void stop(){
		XMLFileController xMLFileController = new XMLFileController(fileName);
		xMLFileController.exportContacts(); // Przy zamykaniu programu eksportujê z ksi¹¿ki kontakty do pliku XML
	}

	public static void main(String[] args) {
		launch(args);
	}
}
