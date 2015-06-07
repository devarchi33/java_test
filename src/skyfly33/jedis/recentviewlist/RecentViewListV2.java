package skyfly33.jedis.recentviewlist;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class RecentViewListV2 {

	private Jedis jedis;
	private static final String KEY_VIEW_SET = "recent:view:zset:";
	private static final int listMaxSize = 30;
	private String key;

	public RecentViewListV2(JedisHelper helper, String userNo) {
		this.jedis = helper.getConnection();
		this.key = KEY_VIEW_SET + userNo;
	}

	
	/**
	 * �ֱ� ��ȸ ��ǰ ��Ͽ� ��ǰ�� �߰��Ѵ�.
	 * @param productNo ��ǰ��ȣ
	 * @return ����� ��ǰ ��� ����
	 */
	public Long add(String productNo) {
		Long result = this.jedis.zadd(this.key, System.nanoTime(), productNo);
		this.jedis.zremrangeByRank(this.key, -(listMaxSize + 1),
				-(listMaxSize + 1));

		return result;
	}

	
	/**
	 * �־��� ������� ����� �ֱ� ��ȸ ��ǰ ����� ��ȸ�Ѵ�.
	 * @return ��ȸ�� ��ǰ ���
	 */
	public Set<String> getRecentViewList() {
		return this.jedis.zrevrange(this.key, 0, -1);
	}

	
	/**
	 * �־��� ������ �ش��ϴ� �ֱ� ��ȸ ��ǰ ����� ��ȸ�Ѵ�
	 * @param cnt ��ȸ�� ��ǰ ����
	 * @return ��ȸ�� ��ǰ ���
	 */
	public Set<String> getRecentViewList(int cnt) {
		return this.jedis.zrevrange(this.key, 0, cnt - 1);
	}

	
	/**
	 * ���� ������ ��ǰ ����� �ִ� ������ ��ȸ�Ѵ�
	 * @return ��ǰ ����� �ִ� ����
	 */
	public int getListMaxSize() {
		return this.listMaxSize;
	}
}
