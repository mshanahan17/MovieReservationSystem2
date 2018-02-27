package model;

import java.util.List;

public class OrderDB {

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

	public void addOrdersToUser(User u, List<Order> orders) {
		//TODO: Implement
		return;
	}
}
