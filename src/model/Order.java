package model;

import java.util.Date;

public class Order {
	//TODO: Validate billing address
	//TODO: Confirm/Validate that the desired ticket quantity does not exceed the showroom capacity
	
	User customer;
	Date date;
	double cost;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
