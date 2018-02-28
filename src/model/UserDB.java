package model;

import model.DBAccess;
import model.User;
	//TODO: LOAD CREDITCARD INFO FROM DB UPON CREATION
public class UserDB {
		
	public static void main(String[] args) {
		UserDB udb = new UserDB();
		
		// User u = udb.getUserById(4);
		
		User u = new User();
		u.setEmailAddress("mattshanahan@hotmail.com");
		u.setPassword("123");
		
		CreditCard cc = new CreditCard();
		cc.setCardNumber("23452");
		cc.setBalance(300.0);
		cc.setCardHolderName("Jeff Bezos");
		cc.setCardType("Visa");
		cc.setCvv("452");
		cc.setExpirationDate("2018-04-01");
		cc.setOwner(u);
		
		System.out.println(cc);
		
		udb.addCreditCardToUser(u, cc);
	}
	
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

    public boolean addCreditCardToUser(User u, CreditCard cc) {
    		DBAccess db = new DBAccess();
       	db.createConnection();
       	
       	if(isValidCreditCardFormatting(cc)) {
       		db.addCreditCardToUser(u, cc);	
       	} else {
       		return false;
       	}
       	       	
       	db.closeConnection();
		return true;
    }
    
    private boolean isValidCreditCardFormatting(CreditCard cc) {
    		//TODO: Do more crap in here
    		
    		String cardNumber = cc.getCardNumber().trim().replaceAll(" ", "");
    		if(cardNumber.length() == 16) {
    			return true;
    		} else {
    			return false;	
    		}
    		
    }
    
    public void addAddressToUser(User u, Address a) {
		DBAccess db = new DBAccess();
       	db.createConnection();
       	db.addAddressToUser(u, a);
       	db.closeConnection();
    		return;
    }
    
    public boolean validateCreditCard(User u, CreditCard cc) {		
    		DBAccess db = new DBAccess();
       	db.createConnection();
       	
       	boolean isValid = db.validateCreditCard(u, cc);
       	
       	db.closeConnection();
		return isValid;
    }
    
    public boolean attemptTransaction(User u, CreditCard cc, double transactionAmount) {
		DBAccess db = new DBAccess();
       	db.createConnection();
       	
       	boolean wasSuccessful = db.attemptTransaction(cc, transactionAmount);
       	
       	db.closeConnection();
		return wasSuccessful;
    }
}
