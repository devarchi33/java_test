package skyfly33.jedis.visitcount;

import redis.clients.jedis.Jedis;

public class VisitCount {

	private JedisHelper jedisHelper;
	private Jedis jedis;
	private final String KEY_EVENT_CLICK_TOTAL = "event:click:total";
	private final String KEY_EVENT_CLICK = "event:click:";

	/**
	 * �湮 Ƚ�� ó���� ���� Ŭ���� ������
	 * 
	 * @param jedisHelper
	 */
	public VisitCount(JedisHelper jedisHelper) {
		this.jedisHelper = jedisHelper;
		this.jedis = jedisHelper.getConnection();
	}

	public Long addVisit(String eventId) {
		this.jedis.incr(KEY_EVENT_CLICK_TOTAL);
		return this.jedis.incr(KEY_EVENT_CLICK + eventId);
	}
}
