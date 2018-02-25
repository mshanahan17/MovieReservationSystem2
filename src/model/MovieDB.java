package model;

import java.util.ArrayList;
import java.util.List;

public class MovieDB {
	
	public static void main(String[] args) {
		MovieDB mdb = new MovieDB();
		
		List<Movie> movies = mdb.searchMovies("theater1", "the scared little kitten", "2018-04-04 17:30:00");
		
		for(Movie movie : movies) {
			System.out.println(movie);
		}
	}

	public List<Movie> searchMovies(String theaterName, String movieName, String dateTime) {

		List<Movie> movies = new ArrayList<Movie>();
		
	   	DBAccess db = new DBAccess();
	   	db.createConnection();
	   	
	   	movies = db.getMovieSearchResults(theaterName, movieName, dateTime);
	   	
	   	db.closeConnection();
		
		return movies;
	}

}
