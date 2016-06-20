package logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Ksiazka {

    @FXML
    private TableView<Osoba> tableViewKsiazkaAdresowa;

    @FXML
    private TableColumn<?, ?> tableColumnImie;

    @FXML
    private TableColumn<?, ?> tableColumnNazwisko;

    @FXML
    private TableColumn<?, ?> tableColumnEmail;

    @FXML
    private TableColumn<?, ?> tableColumnNumerTelefonu;

    private ObservableList<Osoba> ksiazka = FXCollections.observableArrayList();

    @FXML
    void nowy(ActionEvent event) {
    	tableColumnImie.setCellValueFactory(new PropertyValueFactory<Osoba, String>("imie"));
    	System.out.println("Test");
    	ksiazka.add(new Osoba("Jedrzej", "Ostrowski", "531363458", "jedrzej.ostrowski@gmail.com"));
    	ksiazka.add(new Osoba("Karolina", "Sawicka", "535002665", "sawicka.karolina@poczta.onet.pl"));
    	tableViewKsiazkaAdresowa.setItems(ksiazka);
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

}
