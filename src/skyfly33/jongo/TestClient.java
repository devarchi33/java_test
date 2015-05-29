package skyfly33.jongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.mongodb.Mongo;

public class TestClient {

	public static void main(String[] args) throws UnknownHostException {
		Mongo mongo = new Mongo("192.168.0.82", 10000);
		Jongo jongo = new Jongo(mongo.getDB("test"));
		MongoCollection friends = jongo.getCollection("friend");

		// 데이터 추가
		for (int i = 0; i < 10; i++) {
			Address address = new Address("서울", 333111, "역삼");
			List<String> phones = new ArrayList<String>();
			phones.add("011-222-333" + i);
			phones.add("011-333-555" + i);

			Friend f = new Friend("gdhong" + i, 20 + i, phones, address);
			friends.save(f);
		}
		System.out.println("자료 추가 완료");

	}

}
