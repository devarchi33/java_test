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
	 * 날짜별 방문 횟수 처리를 위한 클래스 생성
	 * 
	 * @param jedisHelper
	 */
	public VisitCountOfDay(JedisHelper jedisHelper) {
		this.jedisHelper = jedisHelper;
		this.jedis = jedisHelper.getConnection();
	}

	/**
	 * 이벤트 아이디에 해당하는 날짜의 방문 횟수와 날짜별 전체 방문 횟수를 증가시킨다.
	 * 
	 * @param eventId
	 * @return 날짜별 이벤트 페이지 방문 횟수.
	 */
	public Long addVisit(String eventId) {
		this.jedis.incr(KEY_EVENT_CLICK_DAILY_TOTAL + getToday());
		return this.jedis.incr(KEY_EVENT_CLICK_DAILY + getToday() + ":"
				+ eventId);
	}

	/**
	 * 요청된 날짜에 해당하는 전체 이벤트 페이지 방문 횟수를 조회한다.
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
