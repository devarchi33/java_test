package skyfly33.apache_commons_pool;

public class MyPoolableObject {

	private static int count = 0;

	public MyPoolableObject() {
		count++;
	}

	public int getCount() {
		return this.count;
	}
}
