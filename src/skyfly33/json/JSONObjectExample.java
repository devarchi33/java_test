package skyfly33.json;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class JSONObjectExample {
	public static void main(String[] args) {
		JSONObjectExample example = new JSONObjectExample();
		JSONObject jobj = example.createJSONObject();
		HashMap jmap = example.createMap();
		String sjon = jobj.toString();
		String mjson = JSONObject.toJSONString(jmap);

		System.out.println("JSON to JSON String : " + sjon);
		System.out.println("Map to JSON String : " + mjson);

	}

	@SuppressWarnings("unchecked")
	public JSONObject createJSONObject() {
		JSONObject obj = new JSONObject();
		obj.put("rdb", "oracle");
		obj.put("nosql", "mongodb");
		obj.put("language1", "java");
		obj.put("language2", "javascript");

		return obj;
	}

	@SuppressWarnings("unchecked")
	public HashMap createMap() {
		HashMap map = new HashMap();
		map.put("rdb", "mysql");
		map.put("nosql", "couch base");
		map.put("framework1", "node.js");
		map.put("framework2", "spring");

		return map;
	}
}
