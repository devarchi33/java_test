package skyfly33.jedis.cart;

import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import redis.clients.jedis.Jedis;

public class Cart {

	private Jedis jedis;
	private JSONObject cartInfo;
	private String userNo;
	private static final String KEY_CART_LIST = ":cart:product";
	private static final String KEY_CART_PRODUCT = ":cart:productid:";
	private static final String JSON_PRODUCT_LIST = "products";
	private static final int EXPIRE = 60 * 60 * 24 * 3;

	public Cart(JedisHelper helper, String userNo) {
		this.jedis = helper.getConnection();
		this.userNo = userNo;
		this.cartInfo = getCartInfo();
	}

	public JSONObject getCartInfo() {
		String productInfo = this.jedis.get(this.userNo + KEY_CART_LIST);
		if (productInfo == null || "".equals(productInfo))
			return makeEmptyCart();

		try {
			JSONParser parser = new JSONParser();
			return (JSONObject) parser.parse(productInfo);
		} catch (ParseException e) {
			// TODO: handle exception
			return makeEmptyCart();
		}
	}

	public JSONObject makeEmptyCart() {
		JSONObject cart = new JSONObject();
		cart.put(JSON_PRODUCT_LIST, new JSONArray());
		return cart;
	}

	public int flushCart() {
		JSONArray products = (JSONArray) this.cartInfo.get(JSON_PRODUCT_LIST);
		for (int i = 0; i < products.size(); i++) {
			this.jedis.del(this.userNo + KEY_CART_PRODUCT + products.get(i));
		}

		this.jedis.set(this.userNo + KEY_CART_LIST, "");
		return products.size();
	}

	public String addProduct(String productNo, String productName, int quantity) {
		JSONArray products = (JSONArray) this.cartInfo.get(JSON_PRODUCT_LIST);
		products.add(productNo);

		this.jedis.set(this.userNo + KEY_CART_LIST,
				this.cartInfo.toJSONString());

		JSONObject product = new JSONObject();
		product.put("productNo", productNo);
		product.put("productName", productName);
		product.put("quantity", quantity);

		String poductKey = this.userNo + KEY_CART_PRODUCT + productNo;
		return this.jedis.setex(poductKey, EXPIRE, product.toJSONString());
	}

	public int deleteProduct(String[] productNo) {
		JSONArray products = (JSONArray) this.cartInfo.get(JSON_PRODUCT_LIST);
		int result = 0;

		for (String item : productNo) {
			products.remove(item);
			result += this.jedis.del(this.userNo + KEY_CART_PRODUCT + item);
		}

		this.jedis.set(this.userNo + KEY_CART_LIST,
				this.cartInfo.toJSONString());
		return result;
	}

	public JSONArray getProductList() {
		boolean isChanged = false;
		JSONArray products = (JSONArray) this.cartInfo.get(JSON_PRODUCT_LIST);
		JSONArray result = new JSONArray();
		String value = null;

		for (int i = 0; i < products.size(); i++) {
			value = this.jedis.get(this.userNo + KEY_CART_PRODUCT
					+ products.get(i));

			if (value == null)
				isChanged = true;
			else
				result.add(value);
		}

		if (isChanged) {
			this.jedis.set(this.userNo + KEY_CART_LIST,
					this.cartInfo.toJSONString());
		}

		return result;
	}

	public int flushCartDeprecate() {
		Set<String> keys = this.jedis
				.keys(this.userNo + KEY_CART_PRODUCT + "*");
		for (String key : keys) {
			this.jedis.del(key);
		}

		this.jedis.set(this.userNo + KEY_CART_LIST, "");
		return keys.size();
	}
}
