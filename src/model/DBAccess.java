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
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.log4j.Logger;

import model.PasswordUtilities;


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

	private static final double INVALID_DOUBLE_VALUE = -777.7777;
	private static final int INVALID_INT_VALUE = -777;
	
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

	//log4j logger
	static Logger log = 
    		Logger.getLogger(DBAccess.class.getName());
	
	public void changePassword(User user, String newPassword) {

		try {
			  
//			  String firstName = user.getFirstName();
//			  String lastName = user.getLastName();
//			  String emailAddress = user.getEmailAddress();
//			  String password = user.getPassword();
//			  int numOfVisits = user.getNumOfVisits();			  			  
			  
			  String sql = "UPDATE User SET Salt = ?, SaltyHash = ? WHERE EmailAddress = ?";
			  
			  String salt = PasswordUtilities.getSalt();
			  
			  PreparedStatement ps = conn.prepareStatement(sql);
			  ps.setString(1, salt);
			  ps.setString(2, PasswordUtilities.saltAndHashPassword(newPassword, salt));
			  ps.setString(3, user.getEmailAddress());
			  
			  ps.executeUpdate();
			  
			  ps.close();
			  
			  } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			  }
		return;
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
				log.error(e);
		}
		
	}
	
	public void addHashedSingleUser(User user) {
		  
		try {
		  
		  String firstName = user.getFirstName();
		  String lastName = user.getLastName();
		  String emailAddress = user.getEmailAddress();
		  String password = user.getPassword();
		  int numOfVisits = user.getNumOfVisits();
		  
		  String salt = PasswordUtilities.getSalt();
		  
		  String sql = "INSERT INTO User (FirstName, LastName, EmailAddress, Salt, SaltyHash, NumOfVisits) VALUES (?, ?, ?, ?, ?, ?)";
		  
		  PreparedStatement ps = conn.prepareStatement(sql);
		  ps.setString(1, firstName);
		  ps.setString(2, lastName);
		  ps.setString(3, emailAddress);
		  ps.setString(4, salt);
		  ps.setString(5, PasswordUtilities.saltAndHashPassword(password, salt));
		  ps.setInt(6, numOfVisits);
		  
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
			log.error(e);
		}
		
		return userExists;
	}
	
