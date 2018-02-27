package model;

import model.DBAccess;
import model.User;
	//TODO: LOAD CREDITCARD INFO FROM DB UPON CREATION
public class UserDB {
	
    public void registerUser(User user) {
       	DBAccess db = new DBAccess();
       	db.createConnection();
       	db.addSingleUser(user);
       	db.closeConnection();
    }
    
    public boolean userExistsByEmailAddress(String userName) {
    	    boolean userExists = false;
       	DBAccess db = new DBAccess();
       	db.createConnection();
       	userExists = db.userExistsByEmailAddress(userName);
       	db.closeConnection();
       	
       	return userExists;
    }
    
    public boolean userExistsByPassword(String password) {
	    boolean passwordMatches = false;
   	    DBAccess db = new DBAccess();
   	    db.createConnection();
   	    passwordMatches = db.userExistsByPassword(password);
   	    db.closeConnection();
   	
   	    return passwordMatches;
    }
    
    public User getUserByEmailAddress(String emailAddress) {   
	   	DBAccess db = new DBAccess();
	   	db.createConnection();
	   	User user = db.getUserByEmailAddress(emailAddress);
	   	db.closeConnection();
	   	
	   	return user;
    }
    
    public User getUserById(int id) {
		DBAccess db = new DBAccess();
       	db.createConnection();
       	User u = db.getUserById(id);
       	db.closeConnection();
		return u;
    }

    public void addCreditCardToUser(User u, CreditCard cc) {
    		DBAccess db = new DBAccess();
       	db.createConnection();
       	db.addCreditCardToUser(u, cc);
       	db.closeConnection();
		return;
    }
    
    public void addAddressToUser(User u, Address a) {
    		//TODO: Implement
    		return;
    }
    
    public void attemptTransaction(User u, CreditCard cc, double transactionAmount) {
    		//TODO: Implement
    		return;
    }
}
