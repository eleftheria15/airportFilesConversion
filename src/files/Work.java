package files;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import database_conn.DataBConn;
import export.JSON;
import export.PDF;
import export.XML;
import lists.Aerodrom;

import lists.Let;
import lists.Putnik;

public class Work {
	private List<Putnik> putnici = new ArrayList<>();
	private List<Aerodrom> aerodromi = new ArrayList<>();
	private List<Let> letovi = new ArrayList<>();

	private ReadExcel read;

	public Work() {
		super();
	}

	public String getPutnici(List<Putnik> list, String sql) {
		if (DataBConn.open()) {
			try {
				ResultSet set = DataBConn.getDataFromSQL(sql);
				while (set.next()) {
					list.add(new Putnik(set.getInt("id_putnik"), set.getString("ime"), set.getString("prezime"),
							set.getString("pol"), set.getInt("godina"), set.getString("grad"), set.getString("ulica"),
							set.getString("telefon"), set.getString("klasa"), set.getInt("id_let")));
				}
				DataBConn.close();
				return "true";
			} catch (SQLException e) {
				return e.getMessage();
			}
		}
		return "Error while fetching data from database";
	}

	public DefaultTableModel getDTMPutnici(String sql) {
		Object[][] putnici = getPutniciForTable(sql);

		Object[] columnNames = { "Id putnika", "Ime", "Prezime", "Pol", "Godina", "Grad", "Ulica", "Telefon", "Klasa",
				"Id leta" };

		DefaultTableModel dtm = new DefaultTableModel(putnici, columnNames);
		return dtm;
	}

	public Object[][] getPutniciForTable(String sql) {

		getPutnici(this.putnici, sql);
		Object[][] objects = new Object[putnici.size()][9];
		int i = 0;
		for (Putnik s : putnici) {
			Object[] o = { s.getId_putnik(), s.getIme(), s.getPrezime(), s.getPol(), s.getGodine(), s.getGrad(),
					s.getUlica(), s.getTelefon(), s.getKlasa(), s.getId_let() };
			objects[i] = o;
			i++;
		}
		return objects;
	}

	public boolean fillComboBox(JComboBox<String> box, String table) {
		String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'bazaxml' AND TABLE_NAME = '"
				+ table + "' ";
		if (DataBConn.open()) {
			try {
				ResultSet set = DataBConn.getDataFromSQL(sql);
				box.removeAllItems();
				while (set.next()) {
					box.addItem(set.getString("COLUMN_NAME"));
				}
				DataBConn.close();
				box.repaint();
				return true;
			} catch (SQLException e) {
				return false;
			}
		}
		return false;
	}

	public DefaultTableModel getDTMBAir() {
		String sql = "SELECT * FROM aerodrom";
		return getDTMAirport(sql);
	}

	public DefaultTableModel getDTMAirport(String sql) {
		Object[][] donations = getAirportForTable(sql);

		Object[] columnNames = { "airport_ID", "Name", "Town", "Street", "Phone" };

		DefaultTableModel dtm = new DefaultTableModel(donations, columnNames);
		return dtm;
	}

	public Object[][] getAirportForTable(String sql) {
		getAirport(this.aerodromi, sql);
		Object[][] objects = new Object[aerodromi.size()][5];
		int i = 0;
		for (Aerodrom s : aerodromi) {
			Object[] o = { s.getId_aerodrom(), s.getNaziv(), s.getGrad(), s.getUlica(), s.getTelefon() };
			objects[i] = o;
			i++;
		}
		return objects;
	}

	public String getAirport(List<Aerodrom> list, String sql) {
		if (DataBConn.open()) {
			try {
				ResultSet set = DataBConn.getDataFromSQL(sql);
				while (set.next()) {
					list.add(new Aerodrom(set.getInt("id_aerodrom"), set.getString("naziv"), set.getString("grad"),
							set.getString("ulica"), set.getString("telefon")));
				}
				DataBConn.close();
				return "true";
			} catch (SQLException e) {
				return e.getMessage();
			}
		}
		return "Error while fetching data from database";
	}

