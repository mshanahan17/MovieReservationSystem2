package model;

import java.util.Date;

public class Order {
	//TODO: Validate billing address
	//TODO: Confirm/Validate that the desired ticket quantity does not exceed the showroom capacity
	
	User customer;
	String date;
	double cost;
	String billingAddress;
	String creditCardNumber;
	MovieShowing movieShowing;
	int ticketQuantity;
	
	//TODO: Load up MovieShowing object upon creation from DB
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public MovieShowing getMovieShowing() {
		return movieShowing;
	}

	public void setMovieShowing(MovieShowing movieShowing) {
		this.movieShowing = movieShowing;
	}

	public int getTicketQuantity() {
		return ticketQuantity;
	}

	public void setTicketQuantity(int ticketQuantity) {
		this.ticketQuantity = ticketQuantity;
	}

	@Override
	public String toString() {
		return "Order-"
			+ "\ncustomer: " + customer
			+ "\ndate: " + date
			+ "\ncost: " + cost
			+ "\nbillingAddress: " + billingAddress
			+ "\ncreditCardNumber: " + creditCardNumber;
	}
}
