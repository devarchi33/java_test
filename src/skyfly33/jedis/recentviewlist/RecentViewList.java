package skyfly33.jedis.recentviewlist;

import java.util.List;

import redis.clients.jedis.Jedis;

public class RecentViewList {

	private Jedis jedis;
	private static final String KEY_VIEW_LIST = "recent:view:";
	private static final int listMaxSize = 30;
	private String userNo;

	public RecentViewList(JedisHelper helper, String userNo) {
		this.jedis = helper.getConnection();
		this.userNo = userNo;
	}

	
	/**
	 * �ֱ� ��ȸ ��ǰ ��Ͽ� ��ǰ�� �߰��Ѵ�.
	 * @param productNo ��ǰ��ȣ
	 * @return ����� ��ǰ ����� ����
	 */
	public Long add(String productNo) {
		Long result = this.jedis.lpush(KEY_VIEW_LIST + this.userNo, productNo);
		this.jedis.ltrim(KEY_VIEW_LIST + this.userNo, 0, listMaxSize - 1);

		return result;
	}

	
	/**
	 * �־��� ������� ����� �ֱ� ��ȸ ��ǰ ����� ��ȸ�Ѵ�.
	 * @return ��ȸ�� ��ǰ ���
	 */
	public List<String> getRecentViewList() {
		return this.jedis.lrange(KEY_VIEW_LIST + this.userNo, 0, -1);
	}

	
	/**
	 * �־��� ������ �ش��ϴ� �ֱ� ��ȸ ��ǰ ����� ��ȸ�Ѵ�.
	 * @param cnt ��ȸ�� ��ǰ����
	 * @return ��ȸ�� ��ǰ ���
	 */
	public List<String> getRecentViewList(int cnt) {
		return this.jedis.lrange(KEY_VIEW_LIST + this.userNo, 0, cnt - 1);
	}

	
	/**
	 * ���� ������ ��ǰ ����� �ִ� ������ ��ȸ�Ѵ�.
	 * @return ��ǰ ����� �ִ� ����
	 */
	public int getListMaxSize() {
		return this.listMaxSize;
	}
}
