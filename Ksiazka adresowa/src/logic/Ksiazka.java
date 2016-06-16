package logic;

import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Ksiazka {

	@FXML
	private ListView<Osoba> listViewKontakty;
	ObservableList<Osoba> osoby = FXCollections.observableArrayList();

    @FXML
    private TextField textFieldMiasto;

    @FXML
    private TextField textFieldNumerDomu;

    @FXML
    private TextField textFieldNumerTelefonu;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldUlica;

    @FXML
    private TextField textFieldImie;

    @FXML
    private TextField textFieldNazwisko;

    @FXML
    private TextField textFieldKodPocztowy;

    public void testy(){
    	osoby.add(new Osoba("Jedrzej", "Ostrowski", "531363458", "jedrzej.ostrowski@gmail.com", "Milejewo", "Pomorska Wies", "7", "82-316"));
    	listViewKontakty.setItems(osoby);
    }
}
