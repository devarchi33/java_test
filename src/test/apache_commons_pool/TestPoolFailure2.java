package test.apache_commons_pool;

import org.apache.commons.pool.impl.GenericObjectPool;

public class TestPoolFailure2 {

	/* Object Pool은 1회만 생성 했지만 Pool 안에서 객체를 생성하고 해제 하지 않고 있음. 
	 * default max object countrk  8이므로 8개의 객체 생성이후에는 객체가 해제되길 기다리면서 
	 * process가 무한대기 상태에 빠짐. 
	 */
	public static void main(String[] args) throws Exception{
		GenericObjectPool genericObjectPool = new GenericObjectPool(new MyPoolableObjectFactory());
		
		for(int i = 0; i < 10; i++){
			MyPoolableObject obj = (MyPoolableObject)genericObjectPool.borrowObject();
			System.out.println("i : "  + i);
			System.out.println(obj + ", count : " + obj.getCount());
		}
	}
}
