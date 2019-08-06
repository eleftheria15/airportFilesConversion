package export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

import lists.Aerodrom;

import lists.Let;
import lists.Putnik;

public class XML {

	public static String airportValidation(String xml) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(true);

			SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			factory.setSchema(schemaFactory.newSchema(new Source[] { new StreamSource("schemas/aerodromsema.xsd") }));

			SAXParser parser = factory.newSAXParser();
			SAXReader reader = new SAXReader(parser.getXMLReader());

			try {
				reader.read(new File(xml));
				System.out.println(xml);
				return "Validation successfull";
			} catch (Exception e) {
				return "Validation failed. " + e.getMessage();
			}

		} catch (ParserConfigurationException | SAXException e) {
		}
		return "Validation failed";

	}

	public static String flightValidation(String xml) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(true);

			SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			factory.setSchema(schemaFactory.newSchema(new Source[] { new StreamSource("schemas/letsema.xsd") }));

			SAXParser parser = factory.newSAXParser();
			SAXReader reader = new SAXReader(parser.getXMLReader());

			try {
				reader.read(new File(xml));

				return "Validation successfull";
			} catch (Exception e) {
				return "Validation failed. " + e.getMessage();
			}

		} catch (ParserConfigurationException | SAXException e) {
		}
		return "Validation failed";

	}

	public static String passangerValidation(String xml) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(true);

			SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			factory.setSchema(schemaFactory.newSchema(new Source[] { new StreamSource("schemas/putniksema.xsd") }));

			SAXParser parser = factory.newSAXParser();
			SAXReader reader = new SAXReader(parser.getXMLReader());

			try {
				reader.read(new File(xml));
				return "Validation successfull";
			} catch (Exception e) {
				return "Validation failed. " + e.getMessage();
			}

		} catch (ParserConfigurationException | SAXException e) {
		}
		return "Validation failed";

	}

	public static boolean exportAerodrom(List<Aerodrom> list, String fileName) {
		Document d = DocumentHelper.createDocument();
		Element root = d.addElement("airportslist");
		Element air = root.addElement("airports");

		for (Aerodrom a : list) {

			Element airport = air.addElement("airport");
			airport.addAttribute("id", a.getId_aerodrom() + "");
			Element name = airport.addElement("name");
			name.setText(a.getNaziv());
			Element town = airport.addElement("town");
			town.setText(a.getGrad());
			Element phone = airport.addElement("phone");
			phone.setText(a.getTelefon());
			Element street = airport.addElement("street");
			street.setText(a.getUlica());

		}

		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(fileName + ".xml"), "UTF8"),
					format);
			writer.write(d);
			writer.close();
			return true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return false;

	}

	public static boolean exportFlight(List<Let> list, String fileName) {
		Document d = DocumentHelper.createDocument();
		Element root = d.addElement("flightlist");
		Element flights = root.addElement("flights");

		for (Let s : list) {

			Element flight = flights.addElement("flight");
			flight.addAttribute("id", s.getId_let() + "");
			Element name = flight.addElement("name");
			name.setText(s.getNaziv());
			Element company = flight.addElement("company");
			company.setText(s.getKompanija());
			Element noOfPassangers = flight.addElement("noOfPassangers");
			noOfPassangers.setText(s.getBrojputnika() + "");
			Element terminal = flight.addElement("terminal");
			terminal.setText(s.getTerminal() + "");
			Element airport = flight.addElement("airport");
			airport.setText(s.getId_aerodrom() + "");

		}

		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(fileName + ".xml"), "UTF8"),
					format);
			writer.write(d);
			writer.close();
			return true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return false;
	}

	public static boolean exportPutnik(List<Putnik> list, String fileName) {
		Document d = DocumentHelper.createDocument();
		Element root = d.addElement("passangerlist");
		Element donors = root.addElement("passangers");

		for (Putnik s : list) {

			Element passanger = donors.addElement("passanger");
			passanger.addAttribute("id", s.getId_putnik() + "");
			Element first_name = passanger.addElement("first_name");
			first_name.setText(s.getIme());
			Element last_name = passanger.addElement("last_name");
			last_name.setText(s.getPrezime());
			Element gender = passanger.addElement("gender");
			gender.setText(s.getPol());
			Element age = passanger.addElement("age");
			age.setText(s.getGodine() + "");
			Element city = passanger.addElement("city");
			city.setText(s.getGrad());
			Element street = passanger.addElement("street");
			street.setText(s.getUlica());
			Element phone = passanger.addElement("phone");
			phone.setText(s.getTelefon());
			Element flight = passanger.addElement("flight");
			flight.setText(s.getId_let() + "");
		}

		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(fileName + ".xml"), "UTF8"),
					format);
			writer.write(d);
			writer.close();
			return true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return false;
	}
}
