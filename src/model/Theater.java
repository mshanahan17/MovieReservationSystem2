package model;

public class Theater {
	String name;
	Address address;
	User owner; 
	
	public Theater() {
		super();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString() {
		return "Theater-"
			+ "\nname: " + name
			+ "\naddress: " + address
			+ "\nowner: " + owner;
	}
}
