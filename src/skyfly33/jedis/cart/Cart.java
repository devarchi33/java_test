package skyfly33.jedis.cart;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import redis.clients.jedis.Jedis;

public class Cart {

	private Jedis jedis;
	private static JSONObject cartInfo;
	private String userNo;
	private static final String KEY_CART_LIST = ":cart:product";
	private static final String KEY_CART_PRODUCT = ":cart:productid:";
	private static final String JSON_PRODUCT_LIST = "products";
	private static final int EXPIRE = 60;
	
	public Cart(JedisHelper helper, String userNo){
		this.jedis = helper.getConnection();
		this.userNo = userNo;
		this.cartInfo = getCartInfo();
	}
	
	private JSONObject getCartInfo() {
		String productInfo = this.jedis.get(this.userNo+KEY_CART_LIST);
		if(null == productInfo || "".equals(productInfo))
			return makeEmptyCart();
		
		try {
			JSONParser parser = new JSONParser();
			return (JSONObject) parser.parse(productInfo);
		} catch (ParseException e) {
			// TODO: handle exception
			return makeEmptyCart();
		}
	}
	
	private JSONObject makeEmptyCart() {
		JSONObject cart = new JSONObject();
		cart.put(JSON_PRODUCT_LIST, new JSONArray());
		return cart;
	}
	
	public int flushCart() {
		JSONArray products = (JSONArray) this.cartInfo.get(JSON_PRODUCT_LIST);
		for (int i = 0; i < products.size(); i++) {
			this.jedis.del(this.userNo+KEY_CART_PRODUCT+products.get(i));
		}
		
		this.jedis.set(this.userNo + KEY_CART_LIST, "");
		return products.size();
	}
	
	public String addProduct(String productNo, String productName, int quantity){
		JSONArray products = (JSONArray) this.cartInfo.get(JSON_PRODUCT_LIST);
		
		products.add(productNo);
		
		this.jedis.set(this.userNo + KEY_CART_LIST, this.cartInfo.toJSONString());
		
		JSONObject product = new JSONObject();
		product.put("productNo", productNo);
		product.put("productName", productName);
		product.put("quantity",quantity);
		
		String productKey = this.userNo + KEY_CART_PRODUCT + productNo;
		return this.jedis.setex(productKey, EXPIRE, product.toJSONString());
	}
	
	
	static JedisHelper helper = JedisHelper.getInstance();
	public static void main(String[] args){
		Cart cart = new Cart(helper,"user1");
		System.out.println("before : "+cartInfo);
		cart.addProduct("pNo1", "pName1", 1);
		System.out.println("after : "+cartInfo);
		
		JSONObject tempObject = new JSONObject();
		JSONArray tempArray = new JSONArray();
		tempObject.put("arr", tempArray);
		
		System.out.println(tempObject);
		JSONArray testArray = (JSONArray) tempObject.get("arr");
		System.out.println(tempObject);
		testArray.add("tempData");
		System.out.println(tempObject);
		
		
		
		
	}
}
