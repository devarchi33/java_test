package skyfly33.json;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONWriterExample {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		obj.put("name", "¿Ãµø»∆");
		obj.put("age", "31");

		JSONArray arr = new JSONArray();
		arr.add("msg1");
		arr.add("msg2");
		arr.add("msg3");

		obj.put("list", arr);

		try {
			FileWriter file = new FileWriter(
					"/Users/jeoos43/Desktop/JSONWriterExample.json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("JSONObject : " + obj);
	}
}
