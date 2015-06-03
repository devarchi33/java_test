package skyfly33.jedis.uniquevisit;

import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UniqueVisitTest {

	static JedisHelper helper;
	private UniqueVisit uniqueVisit;
	private static final int VISIT_COUNT = 1000;
	private static final int TOTAL_USER = 10000000;
	private static final String TEST_DATE = "19500101";
	static Random rand = new Random();

	@BeforeClass
	public static void setupBeforeClass() throws Exception {
		helper = JedisHelper.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		helper.destroyPool();
	}

	@Before
	public void setUp() {
		uniqueVisit = new UniqueVisit(helper);
		assertNotNull(uniqueVisit);
	}

	@Test
	public void testRandomPV() {
		int pv = uniqueVisit.getPVCount(getToday());
	}

	@Test
	public void testInvalidPV() {
		assertEquals(0, uniqueVisit.getPVCount(TEST_DATE));
		assertEquals(new Long(0), uniqueVisit.getUVCount(TEST_DATE));
	}

	@Test
	public void testPV() {
		int result = uniqueVisit.getPVCount(getToday());
		uniqueVisit.visit(65487);

		assertEquals(result + 1, uniqueVisit.getPVCount(getToday()));

		System.out.println("PV result : " + result);
	}

	@Test
	public void testUV() {
		uniqueVisit.visit(65487);
		Long result = uniqueVisit.getUVCount(getToday());
		uniqueVisit.visit(65487);

		assertEquals(result, uniqueVisit.getUVCount(getToday()));

		System.out.println("UV result : " + result);
	}

	private String getToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}

}
