package lists;

public class Let {
	private int id_let;
	private String naziv;
	private String kompanija;
	private int id_aerodrom;
	private int brojputnika;
	private int terminal;
	
	
	public Let(int id_let, String naziv, String kompanija, int id_aerodrom, int brojputnika, int terminal) {
		super();
		this.id_let = id_let;
		this.naziv = naziv;
		this.kompanija = kompanija;
		this.id_aerodrom = id_aerodrom;
		this.brojputnika = brojputnika;
		this.terminal = terminal;
	}
	public int getId_let() {
		return id_let;
	}
	public void setId_let(int id_let) {
		this.id_let = id_let;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getKompanija() {
		return kompanija;
	}
	public void setKompanija(String kompanija) {
		this.kompanija = kompanija;
	}
	public int getId_aerodrom() {
		return id_aerodrom;
	}
	public void setId_aerodrom(int id_aerodrom) {
		this.id_aerodrom = id_aerodrom;
	}
	public int getBrojputnika() {
		return brojputnika;
	}
	public void setBrojputnika(int brojputnika) {
		this.brojputnika = brojputnika;
	}
	public int getTerminal() {
		return terminal;
	}
	public void setTerminal(int terminal) {
		this.terminal = terminal;
	}
	@Override
	public String toString() {
		return "Let [id_let=" + id_let + ", naziv=" + naziv + ", kompanija=" + kompanija + ", id_aerodrom="
				+ id_aerodrom + ", brojputnika=" + brojputnika + ", terminal=" + terminal + "]";
	}

	
	
}
