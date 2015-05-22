package test.jedis;

import java.util.List;

import redis.clients.jedis.Jedis;

public class MessageQueueConsumer {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.0.81");
		List<String> messages = null;
		while (true) {
			System.out.println("wating for message in the queue");
			messages = jedis.blpop(5, "queue");
			System.out.println("got the messsage!!");
			System.out.println("key : " + messages.get(0));
			System.out.println("value : " + messages.get(1));
			String payLoad = messages.get(1);
			System.out.println("Message received : " + payLoad);
			jedis.close();
		}
	}
}
