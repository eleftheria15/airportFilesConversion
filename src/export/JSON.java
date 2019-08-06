package export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import lists.Aerodrom;
import lists.Let;
import lists.Putnik;

public class JSON {
	public static void exportPassanger(List<Putnik> list, String fileName) {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject put = new JSONObject();
		for (Putnik p : list) {
			JSONObject putnik = new JSONObject();
			putnik.put("first_name", p.getIme());
			putnik.put("last_name", p.getPrezime());
			putnik.put("year", p.getGodine());
			putnik.put("town", p.getGrad());
			putnik.put("street", p.getUlica());
			putnik.put("phone", p.getTelefon());
			putnik.put("class", p.getKlasa());
			put.put("passanger", putnik);
			array.add(put);

		}
		obj.put("passangers", array);
		try {
			FileWriter writer = new FileWriter(new File(fileName + ".json"));
			writer.write(obj.toJSONString());
			writer.flush();
			writer.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void exportAirport(List<Aerodrom> list, String fileName) {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject aer = new JSONObject();
		for (Aerodrom a : list) {
			JSONObject aerodrom = new JSONObject();
			aerodrom.put("name", a.getNaziv());
			aerodrom.put("town", a.getGrad());
			aerodrom.put("street", a.getUlica());
			aerodrom.put("phone", a.getTelefon());
			aer.put("airport", aerodrom);
			array.add(aer);
		}
		obj.put("airports", array);
		try {
			FileWriter writer = new FileWriter(new File(fileName + ".json"));
			writer.write(obj.toJSONString());
			writer.flush();
			writer.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void exportFlight(List<Let> list, String fileName) {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject le = new JSONObject();
		for (Let l : list) {
			JSONObject let = new JSONObject();
			let.put("name", l.getNaziv());
			let.put("company", l.getKompanija());
			let.put("numberOfPassangers", l.getBrojputnika());
			let.put("terminal", l.getTerminal());
			le.put("flight", let);
			array.add(le);
		}
		obj.put("flights", array);
		try {
			FileWriter writer = new FileWriter(new File(fileName + ".json"));
			writer.write(obj.toJSONString());
			writer.flush();
			writer.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
