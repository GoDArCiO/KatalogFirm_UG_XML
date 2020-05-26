package javaxml;

public class Firma {
	private String nip;
	private String nazwa;
	private String iban;
	private String telefon;
	private String mail;
	private String pesel;
	private String dowod_osobisty;
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	public String getDowod_osobisty() {
		return dowod_osobisty;
	}
	public void setDowod_osobisty(String dowod_osobisty) {
		this.dowod_osobisty = dowod_osobisty;
	}
	public Firma(String nip, String nazwa, String iban, String telefon, String mail, String pesel,
			String dowod_osobisty) {
		super();
		this.nip = nip;
		this.nazwa = nazwa;
		this.iban = iban;
		this.telefon = telefon;
		this.mail = mail;
		this.pesel = pesel;
		this.dowod_osobisty = dowod_osobisty;
	}
	@Override
	public String toString() {
		return "Firma [nip=" + nip + ", nazwa=" + nazwa + ", iban=" + iban + ", telefon=" + telefon + ", mail=" + mail
				+ ", pesel=" + pesel + ", dowod_osobisty=" + dowod_osobisty + "]";
	}
}
