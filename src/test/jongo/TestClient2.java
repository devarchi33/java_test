package test.jongo;

import java.net.UnknownHostException;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.mongodb.Mongo;

public class TestClient2 {

	public static void main(String[] args) throws UnknownHostException {
		Mongo mongo = new Mongo("192.168.0.82",10000);
		Jongo jongo = new Jongo(mongo.getDB("test"));
		MongoCollection zipcodes = jongo.getCollection("zipcodes");

		//db.zipcodes.find({city:'SEATTLE', state:'WA'})
		Iterable<ZipCode> all = zipcodes.find("{city:#, state:#}", "SEATTLE", "WA")
		                          .skip(2).limit(3).as(ZipCode.class);
		for (ZipCode z : all) {
			System.out.println(z.getCity() + ": [" + z.getLoc().get(0) + 
					", " +z.getLoc().get(1) + "]");
		}

	}

}