	public DefaultTableModel getDTMFli() {
		String sql = "SELECT * FROM let";
		return getDTMFligt(sql);
	}

	public DefaultTableModel getDTMFligt(String sql) {
		Object[][] donations = getFligtForTable(sql);

		Object[] columnNames = { "flight_ID", "Name", "Company", "NoOfPassanger", "Terminal" };

		DefaultTableModel dtm = new DefaultTableModel(donations, columnNames);
		return dtm;
	}

	public Object[][] getFligtForTable(String sql) {
		getFligt(this.letovi, sql);
		Object[][] objects = new Object[letovi.size()][6];
		int i = 0;
		for (Let s : letovi) {
			Object[] o = { s.getId_let(), s.getNaziv(), s.getKompanija(), s.getId_aerodrom(), s.getBrojputnika(),
					s.getTerminal() };
			objects[i] = o;
			i++;
		}
		return objects;
	}

	public String getFligt(List<Let> list, String sql) {
		if (DataBConn.open()) {
			try {
				ResultSet set = DataBConn.getDataFromSQL(sql);
				while (set.next()) {
					list.add(new Let(set.getInt("id_let"), set.getString("naziv"), set.getString("kompanija"),
							set.getInt("id_aerodrom"), set.getInt("brojputnika"), set.getInt("terminal")));
				}
				DataBConn.close();
				return "true";
			} catch (SQLException e) {
				return e.getMessage();
			}
		}
		return "Error while fetching data from database";
	}

	public String deleteAllFromDataBase() {

		String putnik = DataBConn.deleteDataFromTable("putnik");
		String aerodrom = DataBConn.deleteDataFromTable("aerodrom");
		String let = DataBConn.deleteDataFromTable("let");

		if (!putnik.equals("true")) {
			return putnik;
		}
		if (!aerodrom.equals("true")) {
			return aerodrom;
		}
		if (!let.equals("true")) {
			return let;
		}

		return "Successfully deleted all records from all tables";
	}

	public String getData(String path) {

		this.read = new ReadExcel(path);

		String warnings = "";
		String checkRec = read.readPutnici(putnici);
		if (!checkRec.equals("")) {
			warnings += checkRec + "\n";
		}
		String checkDon = read.readAirport(aerodromi);
		if (!checkDon.equals("")) {
			warnings += checkDon + "\n";
		}
		String checkBB = read.readLet(letovi);
		if (!checkBB.equals("")) {
			warnings += checkBB + "\n";
		}

		if (warnings.equals("")) {
			return "true";
		} else {
			return warnings;
		}

	}

	public String insertLet() {

		if (aerodromi.size() != 0) {
			if (DataBConn.open()) {
				for (Let l : letovi) {
					String sql = "INSERT INTO let  VALUES (" + l.getId_let() + ",'" + l.getNaziv() + "','"
							+ l.getKompanija() + "'," + l.getId_aerodrom() + "," + l.getBrojputnika() + ","
							+ l.getTerminal() + ")";
					System.out.println(sql);
					if (!DataBConn.insertRecord(sql).equals("true")) {
						return DataBConn.insertRecord(sql);
					}
				}
				DataBConn.close();
				return "Successfully inserted";
			} else {
				return "Error connecting to database";
			}
		} else {
			return "Failed to insert let. No data available.";
		}

	}

	public String insertAerodromi() {

		if (aerodromi.size() != 0) {
			if (DataBConn.open()) {
				for (Aerodrom a : aerodromi) {
					String sql = "INSERT INTO aerodrom  VALUES (" + a.getId_aerodrom() + ",'" + a.getNaziv() + "','"
							+ a.getGrad() + "','" + a.getUlica() + "','" + a.getTelefon() + "')";
					System.out.println(sql);
					if (!DataBConn.insertRecord(sql).equals("true")) {
						return DataBConn.insertRecord(sql);
					}
				}
				DataBConn.close();
				return "Successfully inserted";
			} else {
				return "Error connecting to database";
			}
		} else {
			return "Failed to insert aerodrom. No data available.";
		}

	}

