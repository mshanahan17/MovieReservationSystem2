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

	public void removeOrderItem(Order o) {
		DBAccess db = new DBAccess();
       	db.createConnection();
       	db.removeOrderItem(o);
       	db.updateTotalCostOfOrder(o, o.getCost() * -1);
       	db.closeConnection();
       	return;
	}
	
	public void updateTotalCostOfOrder(Order o, double changeInCost) {
		DBAccess db = new DBAccess();
       	db.createConnection();
       	db.updateTotalCostOfOrder(o, changeInCost);
       	db.closeConnection();
		return;
	}		
	
	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
}
