package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML
    public void nowy() {
    	dodajOsobe("Jan", "Nowak", "111111111", "jannowak@wp.pl");
    	dodajOsobe("Maria", "Kowalska", "999999999", "mariakowalska@wp.pl");
    }

    @FXML
    private void otworz(ActionEvent event) {
    	tableViewKsiazkaAdresowa.setItems(Ksiazka.getInstance().getKsiazka());
    }

    @FXML
    private void zapisz(ActionEvent event) {

    }

    @FXML
    private void dodaj(ActionEvent event) throws IOException {
    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/view/ContactAdd.fxml"));
    	Scene dodajOsobe = new Scene(parent);
    	Stage scenaDodawania = new Stage();
    	scenaDodawania.setScene(dodajOsobe);
    	scenaDodawania.setTitle("Ksi¹¿ka adresowa - Dodaj");
    	scenaDodawania.setResizable(false);
    	scenaDodawania.show();
    }

    @FXML
    private void edytuj(ActionEvent event) {

    }

    @FXML
    private void usun(ActionEvent event) {
    	wybranaOsoba = tableViewKsiazkaAdresowa.getSelectionModel().getSelectedItem();
    	Ksiazka.getInstance().removeKsiazka(wybranaOsoba);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
			inicjalizacjaListy();
		}

	private void inicjalizacjaListy(){
		tableColumnImie = new TableColumn<Object, Object>("Imiê: ");
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

	public void dodajOsobe(String imie, String nazwisko, String numerTelefonu, String email){
		Ksiazka.getInstance().addKsiazka(new Osoba(imie, nazwisko, numerTelefonu, email));
		tableViewKsiazkaAdresowa.setItems(Ksiazka.getInstance().getKsiazka());
	}

}
