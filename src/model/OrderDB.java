package model;

public class OrderDB {

	public Order getOrderById(int id) {
		DBAccess db = new DBAccess();
       	db.createConnection();
       	Order o = db.getOrderById(id);
       	db.closeConnection();
		return o;
	}

}
