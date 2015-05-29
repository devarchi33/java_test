package skyfly33.apache_commons_pool;

import org.apache.commons.pool.BasePoolableObjectFactory;

public class MyPoolableObjectFactory extends BasePoolableObjectFactory{

	@Override
	public Object makeObject() throws Exception {
		// TODO Auto-generated method stub
		return new MyPoolableObject();
	}

}
