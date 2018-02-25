package model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class DBAccess {
	private Connection conn = null;
	private PreparedStatement ps = null;
	
	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	// static final String DB_URL = "jdbc:mysql://localhost/MVCApp"; 
	private static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/mshanahan";

	// Database credentials
	private static final String USER = "mshanahan"; // Replace with your CSE_LOGIN
	private static final String PASS = "k8ErVH";   // Replace with your CSE MySQL_PASSWORD
	
	public static void main(String[] args) { 
		
//		DBAccess dba = new DBAccess();
//		System.out.println(dba.formatSearchString("theater1   "));
//		System.out.println(dba.formatSearchString(" scared kitten    "));
//		System.out.println(dba.formatSearchString("2018-04-04 17:30:00"));
		
//       	DBAccess dba = new DBAccess();
//       	dba.createConnection();       	
//		User u = new User();
//		u.setFirstName("Catman");
//		u.setLastName("Pages");
//		u.setEmailAddress("catman@gmail.com");
//		u.setPassword("333");
//		dba.addSingleUser(u);
//		dba.closeConnection();
//		
//	   	DBAccess db = new DBAccess();
//	   	db.createConnection();
//	   	User user = db.getUserByEmailAddress("catman@gmail.com");
//	   	System.out.println(user.toString());
//	   	db.closeConnection();
				
	}
	
	public void addSingleUser(User user) {
		  
		try {
		  
		  String firstName = user.getFirstName();
		  String lastName = user.getLastName();
		  String emailAddress = user.getEmailAddress();
		  String password = user.getPassword();
		  int numOfVisits = user.getNumOfVisits();

		  String sql = "INSERT INTO User (FirstName, LastName, EmailAddress, Password, NumOfVisits) VALUES (?, ?, ?, ?, ?)";
		  
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setString(1, firstName);
		  ps.setString(2, lastName);
		  ps.setString(3, emailAddress);
		  ps.setString(4, password);
		  ps.setInt(5, numOfVisits);
		  
		  ps.executeUpdate();
		  
		  ps.close();
		  
		  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	}
	
	
	public boolean userExistsByEmailAddress(String emailAddress) {
		boolean userExists = false;
		
		String sql = "SELECT COUNT(EmailAddress) FROM User WHERE EmailAddress = ?";
	    PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, emailAddress);
			
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){	
				if(Integer.parseInt(rs.getString(1)) > 0) {
					userExists = true;
				}    
		    }
			
			rs.close();
			ps.close();
		        
		} catch (SQLException e) {
			System.out.println("ERROR: Blah");
			e.printStackTrace();
		}
		
		return userExists;
	}
	
	public boolean userExistsByPassword(String password) {
		boolean userExists = false;
		String sql = "SELECT COUNT(EmailAddress) FROM User WHERE EmailAddress = ?";
	    PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){	
				if(Integer.parseInt(rs.getString(1)) > 0) {
					userExists = true;
				}    
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userExists;
	}
	
	public User getUserByEmailAddress(String emailAddress) {
		String sql = "SELECT * from User WHERE EmailAddress = ?";
	    PreparedStatement ps;
	   
	    User user = new User();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, emailAddress);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setEmailAddress(rs.getString("EmailAddress"));
				user.setPassword(rs.getString("Password"));
				//TODO: Get the rest of the user data loaded into the object
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		if(user.getFirstName() == null) {
			return null;
		} else {
			return user;	
		}
				
	}

	public User getUserById(int Id) {
		String sql = "SELECT * from User WHERE Id = ?";
	    PreparedStatement ps;
	   
	    User user = new User();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setEmailAddress(rs.getString("EmailAddress"));
				user.setPassword(rs.getString("Password"));
				//TODO: Get the rest of the user data stored in the object
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public void displayAllUsers() {
		String sql = "SELECT * from User";
	    Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			
			while (rs.next()){
		        System.out.println(rs.getString(1) + " " + rs.getString(2) +  " " + rs.getString(3)
		        		+ " " + rs.getString(4) + " " + rs.getString(5));
		    }
			
		    stat.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Theater> getAllTheaters() {
		String sql = "SELECT * from TheaterBuilding";
	    PreparedStatement ps;
	   
	    List<Theater> theaters = new ArrayList<Theater>();
	    
		try {
			ps = conn.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
					
			    Theater t = new Theater();
			    Address a = new Address();
			    User owner = new User();
			    
				a.setStreetAddress(rs.getString("Address"));
				a.setCity(rs.getString("City"));
				a.setState(rs.getString("State"));
				a.setZip(rs.getInt("PostalCode"));
				
				t.setAddress(a);
				t.setName(rs.getString("Name"));
				
				int ownerID = rs.getInt("ownerID");
				owner = getUserById(ownerID);
				t.setOwner(owner);
				
				theaters.add(t);				
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return theaters;
	}

	public List<MovieShowing> getMovieShowingSearchResults(String theaterName, String movieName, String date) {
		//TODO: Finish this
		String sql = "select * from MovieShowing ms\n"
				+ "join Movie m on ms.movieID = m.Id\n"
				+ "join Showroom sr on ms.showroomID = sr.Id\n" 
				+ "join TheaterBuilding tb on sr.theaterBuilding = tb.Id\n" 
				+ "where m.`Movie name` like ? \n" 
				+ "and tb.name like ?\n"
				+ "and ms.StartTime like ?";
		
	    PreparedStatement ps;
	   
	    List<MovieShowing> searchResults = new ArrayList<MovieShowing>();
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, formatSearchString(movieName));
			ps.setString(2, formatSearchString(theaterName));
			ps.setString(3, formatSearchString(date));
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				MovieShowing ms = new MovieShowing();
				ms.setCost(rs.getDouble("Price"));
				ms.setNumOfPurchasedSeats(rs.getInt("NumberPurchased"));				
				ms.setStartTime(rs.getString("StartTime"));
				ms.setEndTime(rs.getString("EndTime"));
				
				int movieId = rs.getInt("movieID");				
				Movie m = getMovieById(movieId);
				ms.setMovie(m);
				
				int showroomId = rs.getInt("showroomID");
				Showroom sr = getShowroomById(showroomId);
				ms.setShowroom(sr);
				
				searchResults.add(ms);
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return searchResults;
	}
	
	public Movie getMovieById(int id) {
		String sql = "select * from Movie where Id = ?";
		
	    PreparedStatement ps;
	   
	    Movie m = new Movie();
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {				
				m.setTitle(rs.getString("Movie name"));
				m.setDescription(rs.getString("Description"));				
				m.setThumbnail(blobToString(rs.getBlob("Thumbnail")));
				m.setRating(rs.getString("Rating"));
				
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return m;
	}
	
	public Showroom getShowroomById(int id) {
		//TODO: Implement
		return null;
	}
	
	public Review getReviewById(int id) {
		//TODO: Implement
		return null;
	}
	
	public Order getOrderById(int id) {
		//TODO: Implement
		return null;
	}
	
	public Theater getTheaterById(int id) {
		//TODO: Implement
		return null;
	}
	
	public MovieShowing getMovieShowingById(int id) {
		//TODO: Implement
		return null;
	}
	
	public CreditCard getCreditCardById(int id) {
		//TODO: Implement
		return null;
	}
	
	public void createConnection() {
		try {
			//Register the JDBC driver
			Class.forName(JDBC_DRIVER);			
		} catch(ClassNotFoundException e){
			System.err.println(e);
			System.exit (-1);
		} 
		
		try {
			 //Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private BufferedImage blobToBufferedImage(Blob b) {
  		
		BufferedImage image = null;
		try {
			InputStream in = b.getBinaryStream();
			image = ImageIO.read(in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		return image;
	}
	
	private String blobToString(Blob b) {
		
		String b64 = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(blobToBufferedImage(b), "jpg", baos );
	        baos.flush();
	        byte[] imageInByteArray = baos.toByteArray();
	        baos.close();
	        b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
		return b64;
	}
	
	private String formatSearchString(String s) {
		String newStr = "%" + s.trim() + "%";
		newStr = newStr.replace(' ', '%');
		return newStr;
	}
}
