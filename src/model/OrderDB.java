package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDB {

	public static void main(String[] args) {
		
		OrderDB odb = new OrderDB();	
		UserDB udb = new UserDB();
		
		List<Order> orders = odb.getOrdersByUser(udb.getUserById(3));
		
		for(Order o : orders) {
			System.out.println("ORDER\n----------------\n " + o);
		}
		
		
//		OrderDB odb = new OrderDB();	
//		UserDB udb = new UserDB();		
//		DBAccess db = new DBAccess();
//		MovieShowingDB msdb = new MovieShowingDB();
//		
//       	db.createConnection();
//       	
//		List<Order> orders = new ArrayList<Order>();
//		Order o1 = new Order();
//		o1.setBillingAddress("blahblahblah");
//		o1.setCost(25);
//		o1.setCreditCardNumber("1111222233334444");
//		o1.setCustomer(udb.getUserById(3));
//		o1.setDate("1111-11-11");
//		o1.setMovieShowing(msdb.getMovieShowingById(4));
//		o1.setTicketQuantity(3);
//				
//		
//		System.out.println(o1.getCustomer());
//		System.exit(0);
//		
//		orders.add(o1);				
//		
//       	odb.addOrdersToUser(orders, 55);
//       	       	
//       	db.closeConnection();		
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
       	int orderId = db.addOrderToUser(orders.get(0), totalCost, getDateTime());
       	
       	//System.out.println("\n-----------------\nORDER ID = " + orderId + "\n-------------------\n");
       	
       	for(Order o : orders) {
       		db.addQuantityToOrderItemTable(o, totalCost, getDateTime(), orderId);
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
