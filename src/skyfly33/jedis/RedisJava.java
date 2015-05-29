package skyfly33.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisJava {

	public static void main(String[] args) {
		try {
			Jedis jedis = new Jedis("192.168.0.82");
			System.out.println("Connection to server successfully!!");
			System.out.println("Server is running : " + jedis.ping());
			jedis.close();
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		}
	}
}