package logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Ksiazka {

    @FXML
    private TableView<?> tableViewKsiazkaAdresowa;

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
