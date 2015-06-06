package skyfly33.jedis.uniquevisit;

import static org.junit.Assert.*;
import org.junit.*;

public class UniqueVisitV2Test {

	static JedisHelper helper;
	private UniqueVisitV2 uniqueVistV2;
	private static final int TOTAL_USER = 100000000;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		helper = JedisHelper.getInstance();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		helper.destroyPool();
	}
	
	@Before
	public void setUp() throws Exception{
		this.uniqueVistV2 = new UniqueVisitV2(helper);
		assertNotNull(uniqueVistV2);
		
		this.uniqueVistV2.visit(7, "20150604");
		this.uniqueVistV2.visit(11, "20150604");
		this.uniqueVistV2.visit(15, "20150604");
		this.uniqueVistV2.visit(TOTAL_USER, "20150604");
		
		this.uniqueVistV2.visit(7, "20150605");
		this.uniqueVistV2.visit(9, "20150605");
		this.uniqueVistV2.visit(11, "20150605");
		this.uniqueVistV2.visit(15, "20150605");
		this.uniqueVistV2.visit(TOTAL_USER, "20150605");
		
		this.uniqueVistV2.visit(7, "20150606");
		this.uniqueVistV2.visit(12, "20150606");
		this.uniqueVistV2.visit(13, "20150606");
		this.uniqueVistV2.visit(15, "20150606");
		this.uniqueVistV2.visit(TOTAL_USER, "20150606");

	}
	
	@Test
	public void testUVSum() {
		String[] dateList ={"20150604", "20150605" ,"20150606"};
		assertEquals(new Long(3), this.uniqueVistV2.getUVSum(dateList));
	}
}
