package model;

import java.util.ArrayList;
import java.util.List;

public class TheaterDB {

//	public static void main(String[] args) {
//		TheaterDB tdb = new TheaterDB();
//		
//		List<Theater> theaters = tdb.getTheaters(); 
//		
//		for(Theater theater : theaters) {
//			System.out.println(theater + "\n\n");
//			
//		}
//	}
	
	public List<Theater> getTheaters() {
		List<Theater> theaters = new ArrayList<Theater>();
		
	   	DBAccess db = new DBAccess();
	   	db.createConnection();
	   	
	   	theaters = db.getAllTheaters();
	   	
	   	db.closeConnection();
		return theaters;
	}

}
