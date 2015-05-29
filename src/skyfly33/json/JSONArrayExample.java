package skyfly33.json;

import java.util.ArrayList;

import org.json.simple.JSONArray;

public class JSONArrayExample {
	public static void main(String[] args) {
		JSONArrayExample example = new JSONArrayExample();
		ArrayList arr = example.createJsonArray();
		JSONArray jarr = example.mixedJsonArray();

		String aJsonList = JSONArray.toJSONString(arr);
		String jsonList = jarr.toString();

		System.out.println("ArrayList to JSONArray : " + aJsonList);
		System.out.println("Mixed JSONList : " + jsonList);
		for (int i = 0; i < jarr.size(); i++) {
			System.out.println("Mixed JSONList[" + i + "] : " + jarr.get(i));
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList createJsonArray() {
		ArrayList arr = new ArrayList();
		arr.add("java");
		arr.add("javascript");
		arr.add("jquery");
		arr.add("mongodb");

		return arr;
	}

	@SuppressWarnings("unchecked")
	public JSONArray mixedJsonArray() {
		JSONArray arr = new JSONArray();
		arr.add("spring");
		arr.add("R");
		arr.add("mysql");
		arr.add(createJsonArray());

		return arr;
	}
}
