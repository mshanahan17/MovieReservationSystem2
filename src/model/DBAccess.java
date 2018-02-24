package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	    
	    Theater t = new Theater();
	    Address a = new Address();
	    User owner = new User();
	    
		try {
			ps = conn.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
								
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


}
