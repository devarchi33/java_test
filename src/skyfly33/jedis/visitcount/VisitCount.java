package skyfly33.jedis.visitcount;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.ArrayList;

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

	/**
	 * ��û�� �̺�Ʈ �������� �湮 Ƚ���� ��ü �̺�Ʈ �������� �湮 Ƚ���� ������Ų��.
	 * 
	 * @param eventId
	 * @return ��û�� �̺�Ʈ �������� �� �湮 Ƚ��.
	 */
	public Long addVisit(String eventId) {
		this.jedis.incr(KEY_EVENT_CLICK_TOTAL);
		return this.jedis.incr(KEY_EVENT_CLICK + eventId);
	}

	/**
	 * 
	 * @return ��ü �̺�Ʈ �������� �湮 Ƚ��.
	 */
	public String getVisitTotalCount() {
		return this.jedis.get(KEY_EVENT_CLICK_TOTAL);
	}

	public List<String> getVisitCount(String... eventList) {
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < eventList.length; i++) {
			result.add(this.jedis.get(KEY_EVENT_CLICK + eventList[i]));
		}

		return result;
	}
}
