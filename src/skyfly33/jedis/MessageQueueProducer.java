package skyfly33.jedis;

import redis.clients.jedis.Jedis;

public class MessageQueueProducer {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.0.81");

		jedis.rpush("queue", "value1");
		jedis.rpush("queue", "value2");
		jedis.rpush("queue", "value3");

		jedis.close();

	}
}