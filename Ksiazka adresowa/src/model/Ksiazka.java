package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Ksiazka {
	private final static Ksiazka instance = new Ksiazka();

	public static Ksiazka getInstance(){
		return instance;
	}
	private ObservableList<Osoba> ksiazka = FXCollections.observableArrayList();

	public ObservableList<Osoba> getKsiazka(){
		return ksiazka;
	}
	public void addKsiazka(Osoba osoba){
		ksiazka.add(osoba);
	}

	public void removeKsiazka(Osoba osoba){
		ksiazka.remove(osoba);
	}
}
