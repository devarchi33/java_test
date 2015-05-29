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
	 * 방문 횟수 처리를 위한 클래스 생성자
	 * 
	 * @param jedisHelper
	 */
	public VisitCount(JedisHelper jedisHelper) {
		this.jedisHelper = jedisHelper;
		this.jedis = jedisHelper.getConnection();
	}

	/**
	 * 요청된 이벤트 페이지의 방문 횟수와 전체 이벤트 페이지의 방문 횟수를 증가시킨다.
	 * 
	 * @param eventId
	 * @return 요청된 이벤트 페이지의 총 방문 횟수.
	 */
	public Long addVisit(String eventId) {
		this.jedis.incr(KEY_EVENT_CLICK_TOTAL);
		return this.jedis.incr(KEY_EVENT_CLICK + eventId);
	}

	/**
	 * 
	 * @return 전체 이벤트 페이지의 방문 횟수.
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
