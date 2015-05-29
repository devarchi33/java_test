package skyfly33.json;

import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONParseExample {

	public static void main(String[] args) {

		JSONParseExample ex = new JSONParseExample();
		System.out.println("JSONObject to String : " + ex.stringByJSONObject());
		System.out.println("JSONArray to String : " + ex.stringByJSONArray());
		String str = ex.stringByJSONArray();
		Object obj = ex.parseJSON(str);
		ex.analysis(obj);

		System.out.println("------------------");
		str = ex.stringByJSONObject();
		obj = ex.parseJSON(str);
		ex.analysis(obj);

		// System.out.println("------------------");
		// str = "{name : ¿Ãµø»∆, age : 31, job : java programmer}";
		// obj = ex.parseJSON(str);
		// ex.analysis(obj);
	}

	@SuppressWarnings("unchecked")
	public String stringByJSONObject() {
		JSONObject obj = new JSONObject();

		obj.put("javascript", "1");
		obj.put("java", "2");
		obj.put("node.js", "3");

		return obj.toJSONString();
	}

	@SuppressWarnings("unchecked")
	public String stringByJSONArray() {
		JSONArray arr = new JSONArray();

		arr.add("mysql");
		arr.add("oracle");
		arr.add("mongodb");

		return arr.toJSONString();
	}

	public Object parseJSON(String jsonString) {
		Object rt = null;
		JSONParser parser = new JSONParser();

		try {
			rt = parser.parse(jsonString);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Parsing Error : " + e.getStackTrace());
		}

		return rt;
	}

	public void analysis(Object obj) {
		if (obj instanceof JSONArray) {
			JSONArray arr = (JSONArray) obj;
			Iterator iterator = arr.iterator();

			while (iterator.hasNext()) {
				System.out.println("[" + iterator.next() + "]");
			}

		} else if (obj instanceof JSONObject) {
			JSONObject jobj = (JSONObject) obj;
			Set keySet = jobj.keySet();

			for (Object key : keySet) {
				Object value = jobj.get(key);
				System.out.println("[" + key + "]/[" + value + "]");
			}

		} else {
			System.out.println("can't analyze");
		}
	}
}
