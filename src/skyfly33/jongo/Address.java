package skyfly33.jongo;

import org.bson.types.ObjectId;

public class Address {
   private ObjectId id;
   private String city;
   private Integer zip;
   private String street;
   
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String city, Integer zip, String street) {
		super();
		this.city = city;
		this.zip = zip;
		this.street = street;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	   
	

}
