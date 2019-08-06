package export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import com.itextpdf.text.BaseColor;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import com.itextpdf.text.pdf.PdfWriter;

import lists.Aerodrom;
import lists.Let;
import lists.Putnik;

public final class PDF {

	static Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);
	static Date date = new Date();
	static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public static boolean exportPutnici(List<Putnik> putnici, String fileName) {
		com.itextpdf.text.Document d = new com.itextpdf.text.Document();

		try {

			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100);
			float[] columnWidths = { 1, 4, 1 };
			table.setWidths(columnWidths);

			Image img = Image.getInstance("img/logo.png");
			img.scaleAbsolute(50, 50);
			Image img2 = Image.getInstance("img/logo.png");
			img2.scaleAbsolute(50, 50);

			Font am = new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
			Paragraph p2 = new Paragraph("Kreirano: " + df.format(date), ffont);
			p2.setAlignment(Element.ALIGN_RIGHT);

			Paragraph p = new Paragraph("Putnici", am);

			PdfPCell c1 = new PdfPCell(img);
			c1.setBorderColor(BaseColor.WHITE);
			PdfPCell c2 = new PdfPCell(p);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			c2.setBorderColor(BaseColor.WHITE);
			c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell c3 = new PdfPCell(img2);
			c3.setBorderColor(BaseColor.WHITE);
			c3.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);

			PdfWriter.getInstance(d, new FileOutputStream(new File(fileName + ".pdf")));

			d.open();
			d.add(p2);
			d.add(new Paragraph(" "));
			d.add(new Paragraph(" "));
			d.add(table);

			Font headerFont = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLUE);

			Font textFont = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
			p.setFont(new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.RED));
			p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

			d.add(new Paragraph(" "));
			Font titleFont = new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
			Paragraph title = new Paragraph("Putnici:", titleFont);
			d.add(title);
			createTablePutnici(d, textFont, headerFont, putnici);

			d.close();
			return true;
		} catch (FileNotFoundException e) {

			// e.printStackTrace();
		} catch (DocumentException e) {

			// e.printStackTrace();
		} catch (IOException e) {

			// e.printStackTrace();
		}
		return false;
	}

	private static void createTablePutnici(com.itextpdf.text.Document d, Font textFont, Font headerFont,
			List<Putnik> putnici) throws DocumentException {
		PdfPTable table = new PdfPTable(10);

		table.setWidthPercentage(110);

		PdfPCell c1 = new PdfPCell(new Phrase("ID putnik", headerFont));
		table.addCell(c1);

		PdfPCell c2 = new PdfPCell(new Phrase("Ime", headerFont));
		table.addCell(c2);

		PdfPCell c3 = new PdfPCell(new Phrase("Prezime", headerFont));
		table.addCell(c3);

		PdfPCell c4 = new PdfPCell(new Phrase("Pol", headerFont));
		table.addCell(c4);

		PdfPCell c5 = new PdfPCell(new Phrase("Godine", headerFont));
		table.addCell(c5);

		PdfPCell c6 = new PdfPCell(new Phrase("Grad", headerFont));
		table.addCell(c6);

		PdfPCell c7 = new PdfPCell(new Phrase("Ulica", headerFont));
		table.addCell(c7);

		PdfPCell c8 = new PdfPCell(new Phrase("Telefon", headerFont));
		table.addCell(c8);

		PdfPCell c9 = new PdfPCell(new Phrase("Klasa", headerFont));
		table.addCell(c9);

		PdfPCell c10 = new PdfPCell(new Phrase("ID leta", headerFont));
		table.addCell(c10);

		d.add(new Paragraph(" "));

		for (Putnik a : putnici) {

			table.addCell(new Phrase(a.getId_putnik() + "", textFont));
			table.addCell(new Phrase(a.getIme(), textFont));
			table.addCell(new Phrase(a.getPrezime(), textFont));
			table.addCell(new Phrase(a.getPol(), textFont));
			table.addCell(new Phrase(a.getGodine() + "", textFont));
			table.addCell(new Phrase(a.getGrad(), textFont));
			table.addCell(new Phrase(a.getUlica(), textFont));
			table.addCell(new Phrase(a.getTelefon(), textFont));
			table.addCell(new Phrase(a.getKlasa(), textFont));
			table.addCell(new Phrase(a.getId_let() + "", textFont));

		}
		d.add(table);

	}

	public static boolean exportLetovi(List<Let> letovi, String fileName) {
		com.itextpdf.text.Document d = new com.itextpdf.text.Document();

		try {

			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100);
			float[] columnWidths = { 1, 4, 1 };
			table.setWidths(columnWidths);

			Image img = Image.getInstance("img/logo.png");
			img.scaleAbsolute(50, 50);
			Image img2 = Image.getInstance("img/logo.png");
			img2.scaleAbsolute(50, 50);

			Font am = new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
			Paragraph p2 = new Paragraph("Kreirano: " + df.format(date), ffont);
			p2.setAlignment(Element.ALIGN_RIGHT);

			Paragraph p = new Paragraph("Letovi", am);

			PdfPCell c1 = new PdfPCell(img);
			c1.setBorderColor(BaseColor.WHITE);
			PdfPCell c2 = new PdfPCell(p);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			c2.setBorderColor(BaseColor.WHITE);
			c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell c3 = new PdfPCell(img2);
			c3.setBorderColor(BaseColor.WHITE);
			c3.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);

			PdfWriter.getInstance(d, new FileOutputStream(new File(fileName + ".pdf")));

			d.open();
			d.add(p2);
			d.add(new Paragraph(" "));
			d.add(new Paragraph(" "));
			d.add(table);

			Font headerFont = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLUE);

			Font textFont = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
			p.setFont(new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.RED));
			p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

			d.add(new Paragraph(" "));
			Font titleFont = new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
			Paragraph title = new Paragraph("Letovi:", titleFont);
			d.add(title);

			createTableLetovi(d, textFont, headerFont, letovi);
			d.close();
			return true;
		} catch (FileNotFoundException e) {

			// e.printStackTrace();
		} catch (DocumentException e) {

			// e.printStackTrace();
		} catch (IOException e) {

			// e.printStackTrace();
		}
		return false;
	}

	private static void createTableAerodomi(com.itextpdf.text.Document d, Font textFont, Font headerFont,
			List<Aerodrom> aerodromi) throws DocumentException {
		PdfPTable table = new PdfPTable(5);

		table.setWidthPercentage(100);

		PdfPCell c1 = new PdfPCell(new Phrase("ID Aerodroma", headerFont));
		table.addCell(c1);

		PdfPCell c2 = new PdfPCell(new Phrase("Naziv", headerFont));
		table.addCell(c2);

		PdfPCell c3 = new PdfPCell(new Phrase("Grad", headerFont));
		table.addCell(c3);

		PdfPCell c4 = new PdfPCell(new Phrase("Ulica", headerFont));
		table.addCell(c4);

		PdfPCell c5 = new PdfPCell(new Phrase("Telefon", headerFont));
		table.addCell(c5);

		for (Aerodrom p : aerodromi) {

			table.addCell(new Phrase(p.getId_aerodrom() + "", textFont));
			table.addCell(new Phrase(p.getNaziv(), textFont));
			table.addCell(new Phrase(p.getGrad(), textFont));
			table.addCell(new Phrase(p.getUlica(), textFont));
			table.addCell(new Phrase(p.getTelefon(), textFont));

		}

		d.add(table);
	}

	public static boolean exportAerodrom(List<Aerodrom> aerodromi, String fileName) {
		com.itextpdf.text.Document d = new com.itextpdf.text.Document();

		try {

			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100);
			float[] columnWidths = { 1, 4, 1 };
			table.setWidths(columnWidths);

			System.out.println("Da");
			Image img = Image.getInstance("img/logo.png");
			img.scaleAbsolute(50, 50);
			Image img2 = Image.getInstance("img/logo.png");
			img2.scaleAbsolute(50, 50);

			Font am = new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
			Paragraph p2 = new Paragraph("Created on: " + df.format(date), ffont);
			p2.setAlignment(Element.ALIGN_RIGHT);

			Paragraph p = new Paragraph("Aerodrom", am);

			PdfPCell c1 = new PdfPCell(img);
			c1.setBorderColor(BaseColor.WHITE);
			PdfPCell c2 = new PdfPCell(p);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			c2.setBorderColor(BaseColor.WHITE);
			c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell c3 = new PdfPCell(img2);
			c3.setBorderColor(BaseColor.WHITE);
			c3.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);

			PdfWriter.getInstance(d, new FileOutputStream(new File(fileName + ".pdf")));

			d.open();
			d.add(p2);
			d.add(new Paragraph(" "));
			d.add(new Paragraph(" "));
			d.add(table);

			Font headerFont = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLUE);

			Font textFont = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
			p.setFont(new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.RED));
			p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

			d.add(new Paragraph(" "));
			Font titleFont = new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
			Paragraph title = new Paragraph("Aerodrom:", titleFont);
			d.add(title);
			createTableAerodomi(d, textFont, headerFont, aerodromi);
			d.close();
			return true;
		} catch (FileNotFoundException e) {

			// e.printStackTrace();
		} catch (DocumentException e) {

			// e.printStackTrace();
		} catch (IOException e) {

			// e.printStackTrace();
		}
		return false;
	}

	private static void createTableLetovi(com.itextpdf.text.Document d, Font textFont, Font headerFont,
			List<Let> letovi) throws DocumentException {
		PdfPTable table = new PdfPTable(6);

		table.setWidthPercentage(100);

		PdfPCell c1 = new PdfPCell(new Phrase("ID Let", headerFont));
		table.addCell(c1);

		PdfPCell c2 = new PdfPCell(new Phrase("Naziv", headerFont));
		table.addCell(c2);

		PdfPCell c3 = new PdfPCell(new Phrase("Kompanija", headerFont));
		table.addCell(c3);

		PdfPCell c4 = new PdfPCell(new Phrase("Id Aerodroma", headerFont));
		table.addCell(c4);

		PdfPCell c5 = new PdfPCell(new Phrase("Broj putnika", headerFont));
		table.addCell(c5);

		PdfPCell c6 = new PdfPCell(new Phrase("Terminal", headerFont));
		table.addCell(c6);

		d.add(new Paragraph(" "));

		for (Let p : letovi) {

			table.addCell(new Phrase(p.getId_let() + "", textFont));
			table.addCell(new Phrase(p.getNaziv(), textFont));
			table.addCell(new Phrase(p.getKompanija(), textFont));
			table.addCell(new Phrase(p.getId_aerodrom() + "", textFont));
			table.addCell(new Phrase(p.getTerminal() + "", textFont));
			table.addCell(new Phrase(p.getTerminal() + "", textFont));

		}

		d.add(table);
	}

}
