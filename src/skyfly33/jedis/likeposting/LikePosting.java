package skyfly33.jedis.likeposting;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import skyfly33.jedis.likeposting.JedisHelper;

public class LikePosting {

	private Jedis jedis;
	private static final String KEY_LIKE_SET = "posting:like:";
	
	public LikePosting(JedisHelper helper) {
		this.jedis = helper.getConnection();
	}
	
	
	/**
	 * ������ ����ڰ� �Խù��� '���ƿ�'�� ǥ���Ѵ�
	 * @param postingNo �Խù� ��ȣ
	 * @param userNo ����� ��ȣ
	 * @return ����ó�� �Ǿ����� true
	 */
	public boolean like(String postingNo, String userNo) {
		return this.jedis.sadd(KEY_LIKE_SET +  postingNo, userNo ) > 0;
	}
	

	/**
	 * ������ ����ڰ� �Խù��� '���ƿ�'�� ����Ѵ�
	 * @param postingNo �Խù� ��ȣ
	 * @param userNo ����� ��ȣ
	 * @return ����ó�� �Ǿ����� true
	 */
	public boolean unLike(String postingNo, String userNo) {
		return this.jedis.srem(KEY_LIKE_SET + postingNo, userNo) > 0;
	}
	
	
	/**
	 * ������ ������� '���ƿ�' ǥ�ÿ��θ� Ȯ���Ѵ�
	 * @param postingNo �Խù� ��ȣ
	 * @param userNo ����� ��ȣ
	 * @return '���ƿ�' ó�� �Ǿ����� true
	 */
	public boolean isLiked (String postingNo, String userNo) {
		return this.jedis.sismember(KEY_LIKE_SET + postingNo, userNo);
	}
	
	
	/**
	 * �Խù��� ���� '���ƿ�' ������ �����Ѵ� (�Խù��� ���� �Ǿ��� ���)
	 * @param postingNo �Խù� ��ȣ
	 * @return �����Ǿ����� true
	 */
	public boolean deleteLikeInfo(String postingNo) {
		return this.jedis.del(KEY_LIKE_SET + postingNo) > 0; 
	}
	
	
	/**
	 * �Խù��� '���ƿ�' Ƚ���� ��ȸ�Ѵ�.
	 * @param postingNo �Խù� ��ȣ
	 * @return ���ƿ� Ƚ��
	 */
	public Long getLikeCount (String postingNo) {
		return this.jedis.scard(KEY_LIKE_SET + postingNo);
	}
	
	
	/**
	 * �־��� �Խù� ����� '���ƿ�' Ƚ���� ��ȸ�Ѵ�
	 * @param postingList ��ȸ ��� ������ ���
	 * @return '���ƿ�' Ƚ�� ���
	 */
	public List<Long> getLikeCountList(String[] postingList) {
		List<Long> result = new ArrayList<Long>();
		
		Pipeline p = jedis.pipelined();
		for (String postingNo : postingList) {
			p.scard(KEY_LIKE_SET + postingNo);
		}
		
		List<Object> pipelineResult = p.syncAndReturnAll();
		
		for (Object item : pipelineResult) {
			result.add((Long) item);
		}
		
		return result;
	}
}
