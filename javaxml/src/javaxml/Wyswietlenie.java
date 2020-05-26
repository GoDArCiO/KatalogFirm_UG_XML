package javaxml;

public class Wyswietlenie implements Comparable< Wyswietlenie >{
	private String nip;
	private String dStart;
	private String dStop;
	private String koszt;
	public Wyswietlenie(String nip, String dStart, String dStop, String koszt) {
		super();
		this.nip = nip;
		this.dStart = dStart;
		this.dStop = dStop;
		this.koszt = koszt;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	@Override
	public String toString() {
		return "Wyswietlenie [nip=" + nip + ", dStart=" + dStart + ", dStop=" + dStop + ", koszt=" + koszt + "]";
	}
	public String getdStart() {
		return dStart;
	}
	public void setdStart(String dStart) {
		this.dStart = dStart;
	}
	public String getdStop() {
		return dStop;
	}
	public void setdStop(String dStop) {
		this.dStop = dStop;
	}
	public String getKoszt() {
		return koszt;
	}
	public void setKoszt(String koszt) {
		this.koszt = koszt;
	}
	@Override
	public int compareTo(Wyswietlenie o) {
		return this.getKoszt().compareTo(o.getKoszt());
	}
}
