package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Ksiazka;
import model.Osoba;

public class ContactAddController {
	@FXML
	private MainWindowController mainWindowController = new MainWindowController();

	@FXML
    private Button buttonDodaj;

	@FXML
    private Button buttonAnuluj;

    @FXML
    private TextField textFieldNumerTelefonu;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldImie;

    @FXML
    private TextField textFieldNazwisko;

	private Stage stage = null;

    @FXML
    private void zatwierdz(ActionEvent event) {
    	Ksiazka.getInstance().addKsiazka(new Osoba(textFieldImie.getText(), textFieldNazwisko.getText(), textFieldNumerTelefonu.getText(), textFieldEmail.getText()));
    	stage = (Stage) buttonDodaj.getScene().getWindow();
    	stage.close();
    }

    @FXML
    private void anuluj(ActionEvent event) {
    	stage = (Stage) buttonAnuluj.getScene().getWindow();
    	stage.close();
    }

}
