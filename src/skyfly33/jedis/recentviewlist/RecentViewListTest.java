package skyfly33.jedis.recentviewlist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RecentViewListTest {

	static JedisHelper helper;
	private RecentViewList viewList;
	private static final String TEST_USER = "123";
	private int listMaxSize;

	@BeforeClass
	public static void setUpBeforeClass() {
		helper = JedisHelper.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		helper.destroyPool();
	}

	@Before
	public void setUp() throws Exception {
		this.viewList = new RecentViewList(helper, TEST_USER);
		assertNotNull(viewList);
		this.listMaxSize = viewList.getListMaxSize();
	}

	@Test
	public void testAdd() {
		for (int i = 0; i <= 50; i++) {
			this.viewList.add(String.valueOf(i));
		}
	}

	@Test
	public void checkMaxSize() {
		int storedSize = this.viewList.getRecentViewList().size();
		assertEquals(this.listMaxSize, storedSize);
	}

	@Test
	public void checkRecentSize() {
		int checkSize = 4;
		int redisSize = this.viewList.getRecentViewList(checkSize).size();
		assertEquals(redisSize, checkSize);
	}

	@Test
	public void checkProductNo() {
		this.viewList.add("50");
		assertEquals(this.viewList.getRecentViewList().size(), this.listMaxSize);

		List<String> itemList = this.viewList.getRecentViewList(5);
		assertEquals("50", itemList.get(0));
		for (String item : itemList) {
			System.out.println(item);
		}
	}
}
