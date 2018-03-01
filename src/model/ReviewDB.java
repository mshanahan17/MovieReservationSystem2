package model;

import java.util.List;

public class ReviewDB {

	public boolean addReview(Review r, String rating) {
		DBAccess db = new DBAccess();
       	db.createConnection();
       	boolean success = db.addReview(r, rating);
       	db.closeConnection();
		return success;
	}
	
	public Review getReviewById(int id) {
		DBAccess db = new DBAccess();
       	db.createConnection();
       	Review r = db.getReviewById(id);
       	db.closeConnection();
		return r;
	}
	
	public List<Review> getReviewsByMovieTitle(String movieTitle) {
					
		DBAccess db = new DBAccess();
       	db.createConnection();
       	
       	List<Review> reviews = db.getReviewsByMovieTitle(movieTitle);
       	
       	db.closeConnection();
		return reviews;
	}
}
