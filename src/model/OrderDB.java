package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

public class OrderDB {
	
	//TODO: Load up MovieShowing object upon creation from DB
	
	public Order getOrderById(int id) {
		DBAccess db = new DBAccess();
       	db.createConnection();
       	Order o = db.getOrderById(id);
       	db.closeConnection();
		return o;
	}
	
	public List<Order> getOrdersByOrderId(int orderId) {
		DBAccess db = new DBAccess();
       	db.createConnection();
       	
       	List<Order> orders = db.getOrdersByOrderId(orderId);
       	
       	db.closeConnection();
		return orders;
	}
	
	public List<Order> getOrdersByUser(User u) {
		DBAccess db = new DBAccess();
       	db.createConnection();
       	List<Order> orders = db.getOrdersByUser(u);
       	db.closeConnection();
		return orders;
	}	

	public void addOrdersToUser(List<Order> orders, double totalCost) {
		//TODO: Implement
		// totalCost gets stored into the Order table into the totalCost attribute		
		
		DBAccess db = new DBAccess();
       	db.createConnection();
       	
       	String todayDate = getDateTime();
       	
       	//System.out.println(orders.get(0).getCustomer());
       	
       	int orderId = db.addOrderToUser(orders.get(0), totalCost, getDateTime());
       	
       	//System.out.println("\n-----------------\nORDER ID = " + orderId + "\n-------------------\n");
       	
       	
       	for(Order o : orders) {
//       		System.out.println("========================\nITERATION\n================================\n");
//       		System.out.println("MIRROR1: \n-----------------------------------------\n" + o);
       		db.addQuantityToOrderItemTable(o, totalCost, getDateTime(), orderId);
       	}
       	
       	db.closeConnection();
       	return;
	}

	public boolean removeOrderItem(Order o) {
		
		if(this.isPassedMovieShowingTime(o.getMovieShowing().getStartTime(), this.getDateTime())) {
			return false;
		} else {
			DBAccess db = new DBAccess();
	       	db.createConnection();
	       	db.removeOrderItem(o);
	       	db.updateTotalCostOfOrder(o, o.getMovieShowing().getCost() * o.getTicketQuantity() * -1);
	       	db.refundCreditCard(o, o.getMovieShowing().getCost() * o.getTicketQuantity());
	       	db.updateMovieShowingSeatsPurchased(o.getMovieShowing(), -o.getTicketQuantity());
	       	db.attemptRemoveOrder(o); // TODO: Lol...
			db.closeConnection();
	       	return true;
		}		
		
	}
	
	public void updateTotalCostOfOrder(Order o, double changeInCost) {
		DBAccess db = new DBAccess();
       	db.createConnection();
       	db.updateTotalCostOfOrder(o, changeInCost);
       	db.refundCreditCard(o, changeInCost * -1);
       	db.closeConnection();
		return;
	}
	
	public void updateCreditCardBalance(Order o, double changeInCost) {
		//TODO: Implement
		DBAccess db = new DBAccess();
       	db.createConnection();
       	db.refundCreditCard(o, changeInCost);
       	db.closeConnection();
		return;
	}
	
	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private boolean isPassedMovieShowingTime(String movieShowingDateTime, String nowDateTime) {
		//TODO: Implement
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date mdt = null;
		Date ndt = null;
		try {
			mdt = dateFormat.parse(movieShowingDateTime);
			ndt = dateFormat.parse(nowDateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		
		return ndt.after(mdt);
	}
	
	private void addTestOrdersToDB() {
		
		List<Order> orders = new ArrayList<Order>();
		OrderDB odb = new OrderDB();
		UserDB udb = new UserDB();
		MovieShowingDB msdb = new MovieShowingDB();
		
		Order o1 = new Order();
		User u = udb.getUserById(6);
		
		o1.setBillingAddress(u.getBillingAddress().getStreetAddress());
		o1.setCost(100);
		o1.setCreditCardNumber(u.getCreditCard().getCardNumber());
		o1.setCustomer(u);
		o1.setDate("2200-11-11");
		o1.setMovieShowing(msdb.getMovieShowingById(4));
		o1.setTicketQuantity(1); 
		
		Order o2 = new Order();
		o2.setBillingAddress(u.getBillingAddress().getStreetAddress());
		o2.setCost(100);
		o2.setCreditCardNumber(u.getCreditCard().getCardNumber());
		o2.setCustomer(udb.getUserById(6));
		o2.setDate("2200-11-11");
		o2.setMovieShowing(msdb.getMovieShowingById(9));
		o2.setTicketQuantity(1); 
		
		orders.add(o1);
		orders.add(o2);
		
		odb.addOrdersToUser(orders, 19);
	}
	
}
