package model;

public class Transaction {
	//TODO: Validate credit card account details
	double balance;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Transaction-"
				+ "\nbalance: " + balance;
	}
}
