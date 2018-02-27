package model;

public class CreditCard {
	String cardHolderName;
	String cardNumber;
	double balance;
	String cardType;
	User owner;
	String cvv;
	String expirationDate;
	
	public CreditCard() {
		// TODO Auto-generated constructor stub
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	@Override
	public String toString() {
		return "CreditCard-"
				+ "\ncardHolderName: " + cardHolderName
				+ "\ncardNumber: " + cardNumber
				+ "\nbalance: " + balance
				+ "\ncardType: " + cardType
				+ "\nowner: " + owner
				+ "\ncvv: " + cvv
				+ "\nexpirationDate: " + expirationDate;
	}

}
