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
	 * LogSaver의 log(str)에서 로그를 작성하고 커넥션을 반환하기 때문
	 * destroy pool을 하면서 connection을 다시 한번 반환 시도를 할때 
	 * IllegalStateException이 발생함.
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
