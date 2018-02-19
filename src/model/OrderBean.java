package model;

import java.util.Date;

public class OrderBean {
	//TODO: Validate billing address
	//TODO: Confirm/Validate that the desired ticket quantity does not exceed the showroom capacity
	
	UserBean customer;
	Date date;
	double cost;
	
	public OrderBean() {
		// TODO Auto-generated constructor stub
	}

	public UserBean getCustomer() {
		return customer;
	}

	public void setCustomer(UserBean customer) {
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
