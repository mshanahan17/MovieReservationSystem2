package model;

public class Address {
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	
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

	public String getZip() {
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
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@Override
	public String toString() {
		return "streetAddress: " + streetAddress 
				+ "\ncity: " + city
				+ "\nstate: " + state
				+ "\nzip: " + zip;
		
	}
}
