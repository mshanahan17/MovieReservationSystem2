package accounthandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Users {
	private Properties prop = new Properties();
	private String userName;
	private String passWord;
	
	
	public Users(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}

	public void newUser(Users user, Properties prop, String filePath) {

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

	public boolean userExists(Users user, Properties prop){
		
		return prop.containsKey(user.getUserName()) ? true : false;
	}
	
	public boolean pwMatch(Users user, Properties prop){
		
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
