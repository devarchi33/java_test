package test.jedis.logging;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import test.util.TestConfig;

public class JedisHelper {

	private static TestConfig tConfig = TestConfig.getInstance();;
	private final String REDIS_HOST = tConfig.getProperties("redis.host");
	private final int REDIS_PORT = Integer.parseInt(tConfig.getProperties("redis.port"));
	private final Set<Jedis> connectionList = new HashSet<Jedis>();
	private JedisPool pool;
	
	private JedisHelper() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setBlockWhenExhausted(true);

		this.pool = new JedisPool(config, REDIS_HOST, REDIS_PORT, 5000);
	}

	
	private static class LazyHolder {
		private static final JedisHelper INSTANCE = new JedisHelper();
	}

	
	public static JedisHelper getInstance() {
		return LazyHolder.INSTANCE;
	}

	
	final public Jedis getConnection() {
		Jedis jedis = this.pool.getResource();
//		Jedis jedis = new Jedis("192.168.0.31");
		this.connectionList.add(jedis);

		return jedis;
	}


	final public void returnResource(Jedis jedis) {
		this.pool.returnResourceObject(jedis);
	}

	
	final public void destroyPool() {
		Iterator<Jedis> jedisList = this.connectionList.iterator();
		while (jedisList.hasNext()) {
			Jedis jedis = jedisList.next();
			this.pool.returnResourceObject(jedis);
		}

		this.pool.destroy();
	}
}
