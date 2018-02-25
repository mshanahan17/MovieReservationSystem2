package model;

import java.util.ArrayList;
import java.util.List;

public class MovieDB {
	
//	public static void main(String[] args) {
//		MovieDB mdb = new MovieDB();
//		
//		List<Movie> movies = mdb.searchMovies("    theater", "  scared kitten  ", " 2018-04-04     17:30:00  ");
//		
//		for(Movie movie : movies) {
//			System.out.println(movie);
//		}
//	}
	
	public Movie getMovieById(int id) {	    
       	DBAccess db = new DBAccess();
       	db.createConnection();
       	
       	Movie m = db.getMovieById(id);
       	
       	db.closeConnection();       	
       	return m;
	}

}
