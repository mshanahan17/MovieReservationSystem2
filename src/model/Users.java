package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Users {
	private String userName;
	private String passWord;

	public Users(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}

	/*
	 * Adds new user to properties file
	 */
	public void newUser(Users user, String filePath) {
		Properties prop = new Properties();
		prop.setProperty(user.getUserName(), user.getPassWord());

		try {
			prop.store(new FileOutputStream(filePath), null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean userExists(Users user, String filePath) 
			throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = null;

		fis = new FileInputStream(filePath);
		prop.load(fis);

		return prop.containsKey(user.getUserName());
	}

	public boolean pwMatch(Users user, String filePath) 
			throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = null;

		fis = new FileInputStream(filePath);
		prop.load(fis);
		
		return prop.getProperty(user.getUserName()).equals(user.getPassWord()) 
					? true : false;

	}

	public String getUserName() {
		return this.userName;
	}

	public String getPassWord() {
		return this.passWord;
	}

}
