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
	 * 날짜별 방문 횟수 처리를 위한 클래스의 생성자.
	 * 
	 * @param jedisHelper
	 */
	public VisitCountOfDayHash(JedisHelper jedisHelper) {
		this.jedisHelper = JedisHelper.getInstance();
		this.jedis = jedisHelper.getConnection();
	}

	/**
	 * 이벤트 아이디에 해당하는 날짜별 방문 횟수와 날짜별 전체 방문 횟수를 증가 시킨다.
	 * 
	 * @param eventId
	 * @return 이벤트 페이지 방문 횟수
	 */
	public Long addVisit(String eventId) {
		this.jedis.hincrBy(KEY_EVENT_DAILY_CLICK_TOTAL, getToday(), 1);
		return this.jedis.hincrBy(KEY_EVENT_CLICK_CLICK + eventId, getToday(),
				1);

	}

	/**
	 * 이벤트 페이지에 대한 모든 날짜별 방문 횟수를 조회한다.
	 * 
	 * @return 전체 이벤트 페이지 방문 횟수
	 */
	public SortedMap<String, String> getVisitCountByDailyTotal() {
		SortedMap<String, String> result = new TreeMap<String, String>();
		result.putAll(this.jedis.hgetAll(KEY_EVENT_DAILY_CLICK_TOTAL));
		return result;
	}

	/**
	 * 이벤트 아이디에 해당하는 모든 날짜의 방문 횟수를 조회한다.
	 *
	 * @param eventId 요청된 이벤트 아이디.
	 * @return 날짜로 정렬된 방문 횟수 목록.
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
