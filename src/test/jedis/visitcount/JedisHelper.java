package test.jedis.visitcount;

import test.util.TestConfig;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisHelper {

	private static final TestConfig tConfig = TestConfig.getInstance();
	private static final String REDIS_HOST = tConfig
			.getProperties("redis.host");
	private static final int REDIS_PORT = Integer.parseInt(tConfig
			.getProperties("redis.port"));
	private final Set<Jedis> connectionList = new HashSet<Jedis>();
	private JedisPool pool = null;

	private static class LazyHolder {
		private static final JedisHelper INSTANCE = new JedisHelper();
	}

	public JedisHelper getInstance() {
		return LazyHolder.INSTANCE;
	}

	private JedisHelper() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setBlockWhenExhausted(true);

		this.pool = new JedisPool(config, REDIS_HOST, REDIS_PORT, 5000);
	}

	final public Jedis getConnection() {
		Jedis jedis = this.pool.getResource();
		connectionList.add(jedis);

		return jedis;
	}

	final public void returnResource(Jedis jedis) {
		this.pool.returnResourceObject(jedis);
	}

	final public void destroyPool() {
		Iterator<Jedis> jedisList = connectionList.iterator();
		while (jedisList.hasNext()) {
			Jedis jedis = jedisList.next();
			this.pool.returnResourceObject(jedis);
		}

		this.pool.destroy();
	}

}