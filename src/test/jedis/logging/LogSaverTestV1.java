package test.jedis.logging;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class LogSaverTestV1 {

	static JedisHelper helper;
	static LogSaver logger;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		helper = JedisHelper.getInstance();
		logger = new LogSaver(helper);
	}

	/**
	 * LogSaver�� log(str)���� �α׸� �ۼ��ϰ� Ŀ�ؼ��� ��ȯ�ϱ� ����
	 * destroy pool�� �ϸ鼭 connection�� �ٽ� �ѹ� ��ȯ �õ��� �Ҷ� 
	 * IllegalStateException�� �߻���.
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//helper.destroyPool();
	}

	@Test
	public void testLogger() {
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < 100; i++) {
			assertTrue(logger.log("This is test log message " + i) > 0);

			try {
				Thread.sleep(random.nextInt(50));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}
