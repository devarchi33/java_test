package test.jedis.logging;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import redis.clients.jedis.Jedis;

public class LogWriterV2 {

	private static final JedisHelper helper = JedisHelper.getInstance();
	private static final String KEY_WAS_LOG = "was:log:list";
	private static final String LOG_FILE_NAME_PREFIX = "./waslog";
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HH'.log'");

	/**
	 * ���� �������� �α׸� �о ���Ϸ� �����Ѵ�.
	 * ����Ʈ�� ����� ��� ��Ҹ� ���Ϸ� �����Ѵ�. ��Ұ� ������ �޼��尡 ����ȴ�.
	 */
	public void start() {
		Jedis jedis = helper.getConnection();
		
		while(true) {
			String log = jedis.rpop(KEY_WAS_LOG);
			if (log == null) break;
			writeFile(log);
		}
	}
	
	private void writeFile(String log) {
		try {
			if (log == null) return;
			FileWriter writer = new FileWriter(getCurrentFileName(), true);
			writer.write(log);
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
	
}
