package test.jedis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisPopJava {

	public static void main(String[] args) {
		try {
			// Connecting to Redis server
			Jedis jedis = new Jedis("192.168.0.20");
			System.out.println("Connection successfully!!");
			// get the stored data and print it
			List<String> list = jedis.lrange("audit:17", 0, -1);
			for (int i = 0; i < list.size(); i++) {
				// System.out.println("Stored string in redis[" + (i+1) + "] : "
				// + list.get(i));
				String log = jedis.lpop("audit:17");
				System.out.println("log [" + (i + 1) + "] : " + log);
			}
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		}
	}
}