//	public boolean userExistsByPassword(String password) {
//		boolean userExists = false;
//		String sql = "SELECT COUNT(EmailAddress) FROM User WHERE EmailAddress = ?";
//	    PreparedStatement ps;
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, password);
//			
//			ResultSet rs = ps.executeQuery();
//			
//			while (rs.next()){	
//				if(Integer.parseInt(rs.getString(1)) > 0) {
//					userExists = true;
//				}    
//		    }
//			
//			rs.close();
//		    ps.close();
//		        
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return userExists;
//	}
	
	public User getUserByEmailAddress(String emailAddress) {
		String sql = "SELECT * from User WHERE EmailAddress = ?";
	    PreparedStatement ps;
	   
	    User user = new User();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, emailAddress);
			
			ResultSet rs = ps.executeQuery();
			Address billing = new Address();
			while(rs.next()) {
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setEmailAddress(rs.getString("EmailAddress"));
				user.setPassword(rs.getString("Password"));
				user.setSalt(rs.getString("Salt"));
				user.setSaltyHash(rs.getString("SaltyHash"));
				//TODO: Get the rest of the user data loaded into the object
				billing.setStreetAddress(rs.getString("Address"));
				billing.setCity(rs.getString("City"));
				billing.setState(rs.getString("State"));
				billing.setZip(rs.getString("PostalCode"));
				user.setBillingAddress(billing);
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
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
				user.setSalt(rs.getString("Salt"));
				user.setSaltyHash(rs.getString("SaltyHash"));
				user.setPhoneNumber(rs.getString("PhoneNumber"));				
				
				Address billingAddress = new Address();				
				billingAddress.setStreetAddress(rs.getString("Address"));
				billingAddress.setCity(rs.getString("City"));
				billingAddress.setState(rs.getString("State"));
				billingAddress.setZip(rs.getString("PostalCode"));
				
				user.setBillingAddress(billingAddress);
								
				user.setCreditCard(getCreditCardByUserId(Id));

		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}
		
		return user;
	}
	
	public CreditCard getCreditCardByUserId(int userId) {		

		
		String sql = "SELECT * FROM CreditCard AS CC "
				+ "JOIN BankAccount AS BA ON CC.BankAccountId = BA.BankAccountId "
				+ "JOIN User AS U ON BA.UserId = U.Id "
				+ "WHERE U.Id = ?";
	    PreparedStatement ps;
	   
	    CreditCard cc = new CreditCard();
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				cc.setCardNumber(rs.getString("CreditCardNumber"));
				cc.setBalance(rs.getDouble("Balance"));
				cc.setCardHolderName(rs.getString("CardHolderName"));
				cc.setCardType(rs.getString("CardType"));
				cc.setCvv(rs.getString("CVV"));
				cc.setExpirationDate(rs.getString("ExpirationDate"));
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}

		return cc;
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
				a.setZip(rs.getString("PostalCode"));
				
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
			log.error(e);
		}

		return theaters;
	}

	public List<MovieShowing> getMovieShowingSearchResults(String theaterName, String movieName, String date) {

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
			log.error(e);
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
			log.error(e);
		}
		
		return m;
	}
	
	public Showroom getShowroomById(int id) {

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
			log.error(e);
		}
	
		return sr;
	}
	
	public List<Review> getReviewsByMovieTitle(String movieTitle) {
		
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
			log.error(e);
		}
		
		return reviewSearchResults;
	}
	
	public Review getReviewById(int id) {
		
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
			log.error(e);
		}
	
		return r;
	}
	
	public List<Order> getOrdersByOrderId(int orderId) {
		//TODO: Implement
		String sql = "select * from OrderItem\n" + 
				"where OrderId = ?";
		
	    PreparedStatement ps;	   	    
	    
	    List<Order> orders = new ArrayList<Order>();
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, orderId);
			
			ResultSet rs = ps.executeQuery();
			
			Order refOrder = getOrderById(orderId);
			
			while(rs.next()) {				
				Order o = new Order();				
				
				o.setTicketQuantity(rs.getInt("Quantity"));
				
				o.setBillingAddress(refOrder.getBillingAddress());
				o.setCost(refOrder.getCost());
				o.setCreditCardNumber(refOrder.getCreditCardNumber());
				o.setCustomer(refOrder.getCustomer());
				o.setDate(refOrder.getDate());
				o.setId(refOrder.getId());
				o.setMovieShowing(getMovieShowingById(rs.getInt("ShowingID")));				
								
				orders.add(o);
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}
	
		return orders;
	}
	
	public List<Order> getOrdersByUser(User u) {

		
		String sql = "    select * from `Order` o\n" + 
				"    join User u on o.CustomerId = u.Id\n" + 
				"    where u.EmailAddress = ?\n" + 
				"    and u.`SaltyHash` = ?;";
		
	    PreparedStatement ps;	   	    
	    
	    List<Order> orders = new ArrayList<Order>();
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, u.getEmailAddress());
			ps.setString(2, u.getSaltyHash());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {	
				Order o = new Order();
				
				// Order attributes
				o.setCost(rs.getDouble("TotalCost"));
				o.setDate(rs.getString("OrderDate"));
				o.setBillingAddress(rs.getString("BillingAddress"));
				o.setCreditCardNumber(rs.getString("CreditCardNumber"));
				o.setId(rs.getInt("Id"));
				
				orders.add(o);
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}
	
		return orders;
	}
	
	public Order getOrderById(int id) {
		
		String sql = "select * from `Order` where Id = ?";
		
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
				o.setId(rs.getInt("Id"));
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}
	
		return o;
	}
	
	public void removeOrderItem(Order o) {
		
		String sql = "DELETE FROM `OrderItem`\n" + 
				"WHERE OrderId = ?\n" +
				"AND ShowingID = \n" + 
				"(select Id from MovieShowing\n" + 
						"    where movieID = \n" + 
						"		(select Id from Movie where `Movie name` = ?)\n" + 
						"    and showroomID = \n" + 
						"		(select Id from Showroom \n" + 
						"        where availableSeats = ? \n" + 
						"        and theaterBuilding = \n" + 
						"			(select Id from TheaterBuilding\n" + 
						"            where `Name` = ?))\n" + 
						"    and StartTime = ?\n" + 
						"    and Price = ?)\n" +  
				"AND Quantity = ?";

		PreparedStatement ps;	   	    	    	    
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, o.getId()); 
			ps.setString(2, o.getMovieShowing().getMovie().getTitle());
			ps.setInt(3, o.getMovieShowing().getShowroom().getCapacity());
			ps.setString(4, o.getMovieShowing().getShowroom().getTheater().getName());
			ps.setString(5, o.getMovieShowing().getStartTime());
			ps.setDouble(6, o.getMovieShowing().getCost()); 
			ps.setInt(7, o.getTicketQuantity());
						
			ps.executeUpdate();
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}

		return;
	}
	
	public void attemptRemoveOrder(Order o) {
		
		String sql = "delete from `Order` where Id = ?";
	    
		PreparedStatement ps;	   	    	    	    
	    
		try {			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, o.getId());			
			
			ps.executeUpdate();
		    ps.close();
		        
		} catch (SQLException e) {
			
		}
		

	}
	
	public void refundCreditCard(Order o, double changeInCost) {
		//TODO: Implement
		
		String sql = "update CreditCard \n" + 
				"set Balance = ?\n" + 
				"where CreditCardNumber = ?";

	    PreparedStatement ps;	   	    	    	    
	    
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, o.getCustomer().getCreditCard().getBalance() + changeInCost);
			ps.setString(2, o.getCreditCardNumber());			
			
			ps.executeUpdate();
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}
		
		return;
	}
	
	public void updateTotalCostOfOrder(Order o, double changeInCost) {
		String sql = "UPDATE `Order`\n" + 
				"SET TotalCost = ?\n" + 
				"WHERE CustomerId = (select Id from User where EmailAddress = ? and `SaltyHash` = ?)\n" + 
				"    and TotalCost = ?\n" + 
				"    and OrderDate = ?\n" + 
				"    and BillingAddress = ?\n" + 
				"    and CreditCardNumber = ?";
		
	    PreparedStatement ps;	   	    	    	    
	    
	    
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, o.getCost() + changeInCost); // TODO: Is this okay?
			ps.setString(2, o.getCustomer().getEmailAddress());
			ps.setString(3, o.getCustomer().getSaltyHash());
			ps.setDouble(4, o.getCost()); //TODO: Is this okay?
			ps.setString(5, o.getDate());
			ps.setString(6, o.getCustomer().getBillingAddress().getStreetAddress());
			ps.setString(7, o.getCustomer().getCreditCard().getCardNumber());
			
			
			ps.executeUpdate();
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}

		return;
	}
	
	public Theater getTheaterById(int id) {
		
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
				a.setZip(rs.getString("PostalCode"));
				
				t.setAddress(a);
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}
	
		return t;
	}
	
	public MovieShowing getMovieShowingById(int id) {
		
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
			log.error(e);
		}
		
		return ms;
	}
	
	public void addCreditCardToUser(User u, CreditCard cc) {
		
		//String sql = "insert into CreditCard (CardHolderName, CreditCardNumber, Balance, CardType, UserId, CVV, ExpirationDate) values (?, ?, ?, ?, ?, ?, ?)";
		String sql = "insert into CreditCard (CardHolderName, CreditCardNumber, Balance, CardType, UserId, CVV, ExpirationDate) \n" + 
				"values (?, ?, ?, ?,\n" + 
				"	(select Id from User where EmailAddress = ? and `SaltyHash` = ?), \n" + 
				"    ?, ?)";
		
		
	    PreparedStatement ps;
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cc.getCardHolderName());
			ps.setString(2, cc.getCardNumber());
			ps.setDouble(3, cc.getBalance());
			ps.setString(4, cc.getCardType());
			
			ps.setString(5, u.getEmailAddress());
			ps.setString(6, u.getSaltyHash());
			
			ps.setString(7, cc.getCvv());
			ps.setString(8, cc.getExpirationDate());			
			
			ps.executeUpdate();						
			
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}
		
		return;
	}
	
	public void addAddressToUser(User u, Address a) {

		String sql = "update User \n" + 
				"set Address = ?, City = ?, State = ?, PostalCode = ?\n" + 
				"where EmailAddress = ? and Password = ?"; 
		
	    PreparedStatement ps;	    
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, a.getStreetAddress());
			ps.setString(2, a.getCity());
			ps.setString(3, a.getState());
			ps.setString(4, a.getZip());
			ps.setString(5, u.getEmailAddress());
			ps.setString(6, u.getSaltyHash());
			
			ps.executeUpdate();
									
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}
		
		return;
	}
	
	public String getCreditCardNumber(User u) {
		String sql = "select CreditCardNumber from CreditCard " + 
				"where CardHolderName = ?";
		
		PreparedStatement ps;
		String ccNum = null;
		String name = u.getFirstName() + " " + u.getLastName();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				ccNum = rs.getString("CreditCardNumber");
			}
		} catch (SQLException e) {
			log.error(e);
		}
		
		return ccNum;
	}
	public boolean validateCreditCard(User u, CreditCard cc) {

		String sql = "select * from CreditCard \n" + 
				"where UserId = \n" + 
				"	(select Id from User where EmailAddress = ? and `SaltyHash` = ?)\n" + 
				"and CreditCardNumber = ?";
		
	    PreparedStatement ps;
	   
	    boolean foundCard = false;
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, u.getEmailAddress());
			ps.setString(2, u.getSaltyHash());
			ps.setString(3, cc.getCardNumber());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("CreditCardNumber") == null) {
					foundCard = false;
				} else {
					foundCard = true;
				}					
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}
		
		return foundCard;
	}
	
	public boolean attemptTransaction(CreditCard cc, double transactionAmount) {
		
		double balance = getCreditCardBalanceByCreditCard(cc);
		double result = balance - transactionAmount;
		
		if(result > 0) {
			updateCreditCardBalanceByCreditCard(cc, result);
			return true;
		} else {
			return false;
		}

	}
	
	private double getCreditCardBalanceByCreditCard(CreditCard cc) {
		
		String sql = "select Balance from CreditCard where CreditCardNumber = ?";
		
	    PreparedStatement ps;
	    
	    double balance = INVALID_DOUBLE_VALUE;
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cc.getCardNumber());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {				
				balance = rs.getDouble("Balance");			
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}
		
		return balance;
	}
	
	private void updateCreditCardBalanceByCreditCard(CreditCard cc, double newBalance) {
		
		String sql = "update CreditCard set Balance = ? where CreditCardNumber = ?";
		
	    PreparedStatement ps;
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, newBalance);
			ps.setString(2, cc.getCardNumber());
			
			ps.executeUpdate();

		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}
		
		return;
	}
	
	public int addOrderToUser(Order firstOrder, double totalCost, String date) {
		
		
		// ======================================================
		// Keep this stuff, yo
		// ======================================================
		// QUERY #1
		String sql = "insert into `Order` (CustomerId, TotalCost, OrderDate, BillingAddress, CreditCardNumber) \n" + 
				"values (\n" + 
				"	(select Id from User where EmailAddress = ? and SaltyHash = ?),\n" + 
				"    ?, ?, ?, ?)"; 

	    User purchaser = firstOrder.getCustomer();
	    User customer = this.getUserByEmailAddress(purchaser.getEmailAddress());
	    String ccNum = this.getCreditCardNumber(purchaser);
	    int id = INVALID_INT_VALUE;
	    
	    PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, purchaser.getEmailAddress());
			ps.setString(2, purchaser.getSaltyHash());
			ps.setDouble(3, totalCost); //TODO: This may need to be an int?
			ps.setString(4, date);
			ps.setString(5, customer.getBillingAddress().getStreetAddress());
			ps.setString(6, ccNum);
								
			ps.executeUpdate();
						
			
			ResultSet rs = ps.getGeneratedKeys();
			
			while(rs.next()) {
				id = rs.getInt(1);
			}
			
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}
				
		return id;
	}
	
	public void addQuantityToOrderItemTable(Order o, double totalCost, String dateTime, int orderId) {
		
		
		String sql = "insert into OrderItem (OrderId, ShowingID, Quantity) \n" + 
				"values( ?,\n" + 
				"    (select Id from MovieShowing\n" + 
				"    where movieID = \n" + 
				"		(select Id from Movie where `Movie name` = ?)\n" + 
				"    and showroomID = \n" + 
				"		(select Id from Showroom \n" + 
				"        where availableSeats = ? \n" + 
				"        and theaterBuilding = \n" + 
				"			(select Id from TheaterBuilding\n" + 
				"            where `Name` = ?))\n" + 
				"    and StartTime = ?\n" + 
				"    and Price = ?),\n" + 
				"    ?);";
	    
	    PreparedStatement ps;
	   
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, orderId);
			ps.setString(2, o.getMovieShowing().getMovie().getTitle());
			ps.setInt(3, o.getMovieShowing().getShowroom().getCapacity());
			ps.setString(4, o.getMovieShowing().getShowroom().getTheater().getName());
			ps.setString(5, o.getMovieShowing().getStartTime());
			ps.setDouble(6, o.getMovieShowing().getCost()); //Double check this cost value is correct
			ps.setInt(7, o.getTicketQuantity());
					
			
			ps.executeUpdate();
									
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}
		
		return;
	}
	
	public void updateMovieShowingSeatsPurchased(MovieShowing ms, int updateValue) {
		
		String sql = "UPDATE `MovieShowing`\n" + 
				"SET `NumberPurchased` = ?\n" + 
				"WHERE Price = ?\n" + 
				"    and NumberPurchased = ?\n" + 
				"    and StartTime = ?\n" + 
				"    and movieID = (select Id from Movie where `Movie name` = ?)\n" + 
				"    and showroomID = \n" + 
				"		(select Id from Showroom \n" + 
				"        where availableSeats = ? \n" + 
				"        and theaterBuilding = \n" + 
				"			(select Id from TheaterBuilding\n" + 
				"            where `Name` = ?))";
		
		PreparedStatement ps;
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ms.getNumOfPurchasedSeats() + updateValue);
			ps.setDouble(2, ms.getCost());
			ps.setInt(3, ms.getNumOfPurchasedSeats());
			ps.setString(4, ms.getStartTime());
			ps.setString(5, ms.getMovie().getTitle());
			ps.setInt(6, ms.getShowroom().getCapacity());
			ps.setString(7, ms.getShowroom().getTheater().getName());
			
			ps.executeUpdate();
						
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}
		
		return;
	}
	
	public CreditCard getCreditCardById(int id) {

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
				cc.setCvv(rs.getString("CVV"));
				cc.setExpirationDate(rs.getString("ExpirationDate"));
				
				int ownerId = rs.getInt("userID");				
				User owner = getUserById(ownerId);
				cc.setOwner(owner);
		    }
			
			rs.close();
		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
		}
		
		return cc;
	}
	
	public boolean addReview(Review r, String rating) {
		String sql = "insert into CustomerReview (movieID, userID, ReviewDate, Rating, Review) \n" + 
				"values (\n" + 
				"	(select Id from Movie where `Movie name` = ?),\n" + 
				"    (select Id from User where EmailAddress = ? and `SaltyHash`= ?),\n" + 
				"    ?, ?, ?)";
		
	    PreparedStatement ps;	   	    
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, r.getMovie().getTitle());
			ps.setString(2, r.getUser().getEmailAddress());
			ps.setString(3, r.getUser().getSaltyHash());
			ps.setString(4, r.getDate());
			ps.setInt(5, Integer.parseInt(rating));
			ps.setString(6, r.getContent());			
			
			ps.executeUpdate();

		    ps.close();
		        
		} catch (SQLException e) {
			log.error(e);
			return false;
		}
		
		return true;
	}
	
	public void createConnection() {
		try {
			//Register the JDBC driver
			Class.forName(JDBC_DRIVER);			
		} catch(ClassNotFoundException e){
			log.error(e);
			System.exit (-1);
		} 
		
		try {
			 //Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			log.error(e);
		}
	}	
	
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			log.error(e);
		}
	}

	private BufferedImage blobToBufferedImage(Blob b) {
  		
		BufferedImage image = null;
		try {
			InputStream in = b.getBinaryStream();
			image = ImageIO.read(in);
		} catch (Exception e) {
			log.error(e);
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
			log.error(e);
		}  		
		
		return b64;
	}
	
	private String formatSearchString(String s) {
		String newStr = "%" + s.trim() + "%";
		newStr = newStr.replace(' ', '%');
		return newStr;
	}
}
