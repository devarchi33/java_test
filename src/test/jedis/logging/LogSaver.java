package test.jedis.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import redis.clients.jedis.Jedis;

public class LogSaver {

	private static final String KEY_WAS_LOG = "was:log";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS");
	JedisHelper helper;

	/**
	 * ���𽺿� �α׸� ����ϱ� ���� Logger�� ������
	 * 
	 * @param helper ���� ���� ��ü
	 */
	public LogSaver(JedisHelper helper) {
		this.helper = helper;
	}
	
	public LogSaver() {
		
	}

	/**
	 * ���𽺿� �α׸� ����ϴ� �޼���
	 * 
	 * @param log ������ ���ڿ�
	 * 
	 * @return ����� ���� ���𽺿� ����� �α� ���ڿ��� ����
	 */
	public Long log(String log) {
		Jedis jedis = this.helper.getConnection();
		Long rtn = jedis.append(KEY_WAS_LOG, sdf.format(new Date()) + log
				+ "\n");

		this.helper.returnResource(jedis);
		return rtn;
	}
}
