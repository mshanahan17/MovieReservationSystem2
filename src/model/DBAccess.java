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
		//TODO: Test this
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
		//TODO: Test this
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
				ms.setStartTime(rs.getString("StartTime").substring(0, 16));
				ms.setEndTime(rs.getString("EndTime").substring(0, 16));
				
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
		//TODO: Test this
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
		//TODO: Test this
		String sql = "select * from Showroom where Id = ?";
		
	    PreparedStatement ps;	   	    
	    
	    Showroom sr = null;
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {	
				sr = new Showroom();
				sr.setCapacity(rs.getInt("availableSeats"));
				
				int theaterId = rs.getInt("theaterBuilding");
				Theater t = getTheaterById(theaterId);
				sr.setTheater(t);
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return sr;
	}
	
	public List<Review> getReviewsByMovieTitle(String movieTitle) {
		//TODO: Test this
		String sql = "select cr.Id from CustomerReview cr\n" + 
				"join Movie m on cr.movieID = m.Id\n" + 
				"where m.`Movie name` like ?"; 				
		
	    PreparedStatement ps;
	   
	    List<Review> reviewSearchResults = new ArrayList<Review>();
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, formatSearchString(movieTitle));
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {				
				int reviewId = rs.getInt("Id");				
				reviewSearchResults.add(getReviewById(reviewId));
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reviewSearchResults;
	}
	
	public Review getReviewById(int id) {
		//TODO: test this
		String sql = "select * from CustomerReview where Id = ?";
		
	    PreparedStatement ps;	   	    
	    
	    Review r = null;
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {	
				r = new Review();
				
				int movieId = rs.getInt("movieID");
				Movie m = getMovieById(movieId);
				r.setMovie(m);
				
				int userId = rs.getInt("userID");
				User u = getUserById(userId);
				r.setUser(u);
				
				r.setDate(rs.getString("ReviewDate"));				
				r.setRating(rs.getString("Rating"));
				r.setContent(rs.getString("Review"));
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return r;
	}
	
	public List<Order> getOrdersByUser(User u) {
		//TODO: test this
		String sql = "select * from `Order` o\n" + 
				"join User u on o.CustomerId = u.Id\n" + 
				"join OrderItem oi on o.Id = oi.OrderId\n" + 
				"where u.EmailAddress = ?\n" + 
				"and u.`Password` = ?;";
		
	    PreparedStatement ps;	   	    
	    
	    List<Order> orders = new ArrayList<Order>();
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, u.getEmailAddress());
			ps.setString(1, u.getPassword());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {	
				Order o = new Order();
				
				// Order attributes
				o.setCost(rs.getDouble("TotalCost"));
				o.setDate(rs.getString("OrderDate"));
				o.setBillingAddress(rs.getString("BillingAddress"));
				o.setCreditCardNumber(rs.getString("CreditCardNumber"));
				
				// TODO: HERE
				int showingId = rs.getInt("ShowingId"); // get MovieShowing with this
				MovieShowing ms = getMovieShowingById(showingId);
				o.setMovieShowing(ms);
				
				o.setTicketQuantity(rs.getInt("Quantity")); // This is how many tickets they bought of a particular showing
				
				orders.add(o);
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return orders;
	}
	
	public Order getOrderById(int id) {
		//TODO: test this
		String sql = "select * from Order where Id = ?";
		
	    PreparedStatement ps;	   	    
	    
	    Order o = null;
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {	
				o = new Order();
				
				int customerId = rs.getInt("CustomerID");
				User customer = getUserById(customerId);
				o.setCustomer(customer);
				
				o.setCost(rs.getInt("TotalCost"));				
				o.setDate(rs.getString("OrderDate"));				
				o.setBillingAddress(rs.getString("BillingAddress"));
				o.setCreditCardNumber(rs.getString("CreditCardNumber"));
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return o;
	}
	
	public Theater getTheaterById(int id) {
		//TODO: Test this
		String sql = "select * from TheaterBuilding where Id = ?";
		
	    PreparedStatement ps;	   	    
	    
	    Theater t = null;
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {	
				t = new Theater();
				
				int ownerId = rs.getInt("ownerID");
				User owner = getUserById(ownerId);
				t.setOwner(owner);
				
				t.setName(rs.getString("Name"));	
				
				Address a = new Address();
				a.setStreetAddress(rs.getString("Address"));
				a.setCity(rs.getString("Address"));
				a.setState(rs.getString("State"));
				a.setZip(rs.getInt("PostalCode"));
				
				t.setAddress(a);
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return t;
	}
	
	public MovieShowing getMovieShowingById(int id) {
		//TODO: Test this
		String sql = "select * from MovieShowing where Id = ?";
		
	    PreparedStatement ps;
	   
	    MovieShowing ms = null;
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				ms = new MovieShowing();
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
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ms;
	}
	
	public CreditCard getCreditCardById(int id) {
		//TODO: Test this
		String sql = "select * from CreditCard where Id = ?";
		
	    PreparedStatement ps;
	   
	    CreditCard cc = null;
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				cc = new CreditCard();
				cc.setCardHolderName(rs.getString("CardHolderName"));
				cc.setCardNumber(rs.getString("CreditCardNumber"));				
				cc.setBalance(rs.getDouble("Balance"));
				cc.setCardType(rs.getString("CardType"));
				cc.setCvv(rs.getInt("CVV"));
				cc.setExpirationDate(rs.getString("ExpirationDate"));
				
				int ownerId = rs.getInt("userID");				
				User owner = getUserById(ownerId);
				cc.setOwner(owner);
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cc;
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