	public String insertPutnik() {

		if (putnici.size() != 0) {
			if (DataBConn.open()) {
				for (Putnik p : putnici) {
					String sql = "INSERT INTO putnik  VALUES (" + p.getId_putnik() + ",'" + p.getIme() + "','"
							+ p.getPrezime() + "','" + p.getPol() + "'," + p.getGodine() + ",'" + p.getGrad() + "','"
							+ p.getUlica() + "','" + p.getTelefon() + "','" + p.getKlasa() + "'," + p.getId_let() + ")";
					System.out.println(sql);
					if (!DataBConn.insertRecord(sql).equals("true")) {
						return DataBConn.insertRecord(sql);
					}
				}
				DataBConn.close();
				return "Successfully inserted";
			} else {
				return "Error connecting to database";
			}
		} else {
			return "Failed to insert putnik. No data available.";
		}

	}

	public String validateKeys() {
		String warning = "";
		for (Putnik p : putnici) {
			Boolean check = false;
			for (Let l : letovi) {
				if (p.getId_let() == l.getId_let()) {
					check = true;
				}
			}
			if (check == false) {
				warning += "Freign key validation, putnik ID " + p.getId_putnik() + ", unknown let " + p.getId_let()
						+ "\n";
			}
		}
		for (Let l : letovi) {
			Boolean check = false;
			for (Aerodrom a : aerodromi) {
				if (l.getId_aerodrom() == a.getId_aerodrom()) {
					check = true;
				}
			}
			if (check == false) {
				warning += "Freign key validation, let ID " + l.getId_let() + ", unknown aerodrom " + l.getId_aerodrom()
						+ "\n";
			}
		}
		if (warning.equals("")) {
			return "true";
		} else {
			return warning;
		}
	}

	public String validateAndFillData(String path) {
		String validation = getData(path);
		String vlaKeys = validateKeys();
		if (validation.equals("true")) {
			if (vlaKeys.equals("true")) {
				return "true";
			} else {
				return vlaKeys;
			}
		} else {
			return validation;
		}
	}

	public <T> boolean exportToJSON(List<T> list, String fileName) {
		if (list.get(0) != null) {
			if (list.get(0) instanceof Aerodrom) {

				JSON.exportAirport((List<Aerodrom>) list, fileName);
				return true;
			} else if (list.get(0) instanceof Putnik) {

				JSON.exportPassanger((List<Putnik>) list, fileName);
				return true;
			} else if (list.get(0) instanceof Let) {

				JSON.exportFlight((List<Let>) list, fileName);
				return true;

			}

		}
		return false;
	}
	
	public <T> boolean exportToXML(List<T> list, String fileName) {
		if (list.get(0) != null) {
			if (list.get(0) instanceof Aerodrom) {

				XML.exportAerodrom((List<Aerodrom>) list, fileName);
				return true;
			} else if (list.get(0) instanceof Putnik) {

				XML.exportPutnik((List<Putnik>) list, fileName);
				return true;
			} else if (list.get(0) instanceof Let) {

				XML.exportFlight((List<Let>) list, fileName);
				return true;

			}
		}
		return false;
	}

	public <T> boolean exportToPDF(List<T> list, String fileName) {
		if (list.get(0) != null) {
			if (list.get(0) instanceof Putnik) {

				return PDF.exportPutnici((List<Putnik>) list, fileName);

			} else if (list.get(0) instanceof Aerodrom) {

				return PDF.exportAerodrom((List<Aerodrom>) list, fileName);

			} else if (list.get(0) instanceof Let) {

				return PDF.exportLetovi((List<Let>) list, fileName);

			}
		}

		return false;
	}

}
