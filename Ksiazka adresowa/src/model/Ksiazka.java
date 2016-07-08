package model;
/*
 * Jest to klasa, kt�ra tworzy swoj� instancj� i w niej przechowuje kontakty
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Ksiazka {
	private final static Ksiazka instance = new Ksiazka();

	public static Ksiazka getInstance(){
		return instance;
	}
	private Osoba edytowanaOsoba = null; // Zmienna, w kt�rej przechowuj� wybran� osob� poprzez klikni�cie w list�.

	private ObservableList<Osoba> ksiazka = FXCollections.observableArrayList(); // Lista przechowuj�ca osoby

	public ObservableList<Osoba> getKsiazka(){
		return ksiazka;
	}

	public Osoba getEdytowanaOsoba() {
		return edytowanaOsoba;
	}
	public void setEdytowanaOsoba(Osoba edytowanaOsoba) {
		this.edytowanaOsoba = edytowanaOsoba;
	}

	public void addKsiazka(Osoba osoba){
		ksiazka.add(osoba);
	}

	public void removeKsiazka(Osoba osoba){
		ksiazka.remove(osoba);
	}
	public void editKsiazka(Osoba osoba, String imie, String nazwisko, String numerTelefonu, String email){
		// Metoda kt�ra usuwa osob�, a w jej miejsce wstawia now� - u�ywana w celu edycji.
		int indeksOsobyEdytowanej = ksiazka.indexOf(osoba);
		ksiazka.remove(osoba);
		ksiazka.add(indeksOsobyEdytowanej, new Osoba(imie, nazwisko, numerTelefonu, email));
	}
}
