package skyfly33.jedis.visitcount;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;

public class VisitCountOfDayTest {

	static JedisHelper jedisHelper;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		jedisHelper = JedisHelper.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		jedisHelper.destroyPool();
	}

	@Test
	public void testAddVisit() {
		VisitCountOfDay visitCountOfDay = new VisitCountOfDay(jedisHelper);
		assertNotNull(visitCountOfDay);

		assertTrue(visitCountOfDay.addVisit("52") > 0);
		assertTrue(visitCountOfDay.addVisit("180") > 0);
		assertTrue(visitCountOfDay.addVisit("554") > 0);
	}

	@Test
	public void testGetVisitCountOfDay() {
		String[] dateList = { "20150527", "20150528", "20150529",
				"20150530", "20150531" };
		VisitCountOfDay visitCountOfDay = new VisitCountOfDay(jedisHelper);
		List<String> result = visitCountOfDay.getVisitCountByDate("52",
				dateList);

		assertNotNull(result);
		assertTrue(result.size() == 5);

		System.out.println(visitCountOfDay.getVisitTotalCount("20150527"));
		System.out.println(visitCountOfDay.getVisitCountByDate("52", dateList));
	}
}
