package logic;

public class Osoba {
	private String imie;
	private String nazwisko;
	private String numerTelefonu;
	private String email;

	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public String getNumerTelefonu() {
		return numerTelefonu;
	}
	public void setNumerTelefonu(String numerTelefonu) {
		this.numerTelefonu = numerTelefonu;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Osoba(String imie, String nazwisko, String numerTelefonu, String email){
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.numerTelefonu = numerTelefonu;
		this.email = email;
	}
}
