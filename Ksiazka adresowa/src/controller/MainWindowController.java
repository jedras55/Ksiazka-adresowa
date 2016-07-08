package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Ksiazka;
import model.Osoba;

public class MainWindowController implements Initializable{

	private Osoba wybranaOsoba = null;

    @FXML
    private TableView<Osoba> tableViewKsiazkaAdresowa = new TableView<Osoba>();

    @FXML
    private TableColumn tableColumnImie;

    @FXML
    private TableColumn tableColumnNazwisko;

    @FXML
    private TableColumn tableColumnEmail;

    @FXML
    private TableColumn tableColumnNumerTelefonu;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
			inicjalizacjaListy();
		}

	private void inicjalizacjaListy(){
		tableColumnImie = new TableColumn<Object, Object>("Imi�: ");
    	tableColumnNazwisko = new TableColumn("Nazwisko: ");
    	tableColumnEmail = new TableColumn("E-mail: ");
    	tableColumnNumerTelefonu = new TableColumn("Numer telefonu: ");
    	tableColumnImie.setCellValueFactory(new PropertyValueFactory<Osoba, String>("imie"));
    	tableColumnNazwisko.setCellValueFactory(new PropertyValueFactory<Osoba, String>("nazwisko"));
    	tableColumnEmail.setCellValueFactory(new PropertyValueFactory<Osoba, String>("email"));
    	tableColumnNumerTelefonu.setCellValueFactory(new PropertyValueFactory<Osoba, String>("numerTelefonu"));
    	tableViewKsiazkaAdresowa.getColumns().addAll(tableColumnImie, tableColumnNazwisko, tableColumnEmail, tableColumnNumerTelefonu);
    	tableViewKsiazkaAdresowa.setItems(Ksiazka.getInstance().getKsiazka());
	}

    @FXML
    public void nowy() { // Wy�ietla alert, przy potwierdzeniu czy�ci list� - tworzy now�
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Nowa lista");
    	alert.setHeaderText("Czy wyczy�ci� list�?");
    	alert.setContentText("Tworz�c now� list�, stara zostanie wyczysczona.");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	   Ksiazka.getInstance().getKsiazka().clear();
    	}
    }

    @FXML
    private void otworz(ActionEvent event) { // Otwiera plik .xml z file choosera, przepisuje z niego kontakty do ksi��ki
    	FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pliki .xml (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(new Stage());
        if(file != null){
        	XMLFileController xMLFileController = new XMLFileController(file.getPath());
        	xMLFileController.importContacts();
        }
    }

    @FXML
    private void zapisz(ActionEvent event) { // Wpisuje kontakty do pluku .xml wybranego z file choosera
    	FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pliki .xml (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Zapisz plik");
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
        	XMLFileController xMLFileController = new XMLFileController(file.getPath());
        	xMLFileController.exportContacts();
        }
    }

    @FXML
    private void dodaj(ActionEvent event) throws IOException { // Otwiera okno tworzenia kontaktu
    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/view/ContactAdd.fxml"));
    	Scene dodajOsobe = new Scene(parent);
    	Stage scenaDodawania = new Stage();
    	scenaDodawania.setScene(dodajOsobe);
    	scenaDodawania.setTitle("Ksi��ka adresowa - Dodaj");
    	scenaDodawania.setResizable(false);
    	scenaDodawania.show();
    }

    @FXML
    private void edytuj(ActionEvent event) throws IOException { // Otwiera okno edycji osoby wybranej z listy
    	wybranaOsoba = tableViewKsiazkaAdresowa.getSelectionModel().getSelectedItem();
    	if(wybranaOsoba != null){
	    	Ksiazka.getInstance().setEdytowanaOsoba(wybranaOsoba);
	    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/view/ContactEdit.fxml"));
	    	Scene edytujOsobe = new Scene(parent);
	    	Stage scenaEdycji = new Stage();
	    	scenaEdycji.setScene(edytujOsobe);
	    	scenaEdycji.setTitle("Ksi��ka adresowa - Edytuj");
	    	scenaEdycji.setResizable(false);
	    	scenaEdycji.show();
    	}

    }

    @FXML
    private void usun(ActionEvent event) { // Usuwa z listy wybran� osob�
    	wybranaOsoba = tableViewKsiazkaAdresowa.getSelectionModel().getSelectedItem();
    	Ksiazka.getInstance().removeKsiazka(wybranaOsoba);
    }

}
