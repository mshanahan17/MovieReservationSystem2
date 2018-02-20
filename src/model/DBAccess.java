package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement ps = null;
	
	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	// static final String DB_URL = "jdbc:mysql://localhost/MVCApp"; 
	private static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/mshanahan";

	// Database credentials
	private static final String USER = "mshanahan"; // Replace with your CSE_LOGIN
	private static final String PASS = "k8ErVH";   // Replace with your CSE MySQL_PASSWORD
	
	public void addSingleUser(User user) {
		  
		try {
		  stmt = conn.createStatement();
		  String sql;
		  
		  String firstName = user.getFirstName();
		  String lastName = user.getLastName();
		  String emailAddress = user.getEmailAddress();
		  String password = user.getPassword();
		  

		  sql = "INSERT INTO User (firstName, lastName, emailAddress, password)" +
		          "VALUES ('" + firstName +
				  "', '" + lastName + 
				  "', '" + emailAddress + 
				  "', '" + password + "')";
		  stmt.executeUpdate(sql);
		  
		  
		  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	}
	
	
	public boolean userExistsByEmailAddress(String emailAddress) {
		boolean userExists = false;
		String sql = "SELECT * from User";
	    Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			
			while (rs.next()){	
				if(emailAddress.equals( rs.getString(4) )) {
					userExists = true;
				}    
		    }
			
		    stat.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userExists;
	}
	
	public boolean userExistsByPassword(String password) {
		boolean passwordMatches = false;
		String sql = "SELECT * from User";
	    Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			
			while (rs.next()){	
				if(password.equals( rs.getString(5) )) {
					passwordMatches = true;
				}    
		    }
			
		    stat.close();
		        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return passwordMatches;
	}
	
	
	public User getUserByEmailAddress(String emailAddress) {
		String sql = "SELECT * from User";
	    Statement stat;
	   
	    User user = new User();
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			
			while (rs.next()){
				if(emailAddress.equals( rs.getString(4) )) {
					user.setFirstName(rs.getString(2));
					user.setLastName(rs.getString(3));
					user.setEmailAddress(rs.getString(4));
					user.setPassword(rs.getString(5));
				} 
		    }
			
		    stat.close();
		        
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


	public void createConnection() {
		try{
			//Register the JDBC driver
			Class.forName(JDBC_DRIVER);			
		}catch(ClassNotFoundException e){
			System.err.println(e);
			System.exit (-1);
		}
		try {
			 //Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
