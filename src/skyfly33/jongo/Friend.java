package skyfly33.jongo;

import java.util.List;

import org.bson.types.ObjectId;

public class Friend {
   private ObjectId id;
   private String name;
   private int age;
   private List<String> phones;
   private Address address;
   
	public Friend() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Friend( String name, int age, List<String> phones,
			Address address) {
		super();
		this.name = name;
		this.age = age;
		this.phones = phones;
		this.address = address;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	

   
}
