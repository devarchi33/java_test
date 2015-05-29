package skyfly33.jedis.visitcount;

import java.text.SimpleDateFormat;
import java.util.*;
import redis.clients.jedis.Jedis;

public class VisitCountOfDayHash {

	private JedisHelper jedisHelper;
	private Jedis jedis;
	private static final String KEY_EVENT_DAILY_CLICK_TOTAL = "event:daily:click:total:hash:";
	private static final String KEY_EVENT_CLICK_CLICK = "event:daily:click:hash:";

	/**
	 * ��¥�� �湮 Ƚ�� ó���� ���� Ŭ������ ������.
	 * 
	 * @param jedisHelper
	 */
	public VisitCountOfDayHash(JedisHelper jedisHelper) {
		this.jedisHelper = JedisHelper.getInstance();
		this.jedis = jedisHelper.getConnection();
	}

	/**
	 * �̺�Ʈ ���̵� �ش��ϴ� ��¥�� �湮 Ƚ���� ��¥�� ��ü �湮 Ƚ���� ���� ��Ų��.
	 * 
	 * @param eventId
	 * @return �̺�Ʈ ������ �湮 Ƚ��
	 */
	public Long addVisit(String eventId) {
		this.jedis.hincrBy(KEY_EVENT_DAILY_CLICK_TOTAL, getToday(), 1);
		return this.jedis.hincrBy(KEY_EVENT_CLICK_CLICK + eventId, getToday(),
				1);

	}

	/**
	 * �̺�Ʈ �������� ���� ��� ��¥�� �湮 Ƚ���� ��ȸ�Ѵ�.
	 * 
	 * @return ��ü �̺�Ʈ ������ �湮 Ƚ��
	 */
	public SortedMap<String, String> getVisitCountByDailyTotal() {
		SortedMap<String, String> result = new TreeMap<String, String>();
		result.putAll(this.jedis.hgetAll(KEY_EVENT_DAILY_CLICK_TOTAL));
		return result;
	}

	/**
	 * �̺�Ʈ ���̵� �ش��ϴ� ��� ��¥�� �湮 Ƚ���� ��ȸ�Ѵ�.
	 *
	 * @param eventId ��û�� �̺�Ʈ ���̵�.
	 * @return ��¥�� ���ĵ� �湮 Ƚ�� ���.
	 */
	public SortedMap<String, String> getVisitCountByDaily(String eventId) {
		SortedMap<String, String> result = new TreeMap<String, String>();
		result.putAll(this.jedis.hgetAll(KEY_EVENT_CLICK_CLICK + eventId));
		return result;
	}

	private String getToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}
}
