package skyfly33.jedis.uniquevisit;

import redis.clients.jedis.Jedis;

public class test {

	static JedisHelper helper = JedisHelper.getInstance();
	static Jedis jedis = helper.getConnection();
	
	public static void main(String[] args){
		String temp = jedis.get("uv:event");
		int tempSize = temp.length();
		
		System.out.println("tempSize : " + tempSize);
	}
}
