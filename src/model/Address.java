package model;

public class Address {
	private String streetAddress;
	private String city;
	private String state;
	private int zip;
	
	public Address() {
		super();		
	}
	
	public String getStreetAddress() {
		return streetAddress;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getZip() {
		return zip;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setZip(int zip) {
		this.zip = zip;
	}
	
	@Override
	public String toString() {
		return "Address-"
				+ "\nstreetAddress: " + streetAddress 
				+ "\ncity: " + city
				+ "\nstate: " + state
				+ "\nzip: " + zip;
		
	}
}
