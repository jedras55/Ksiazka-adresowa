package logic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Ksiazka implements Initializable{

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

    private ObservableList<Osoba> ksiazka = FXCollections.observableArrayList();

    @FXML
    void nowy(ActionEvent event) {
    	ksiazka.add(new Osoba("Jedrzej", "Ostrowski", "531363458", "jedrzej.ostrowski@gmail.com"));
    	ksiazka.add(new Osoba("Karolina", "Sawicka", "535002665", "sawicka.karolina@poczta.onet.pl"));
    	tableViewKsiazkaAdresowa.setItems(ksiazka);
    	System.out.println("Test");
    }

    @FXML
    void otworz(ActionEvent event) {

    }

    @FXML
    void zapisz(ActionEvent event) {

    }

    @FXML
    void dodaj(ActionEvent event) {

    }

    @FXML
    void edytuj(ActionEvent event) {

    }

    @FXML
    void usun(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableColumnImie = new TableColumn("Imiê: ");
    	tableColumnNazwisko = new TableColumn("Nazwisko: ");
    	tableColumnEmail = new TableColumn("E-mail: ");
    	tableColumnNumerTelefonu = new TableColumn("Numer telefonu: ");
    	tableColumnImie.setCellValueFactory(new PropertyValueFactory<Osoba, String>("imie"));
    	tableColumnNazwisko.setCellValueFactory(new PropertyValueFactory<Osoba, String>("nazwisko"));
    	tableColumnEmail.setCellValueFactory(new PropertyValueFactory<Osoba, String>("email"));
    	tableColumnNumerTelefonu.setCellValueFactory(new PropertyValueFactory<Osoba, String>("numerTelefonu"));
    	tableViewKsiazkaAdresowa.getColumns().addAll(tableColumnImie, tableColumnNazwisko, tableColumnEmail, tableColumnNumerTelefonu);
	}

}
