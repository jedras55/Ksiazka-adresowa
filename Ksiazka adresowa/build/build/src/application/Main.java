package application;

import java.io.IOException;

import controller.XMLFileController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	private String fileName = "res/contacts.xml"; // �cie�ka do wewn�trznego pliku, kt�ry przechowuje kontakty
	@Override
	public void start(Stage primaryStage) throws IOException {
		XMLFileController xMLFileController = new XMLFileController(fileName);
		xMLFileController.importContacts(); // Przy starcie programu importuj� z pliku xml list� kontakt�w
		Parent parent = (Parent)FXMLLoader.load(getClass().getResource("/view/MainWindow.fxml"));
		Scene scene = new Scene(parent);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Ksi��ka adresowa");
		primaryStage.show();
	}
	public void stop(){
		XMLFileController xMLFileController = new XMLFileController(fileName);
		xMLFileController.exportContacts(); // Przy zamykaniu programu eksportuj� z ksi��ki kontakty do pliku XML
	}

	public static void main(String[] args) {
		launch(args);
	}
}
