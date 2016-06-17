package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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

    @FXML
    private Button buttonUsun;

    @FXML
    private Button buttonNowy;

    @FXML
    private Button buttonEdytuj;

    @FXML
    private Button buttonAnuluj;

    @FXML
    private Button buttonAktualizuj;

    @FXML
    private Button buttonDodaj;

    @FXML
    private Button buttonDodajAnuluj;

    public Osoba wybrana = null;

    public void wybierzOsobe(){
    	wybrana = listViewKontakty.getSelectionModel().getSelectedItem();
    	if(wybrana != null){
	    	wyjdzZTrybuEdycji();

	    	textFieldImie.setText(wybrana.getImie());
	    	textFieldNazwisko.setText(wybrana.getNazwisko());
	    	textFieldNumerTelefonu.setText(wybrana.getNumerTelefonu());
	    	textFieldEmail.setText(wybrana.getEmail());
	    	textFieldMiasto.setText(wybrana.getMiasto());
	    	textFieldUlica.setText(wybrana.getUlica());
	    	textFieldNumerDomu.setText(wybrana.getNumerDomu());
	    	textFieldKodPocztowy.setText(wybrana.getKodPocztowy());
	    	zablokujTextFieldy();
    	}
    }

    public void dodajTestoweWartosci(){
    	osoby.add(new Osoba("Jedrzej", "Ostrowski", "531363458", "jedrzej.ostrowski@gmail.com", "Milejewo", "Pomorska Wies", "7", "82-316"));
    	osoby.add(new Osoba("Marek", "Nowak", "663490220", "marek.nowak@gmail.com", "Elbl�g", "Grunwaldzka", "102", "82-300"));
    	osoby.add(new Osoba("Micha", "Wa�niak", "663490220", "marek.nowak@gmail.com", "Elbl�g", "Grunwaldzka", "102", "82-300"));
    	osoby.add(new Osoba("Pawel", "Mezenski", "663490220", "marek.nowak@gmail.com", "Elbl�g", "Grunwaldzka", "102", "82-300"));
    	osoby.add(new Osoba("Adam", "Malysz", "663490220", "marek.nowak@gmail.com", "Elbl�g", "Grunwaldzka", "102", "82-300"));
    	osoby.add(new Osoba("Robert", "Kubica", "663490220", "marek.nowak@gmail.com", "Elbl�g", "Grunwaldzka", "102", "82-300"));

    	listViewKontakty.setItems(osoby);
    }
    public void Importuj() throws FileNotFoundException{
    	FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pliki .adr (*.adr)", "*.adr");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(new Stage());
        if(file != null){
        	osoby.clear();
        	Scanner sc = new Scanner(file);
        	String plik;
        	plik = sc.nextLine();
        	plik = plik.substring(1,plik.length()-1);
        	String[] ksiazka = plik.split(", ");

        	for(int i=0; i<ksiazka.length; i++){
        		String[] osoba = ksiazka[i].split(";");
        		osoby.add(new Osoba(osoba[0],osoba[1],osoba[2],osoba[3],osoba[4],osoba[5],osoba[6],osoba[7]));
        	}
        	listViewKontakty.setItems(osoby);
        	sc.close();
        }
    }
    public void Eksportuj() throws IOException{
    	FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pliki .adr (*.adr)", "*.adr");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Zapisz plik");
        File file = fileChooser.showSaveDialog(new Stage());
        ArrayList<String> osoba = new ArrayList<>();
        for(int i = 0; i < osoby.size(); i++){
        	Osoba tmp = osoby.get(i);
        	String imie = tmp.getImie();
        	String nazwisko = tmp.getNazwisko();
        	String numerTelefonu = tmp.getNumerTelefonu();
        	String email = tmp.getEmail();
        	String miasto = tmp.getMiasto();
        	String ulica = tmp.getUlica();
        	String numerDomu = tmp.getNumerDomu();
        	String kodPocztowy = tmp.getKodPocztowy();
        	String osobaDane = imie + ";" + nazwisko + ";" + numerTelefonu + ";" + email + ";"+ miasto + ";" + ulica + ";" + numerDomu + ";" + kodPocztowy;
        	osoba.add(osobaDane);
        }
        if (file != null) {
        	SaveFile(osoba.toString(), file);
        }
    }
    public void Zamknij(){
    	Platform.exit();
    }

    public void Nowy(){
    	przejdzWTrybDodawania();
    	wyczyscTextFieldy();
    }

    public void Edytuj(){
    	if(wybrana != null){
    		przejdzWTrybEdycji();
    	}
    	else{

    	}
    }

    public void Usun(){
    	if(wybrana != null){
    		osoby.remove(wybrana);
    		wyczyscTextFieldy();
    		listViewKontakty.getSelectionModel().clearSelection();
    	}
    }
    public void Dodaj(){
    	wyjdzZTrybuDodawania();

    	String imie = textFieldImie.getText();
    	String nazwisko = textFieldNazwisko.getText();
    	String numerTelefonu = textFieldNumerTelefonu.getText();
    	String email = textFieldEmail.getText();
    	String miasto = textFieldMiasto.getText();
    	String ulica = textFieldUlica.getText();
    	String numerDomu = textFieldNumerDomu.getText();
    	String kodPocztowy = textFieldKodPocztowy.getText();

    	Osoba nowa = new Osoba(imie, nazwisko, numerTelefonu, email, miasto, ulica, numerDomu, kodPocztowy);
    	osoby.add(nowa);
    	listViewKontakty.setItems(osoby);
    }
    public void DodajAnuluj(){
    	wyjdzZTrybuDodawania();
    }

    public void Aktualizuj(){
    	wybrana.setImie(textFieldImie.getText());
    	wybrana.setNazwisko(textFieldNazwisko.getText());
    	wybrana.setNumerTelefonu(textFieldNumerTelefonu.getText());
    	wybrana.setEmail(textFieldEmail.getText());
    	wybrana.setMiasto(textFieldMiasto.getText());
    	wybrana.setUlica(textFieldUlica.getText());
    	wybrana.setNumerDomu(textFieldNumerDomu.getText());
    	wybrana.setKodPocztowy(textFieldKodPocztowy.getText());
    	wyjdzZTrybuEdycji();
    }

    public void Anuluj(){
    	wyjdzZTrybuEdycji();
    }

    public void zablokujTextFieldy(){
    	textFieldImie.setEditable(false);
    	textFieldNazwisko.setEditable(false);
    	textFieldNumerTelefonu.setEditable(false);
    	textFieldEmail.setEditable(false);
    	textFieldMiasto.setEditable(false);
    	textFieldUlica.setEditable(false);
    	textFieldNumerDomu.setEditable(false);
    	textFieldKodPocztowy.setEditable(false);
    }
    public void odblokujTextFieldy(){
    	textFieldImie.setEditable(true);
    	textFieldNazwisko.setEditable(true);
    	textFieldNumerTelefonu.setEditable(true);
    	textFieldEmail.setEditable(true);
    	textFieldMiasto.setEditable(true);
    	textFieldUlica.setEditable(true);
    	textFieldNumerDomu.setEditable(true);
    	textFieldKodPocztowy.setEditable(true);
    }
    public void przejdzWTrybDodawania(){
    	buttonNowy.setVisible(false);
    	buttonEdytuj.setVisible(false);
    	buttonUsun.setVisible(false);
    	buttonDodaj.setVisible(true);
    	buttonDodajAnuluj.setVisible(true);
    	odblokujTextFieldy();
    }
    public void wyjdzZTrybuDodawania(){
    	buttonNowy.setVisible(true);
    	buttonEdytuj.setVisible(true);
    	buttonUsun.setVisible(true);
    	buttonDodaj.setVisible(false);
    	buttonDodajAnuluj.setVisible(false);
    	zablokujTextFieldy();
    }
    public void przejdzWTrybEdycji(){
    	buttonNowy.setVisible(false);
    	buttonEdytuj.setVisible(false);
    	buttonUsun.setVisible(false);
    	buttonAktualizuj.setVisible(true);
    	buttonAnuluj.setVisible(true);
    	odblokujTextFieldy();
    }
    public void wyjdzZTrybuEdycji(){
    	buttonNowy.setVisible(true);
    	buttonEdytuj.setVisible(true);
    	buttonUsun.setVisible(true);
    	buttonAktualizuj.setVisible(false);
    	buttonAnuluj.setVisible(false);
    	zablokujTextFieldy();
    }
    public void wyczyscTextFieldy(){
    	textFieldImie.setText("");
    	textFieldNazwisko.setText("");
    	textFieldNumerTelefonu.setText("");
    	textFieldEmail.setText("");
    	textFieldMiasto.setText("");
    	textFieldUlica.setText("");
    	textFieldNumerDomu.setText("");
    	textFieldKodPocztowy.setText("");
    }
    private void SaveFile(String content, File file) throws IOException{
        FileWriter fileWriter = null;
        fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.close();

    }
}
