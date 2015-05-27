package test.apache_commons_pool;

import org.apache.commons.pool.impl.GenericObjectPool;

public class TestPoolSuccess {

	public static void main(String[] args) throws Exception {

		GenericObjectPool genericObjectPool = new GenericObjectPool(
				new MyPoolableObjectFactory());
		for (int i = 0; i < 10; i++) {
			MyPoolableObject obj = (MyPoolableObject) genericObjectPool
					.borrowObject();
			System.out.println("i : " + i);
			System.out.println(obj + "count : " + obj.getCount());

			genericObjectPool.returnObject(obj);
		}
	}
}
