package skyfly33.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisStringJava {
	public static void main(String[] args) {
		try {
			// Connecting to Redis server on localhost
			Jedis jedis = new Jedis("192.168.0.81");
			System.out.println("Connection to server sucessfully");
			// set the data in redis string
			jedis.set("tutorial-name", "Redis tutorial");
			// Get the stored data and print it
			System.out.println("Stored string in redis : "
					+ jedis.get("tutorial-name"));
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		}
	}
}