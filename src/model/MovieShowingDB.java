package model;

import java.util.ArrayList;
import java.util.List;

public class MovieShowingDB {

	public static void main(String[] args) {
		MovieShowingDB mdb = new MovieShowingDB();
		
		List<MovieShowing> movieShowings = mdb.searchMovieShowings("    theater", "  scared kitten  ", " 2018-04-04     17:30:00  ");
		
		for(MovieShowing movieShowing : movieShowings) {
			System.out.println(movieShowing);
		}
	}
	
	public MovieShowing getMovieShowingById(int id) {
		DBAccess db = new DBAccess();
       	db.createConnection();
       	MovieShowing ms = db.getMovieShowingById(id);
       	db.closeConnection();
		return ms;
	}
	
	public List<MovieShowing> searchMovieShowings(String theaterName, String movieName, String date) {

		List<MovieShowing> movieShowings = new ArrayList<MovieShowing>();
		
	   	DBAccess db = new DBAccess();
	   	db.createConnection();
	   	
	   	movieShowings = db.getMovieShowingSearchResults(theaterName, movieName, date);
	   	
	   	db.closeConnection();
		
		return movieShowings;
	}
	
	public void updateNumberPurchasedSeats() {
		//TODO: Implement
		return;
	}

}
