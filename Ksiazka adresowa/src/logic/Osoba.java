package logic;

public class Osoba {
	private String imie;
	private String nazwisko;
	private String numerTelefonu;
	private String email;
	private String miasto;
	private String ulica;
	private String numerDomu;
	private String kodPocztowy;
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
	public String getMiasto() {
		return miasto;
	}
	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public String getNumerDomu() {
		return numerDomu;
	}
	public void setNumerDomu(String numerDomu) {
		this.numerDomu = numerDomu;
	}
	public String getKodPocztowy() {
		return kodPocztowy;
	}
	public void setKodPocztowy(String kodPocztowy) {
		this.kodPocztowy = kodPocztowy;
	}

	public Osoba(String imie, String nazwisko, String numerTelefonu, String email, String miasto, String ulica, String numerDomu, String kodPocztowy){
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.numerTelefonu = numerTelefonu;
		this.email = email;
		this.miasto = miasto;
		this.ulica = ulica;
		this.numerDomu = numerDomu;
		this.kodPocztowy = kodPocztowy;
	}
	public String toString(){
		return imie + " " + nazwisko;
	}
	public String toStringPlik(){
		return imie + " ;" + nazwisko + " ;" + numerTelefonu + " ;" + email + " ;"+ miasto + " ;" + ulica + " ;" + numerDomu + " ;" + kodPocztowy;
	}
}
