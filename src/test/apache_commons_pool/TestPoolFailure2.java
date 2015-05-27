package test.apache_commons_pool;

import org.apache.commons.pool.impl.GenericObjectPool;

public class TestPoolFailure2 {

	/* Object Pool�� 1ȸ�� ���� ������ Pool �ȿ��� ��ü�� �����ϰ� ���� ���� �ʰ� ����. 
	 * default max object countrk  8�̹Ƿ� 8���� ��ü �������Ŀ��� ��ü�� �����Ǳ� ��ٸ��鼭 
	 * process�� ���Ѵ�� ���¿� ����. 
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
