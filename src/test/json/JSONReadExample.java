package test.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReadExample {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(
					"/Users/jeoos43/Desktop/JSONreadExample.json"));

			JSONObject jobj = (JSONObject) obj;

			String dbname = (String) jobj.get("dbname");
			System.out.println("dbname : " + dbname);
			String version = (String) jobj.get("version");
			System.out.println("version : " + version);

			JSONArray msg = (JSONArray) jobj.get("messages");
			Iterator<String> iterator = msg.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}

		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
	}
}
