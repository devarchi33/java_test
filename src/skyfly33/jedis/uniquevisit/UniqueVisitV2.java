package skyfly33.jedis.uniquevisit;

import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UniqueVisitV2 {

	private Jedis jedis;
	private static final String KEY_PAGE_VIEW = "page:view:";
	private static final String KEY_UNIQUE_VISITOR = "unique:visitors:";

	public UniqueVisitV2(JedisHelper helper) {
		this.jedis = helper.getConnection();
	}

	
	/**
	 * Ư�� ������� �� �湮 Ƚ���� ���� �湮 Ƚ���� ���� ��Ų��.
	 * @param userNo ����� ��ȣ
	 */
	public void visit(int userNo) {
		Pipeline p = this.jedis.pipelined();
		p.incr(KEY_PAGE_VIEW + getToday());
		p.setbit(KEY_UNIQUE_VISITOR + getToday(), userNo, true);
		p.sync();
	}

	
	/**
	 * �׽�Ʈ�� ���Ͽ� �־��� ��¥�� �� �湮 Ƚ���� ���� �湮 Ƚ���� ���� ��Ų��.
	 * @param userNo ����� ��ȣ
	 * @param date �׽�Ʈ�� ���� ��¥
	 */
	public void visit(int userNo, String date) {
		Pipeline p = this.jedis.pipelined();
		p.incr(KEY_PAGE_VIEW + date);
		p.setbit(KEY_UNIQUE_VISITOR + date, userNo, true);
		p.sync();
	}

	
	/**
	 * �־��� �Ⱓ�� �� �湮�� ���� ����Ѵ�.
	 * @param dateList �� �湮�ڸ� ���� �Ⱓ
	 * @return �־��� �Ⱓ�� �湮�� ��
	 */
	public Long getUVSum(String[] dateList) {
		String[] keyList = new String[dateList.length];
		int i = 0;
		for (String item : dateList) {
			keyList[i] = KEY_UNIQUE_VISITOR + item;
			i++;
		}

		jedis.bitop(BitOP.AND, "uv:event", keyList);
		return jedis.bitcount("uv:event");
	}

	private String getToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}
}
