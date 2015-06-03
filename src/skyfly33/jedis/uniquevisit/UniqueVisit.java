package skyfly33.jedis.uniquevisit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.Date;
import java.text.SimpleDateFormat;

public class UniqueVisit {

	private Jedis jedis;
	private static final String KEY_PAGE_VIEW = "page:view:";
	private static final String KEY_UNIQUE_VISITOR = "unique:visitors:";

	public UniqueVisit(JedisHelper helper) {
		this.jedis = helper.getConnection();
	}

	
	/**
	 * Ư�� ������� �� �湮 Ƚ���� ���� �湮 Ƚ���� ������Ų��.
	 * @param userNo ����ڹ�ȣ.
	 */
	public void visit(int userNo) {
		Pipeline p = this.jedis.pipelined();
		p.incr(KEY_PAGE_VIEW + getToday());
		p.setbit(KEY_UNIQUE_VISITOR + getToday(), userNo, true);
		p.sync();
	}

	
	/**
	 * ��û�� ��¥�� ���� �湮�� ���� ��ȸ�Ѵ�.
	 * @param date ��ȸ��� ��¥
	 * @return ���� �湮�� ��
	 */
	public int getPVCount(String date) {
		int result = 0;
		try {
			result = Integer.parseInt(this.jedis.get(KEY_PAGE_VIEW + date));
		} catch (Exception e) {
			// TODO: handle exception
			result = 0;
		}

		return result;
	}

	
	/**
	 * ��û�� ��¥�� �� �湮�� ���� ��ȸ�Ѵ�
	 * @param date ��ȸ ��� ��¥
	 * @return �� �湮�� ��
	 */
	public Long getUVCount(String date) {
		return jedis.bitcount(KEY_UNIQUE_VISITOR + date);
	}

	private String getToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}

}
