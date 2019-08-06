package lists;

public class Putnik {

	private int id_putnik;
	private String ime;
	private String prezime;
	private String pol;
	private int godine;
	private String grad;
	private String ulica;
	private String telefon;
	private String klasa;
	private int id_let;

	public Putnik(int id_putnik, String ime, String prezime, String pol, int godine, String grad, String ulica,
			String telefon, String klasa, int id_let) {
		super();
		this.id_putnik = id_putnik;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.godine = godine;
		this.grad = grad;
		this.ulica = ulica;
		this.telefon = telefon;
		this.klasa = klasa;
		this.id_let = id_let;
	}

	public int getId_putnik() {
		return id_putnik;
	}

	public void setId_putnik(int id_putnik) {
		this.id_putnik = id_putnik;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public int getGodine() {
		return godine;
	}

	public void setGodine(int godine) {
		this.godine = godine;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getKlasa() {
		return klasa;
	}

	public void setKlasa(String klasa) {
		this.klasa = klasa;
	}

	public int getId_let() {
		return id_let;
	}

	public void setId_let(int id_let) {
		this.id_let = id_let;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Putnik) {
			Putnik s = (Putnik) obj;
			if (s.getId_let() == (id_let)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Putnik [id_putnik=" + id_putnik + ", ime=" + ime + ", prezime=" + prezime + ", pol=" + pol + ", godine="
				+ godine + ", grad=" + grad + ", ulica=" + ulica + ", telefon=" + telefon + ", klasa=" + klasa
				+ ", id_let=" + id_let + "]";
	}

}
