package test.jedis.logging;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import redis.clients.jedis.Jedis;

public class LogWriter {

	private static final JedisHelper helper = JedisHelper.getInstance();
	private static final String KEY_WAS_LOG = "was:log";
	private static final String osName = System.getProperty("os.name");
	private static final String homePath = System.getProperty("user.home");
	private static final String LOG_FILE_NAME_PREFIX = osName.startsWith("M") ? homePath.concat("/Desktop") : homePath.concat("\\Desktop");
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyMMdd-HH'.log'");
	private static final int WATING_TERM = 5000;

	/**
	 * ���� �������� �α׸� �о ���Ϸ� �����Ѵ�. ���α׷��� ����Ǳ� ������ ������ �����Ѵ�.
	 */
	public void start() {
		Random random = new Random();
		Jedis jedis = helper.getConnection();
		while (true) {
			writeFile(jedis.getSet(KEY_WAS_LOG, ""));

			try {
				Thread.sleep(random.nextInt(WATING_TERM));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	/**
	 * �޼��尡 ȣ��� �ð��� �ش��ϴ� �α� ���ϸ��� �����Ѵ�.
	 * 
	 * @return ���� �ð��� �ش��ϴ� �α� ���ϸ�
	 */
	public String getCurrentFileName() {
		return LOG_FILE_NAME_PREFIX + sdf.format(new Date());
	}

	private void writeFile(String log) {
		try {
			FileWriter writer = new FileWriter(getCurrentFileName(), true);

			writer.write(log);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
