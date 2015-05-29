package skyfly33.jedis.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import redis.clients.jedis.Jedis;

public class LogSaverV2 {

	private static final String KEY_WAS_LOG = "was:log:list";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS");
	JedisHelper helper;

	/**
	 * 레디스에 로그를 기록하기 위한 Logger의 생성자
	 * 
	 * @param helper
	 */
	public LogSaverV2(JedisHelper helper) {
		this.helper = helper;
	}

	public Long log(String log) {
		Jedis jedis = this.helper.getConnection();
		Long rtn = jedis
				.lpush(KEY_WAS_LOG, sdf.format(new Date()) + log + "\n");

		this.helper.returnResource(jedis);

		return rtn;
	}

}
