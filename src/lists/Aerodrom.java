package lists;

public class Aerodrom {
	private int id_aerodrom;
	private String naziv;	
	private String grad;
	private String ulica;
	private String telefon;
	public Aerodrom(int id_aerodrom, String naziv, String grad, String ulica, String telefon) {
		super();
		this.id_aerodrom = id_aerodrom;
		this.naziv = naziv;
		this.grad = grad;
		this.ulica = ulica;
		this.telefon = telefon;
	}
	
	
	public int getId_aerodrom() {
		return id_aerodrom;
	}


	public void setId_aerodrom(int id_aerodrom) {
		this.id_aerodrom = id_aerodrom;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
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


	@Override
	public String toString() {
		return "Aerodrom [id_aerodrom=" + id_aerodrom + ", naziv=" + naziv + ", grad=" + grad + ", ulica=" + ulica
				+ ", telefon=" + telefon + "]";
	}

	
	
}
