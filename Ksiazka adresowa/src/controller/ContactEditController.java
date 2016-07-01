package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Ksiazka;
import model.Osoba;

public class ContactEditController implements Initializable {

    @FXML
    private TextField textFieldNumerTelefonu;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private Button buttonEdytuj;

    @FXML
    private TextField textFieldImie;

    @FXML
    private Button buttonAnuluj;

    @FXML
    private TextField textFieldNazwisko;

    private Stage stage = null;

    @FXML
    void edytuj(ActionEvent event) {
		Ksiazka.getInstance().editKsiazka(Ksiazka.getInstance().getEdytowanaOsoba(), textFieldImie.getText(), textFieldNazwisko.getText(), textFieldNumerTelefonu.getText(), textFieldEmail.getText());
    	stage = (Stage) buttonEdytuj.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void anuluj(ActionEvent event) {
    	stage = (Stage) buttonAnuluj.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Osoba wybranaOsoba = Ksiazka.getInstance().getEdytowanaOsoba();
		textFieldImie.setText(wybranaOsoba.getImie());
		textFieldNazwisko.setText(wybranaOsoba.getNazwisko());
		textFieldNumerTelefonu.setText(wybranaOsoba.getNumerTelefonu());
		textFieldEmail.setText(wybranaOsoba.getNumerTelefonu());

	}

}