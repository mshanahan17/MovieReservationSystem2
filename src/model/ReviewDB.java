package model;

import java.util.List;

public class ReviewDB {

<<<<<<< HEAD
	public boolean addReview(Review r, String rating) {
=======
	public static void main(String[] args) {
//		Review r = new Review();
//		r.set
//		addReview();
		return;
	}
	
	public boolean addReview(Review r) {
>>>>>>> 83eb57f90fddd658c90e466b4bdc3087d29a08b1
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
