package skyfly33.jedis.visitcount;

import static org.junit.Assert.*;
import org.junit.*;

import java.util.*;
import java.text.SimpleDateFormat;

public class VisitCountOfDayHashTest {

	static JedisHelper jedisHelper;

	@BeforeClass
	public static void setupBeforeClass() {
		jedisHelper = JedisHelper.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		jedisHelper.destroyPool();
	}
	
	@Test
	public void testAddVisit() {
		VisitCount visitCount = new VisitCount(jedisHelper);
		assertTrue(visitCount.addVisit("52") > 0);
		assertTrue(visitCount.addVisit("180") > 0);
		assertTrue(visitCount.addVisit("554") > 0);
		
		VisitCountOfDayHash visitCountOfDay = new VisitCountOfDayHash(jedisHelper);
		assertTrue(visitCountOfDay.addVisit("52") > 0);
		assertTrue(visitCountOfDay.addVisit("180") > 0);
		assertTrue(visitCountOfDay.addVisit("554") > 0);
	}

	@Test
	public void testGetVisitCountByDate() {
		String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
		VisitCountOfDayHash visitCountOfDay = new VisitCountOfDayHash(jedisHelper);
		assertNotNull(visitCountOfDay);
		
		SortedMap<String, String> visitCount = visitCountOfDay.getVisitCountByDaily("554");

		assertTrue(visitCount.size() > 0);
		assertNotNull(visitCount);
		assertNotNull(visitCount.firstKey());
		assertNotNull(visitCount.lastKey());

		System.out.println(visitCount);

		SortedMap<String, String> totalVisit = visitCountOfDay.getVisitCountByDailyTotal();
		assertTrue(totalVisit.size() > 0);
		assertNotNull(totalVisit);
		assertNotNull(totalVisit.firstKey());
		assertNotNull(totalVisit.lastKey());

		System.out.println(totalVisit);

	}
}
