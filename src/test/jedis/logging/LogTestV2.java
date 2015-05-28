package test.jedis.logging;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.*;

public class LogTestV2 {

	static JedisHelper helper;
	private static final int WATING_TERM = 5000;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		helper = JedisHelper.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		helper.destroyPool();
	}

	@Test
	public void testSaver() {
		Random random = new Random(System.currentTimeMillis());
		LogSaverV2 saver = new LogSaverV2(helper);
		for (int i = 0; i < 100; i++) {
			assertTrue(saver.log(i + ", This is new test log Message") > 0);

			try {
				Thread.sleep(random.nextInt(50));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}

	@Test
	public void testWriter() {
		LogWriterV2 writer = new LogWriterV2();

		for (int i = 0; i < 5; i++) {
			writer.start();

			try {
				Thread.sleep(WATING_TERM);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
