package model;

//TODO: LOAD CREDITCARD INFO FROM DB UPON CREATION

public class User {
	private String firstName;
	private String lastName;	
	private String emailAddress;
	private String password;
	private String salt;
	private String saltyHash;
	private int numOfVisits;
	
	private String phoneNumber;
	private Address billingAddress;
	private Address shippingAddress;	
	private CreditCard creditCard;
	
	public User() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getNumOfVisits() {
		return numOfVisits;
	}

	public void setNumOfVisits(int numOfVisits) {
		this.numOfVisits = numOfVisits;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSaltyHash() {
		return saltyHash;
	}

	public void setSaltyHash(String saltyHash) {
		this.saltyHash = saltyHash;
	}

	@Override
	public String toString() {
		return "User-"
				+ "\nfirstName: " + firstName
				+ "\nlastName: " + lastName
				+ "\nemailAddress: " + emailAddress
				+ "\npassword: " + password
				+ "\nnumOfVisits: " + numOfVisits
				+ "\nphoneNumber: " + phoneNumber
				+ "\nbillingAddress: " + billingAddress
				+ "\nshippingAddress: " + shippingAddress
				+ "\ncreditCard: " + creditCard;
	}
		
}
