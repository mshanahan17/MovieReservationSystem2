package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderDB {

	public static void main(String[] args) {
		//System.out.println(getDate());
		return;
	}
	//TODO: Load up MovieShowing object upon creation from DB
	
	public Order getOrderById(int id) {
		DBAccess db = new DBAccess();
       	db.createConnection();
       	Order o = db.getOrderById(id);
       	db.closeConnection();
		return o;
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
       	db.addOrdersToUser(orders, totalCost, getDateTime());
       	
       	for(Order o : orders) {
       		db.addQuantityToOrderItemTable(o, totalCost, getDateTime());	
       	}       	
       	
       	db.closeConnection();
       	return;
	}

	public void removeOrder() {
		//TODO: Implement
		return;
	}
	
	public void updateTotalCostOfOrder() {
		//TODO: Implement
		return;
	}		
	
	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
}
