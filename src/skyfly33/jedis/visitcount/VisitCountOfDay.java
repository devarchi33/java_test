package skyfly33.jedis.visitcount;

import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class VisitCountOfDay {

	private JedisHelper jedisHelper;
	private Jedis jedis;
	private static final String KEY_EVENT_CLICK_DAILY_TOTAL = "event:click:daily:total:";
	private static final String KEY_EVENT_CLICK_DAILY = "event:click:daily:";

	/**
	 * ��¥�� �湮 Ƚ�� ó���� ���� Ŭ���� ����
	 * 
	 * @param jedisHelper
	 */
	public VisitCountOfDay(JedisHelper jedisHelper) {
		this.jedisHelper = jedisHelper;
		this.jedis = jedisHelper.getConnection();
	}

	/**
	 * �̺�Ʈ ���̵� �ش��ϴ� ��¥�� �湮 Ƚ���� ��¥�� ��ü �湮 Ƚ���� ������Ų��.
	 * 
	 * @param eventId
	 * @return ��¥�� �̺�Ʈ ������ �湮 Ƚ��.
	 */
	public Long addVisit(String eventId) {
		this.jedis.incr(KEY_EVENT_CLICK_DAILY_TOTAL + getToday());
		return this.jedis.incr(KEY_EVENT_CLICK_DAILY + getToday() + ":"
				+ eventId);
	}

	/**
	 * ��û�� ��¥�� �ش��ϴ� ��ü �̺�Ʈ ������ �湮 Ƚ���� ��ȸ�Ѵ�.
	 * 
	 * @param date
	 * @return
	 */
	public String getVisitTotalCount(String date) {    
		return this.jedis.get(KEY_EVENT_CLICK_DAILY_TOTAL + date);
	}

	public List<String> getVisitCountByDate(String eventId, String[] dateList) {
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < dateList.length; i++) {
			result.add(jedis.get(KEY_EVENT_CLICK_DAILY + dateList[i] + ":" + eventId));
		}

		return result;
	}

	private String getToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}
}
