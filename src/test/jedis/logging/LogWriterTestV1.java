package test.jedis.logging;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class LogWriterTestV1 {

	static JedisHelper helper;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		helper = JedisHelper.getInstance();
	}

	@AfterClass
	public static void tearDownAfterCalss() throws Exception {
		helper.destroyPool();
	}

	@Test
	public void testLogger() {
		LogWriter writer = new LogWriter();
		writer.start();
	}
}
