package model;

public class TheatreBean {
	String name;
	Address address;
	UserBean user; //TODO: Is this the correct class it should be referencing?
	
	public TheatreBean() {
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
	
	public UserBean getUser() {
		return user;
	}
	
	public void setUser(UserBean user) {
		this.user = user;
	}
	
	
}
