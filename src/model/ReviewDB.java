package model;

public class ReviewDB {

	public Review getReviewById(int id) {
		DBAccess db = new DBAccess();
       	db.createConnection();
       	Review r = db.getReviewById(id);
       	db.closeConnection();
		return r;
	}

}
