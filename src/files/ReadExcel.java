package files;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import lists.Aerodrom;

import lists.Let;
import lists.Putnik;

public class ReadExcel {

	private static String path;

	public ReadExcel(String path) {
		super();
		ReadExcel.path = path;

	}

	public String readPutnici(List<Putnik> putnici) {
		String warning = "";
		putnici.clear();
		File inputWorkbook = new File(path);
		if (!inputWorkbook.exists()) {
			return "Invalid file path!";
		}
		Workbook w;
		String error = "";
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			final String sheetName = "putnik";
			Sheet sheet = w.getSheet(sheetName);
			if (sheet == null) {
				error += "Unknown sheet in the excel file, sheet: " + sheetName;
				return error;
			}
			if (sheet.getRows() == 0) {
				error += "No rows found in the specified sheet " + sheet.getName();
			}
			Cell[] header = sheet.getRow(0);
			for (int i = 1; i < sheet.getRows(); i++) {
				Cell[] cells = sheet.getRow(i);
				int id = -1;
				try {
					id = Integer.parseInt(cells[0].getContents());
				} catch (NumberFormatException e) {
					warning += ("Cell " + header[0].getContents() + " content is not well formatted, row " + i
							+ ", sheet " + sheet.getName()) + "\n";
				}

				String first_name = cells[1].getContents();
				String last_name = cells[2].getContents();
				String gender = cells[3].getContents();
				int years = Integer.parseInt(cells[4].getContents());
				String town = cells[5].getContents();
				String street = cells[6].getContents();
				String phone = cells[7].getContents();
				String classType = cells[8].getContents();
				int id_let = -1;
				try {
					id_let = Integer.parseInt(cells[9].getContents());
				} catch (NumberFormatException e) {
					warning += ("Cell " + header[9].getContents() + " content is not well formatted, row " + i
							+ ", sheet " + sheet.getName()) + "\n";
				}
				Putnik p = new Putnik(id, first_name, last_name, gender, years, town, street, phone, classType, id_let);
				if (warning.equals("")) {
					if (putnici.contains(p)) {
						warning += "Duplicate passanger with the id " + id + " at line " + i + "\n";
					} else {
						putnici.add(p);
					}
				}

			}
			return warning;
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String readAirport(List<Aerodrom> list) {
		String warning = "";
		list.clear();
		String error = "";
		File inputWorkbook = new File(path);

		if (!inputWorkbook.exists()) {
			return "Invalid file path!";
		}
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			final String sheetName = "aerodrom";
			Sheet sheet = w.getSheet(sheetName);
			if (sheet == null) {
				error += "Unknown sheet in the excel file, sheet: " + sheetName;
				return error;
			}
			if (sheet.getRows() == 0) {
				error += "No rows found in the specified sheet " + sheet.getName();
				return error;
			}
			Cell[] header = sheet.getRow(0);
			for (int i = 1; i < sheet.getRows(); i++) {

				Cell[] cells = sheet.getRow(i);
				int ida = -1;
				try {
					ida = Integer.parseInt(cells[0].getContents());
				} catch (NumberFormatException e) {
					warning += ("Cell " + header[0].getContents() + " content is not well formatted, row " + i
							+ ", sheet " + sheet.getName()) + "\n";
				}
				String name = cells[1].getContents();
				String town = cells[2].getContents();
				String street = cells[3].getContents();
				String phone = cells[4].getContents();
				Aerodrom a = new Aerodrom(ida, name, town, street, phone);
				if (warning.equals("")) {
					if (list.contains(a)) {
						warning += "Duplicate airport the id " + ida + " at line " + i + "\n";

					} else {
						list.add(a);
					}
				}

			}
			return warning;
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String readLet(List<Let> list) {
		String warning = "";
		list.clear();
		File inputWorkbook = new File(path);
		if (!inputWorkbook.exists()) {
			return "Invalid file path!";
		}
		String error = "";
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			final String sheetName = "let";
			Sheet sheet = w.getSheet(sheetName);
			if (sheet == null) {
				error += "Unknown sheet in the excel file, sheet: " + sheetName;
				return error;
			}
			if (sheet.getRows() == 0) {
				error += "No rows found in the specified sheet " + sheet.getName();
			}
			Cell[] header = sheet.getRow(0);
			for (int i = 1; i < sheet.getRows(); i++) {
				Cell[] cells = sheet.getRow(i);
				int id_let = -1;

				try {
					id_let = Integer.parseInt(cells[0].getContents());
				} catch (NumberFormatException e) {
					warning += ("Cell " + header[0].getContents() + " content is not well formatted, row " + i
							+ ", sheet " + sheet.getName() + "\n");
				}
				String name = cells[1].getContents();
				String company = cells[2].getContents();
				int id_aerodrom = -1;
				try {
					id_aerodrom = Integer.parseInt(cells[3].getContents());
				} catch (NumberFormatException e) {
					warning += ("Cell " + header[3].getContents() + " content is not well formatted, row " + i
							+ ", sheet " + sheet.getName()) + "\n";
				}
				int nuOfPassangers = Integer.parseInt(cells[4].getContents());
				int terminal = Integer.parseInt(cells[5].getContents());
				Let l = new Let(id_let, name, company, id_aerodrom, nuOfPassangers, terminal);
				if (warning.equals("")) {
					if (list.contains(l)) {
						warning += "Duplicate blood bank with the id " + id_let + " at line " + i + "\n";
					} else {
						list.add(l);
					}
				}

			}
			return warning;
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
