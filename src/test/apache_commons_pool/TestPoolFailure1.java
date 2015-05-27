package test.apache_commons_pool;

import org.apache.commons.pool.impl.GenericObjectPool;

public class TestPoolFailure1 {

	public static void main(String[] args) throws Exception {
		
		
		//Object Pool 자체를 매번 생성하고 있음. 
		for(int i = 0; i <10; i++){
			GenericObjectPool genericObjectPool = new GenericObjectPool(new MyPoolableObjectFactory());
			MyPoolableObject obj = (MyPoolableObject)genericObjectPool.borrowObject();
			System.out.println("i : " + i);
			System.out.println(obj + ", count : " + obj.getCount());
		}
	}
}
