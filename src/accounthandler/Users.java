package accounthandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Users {

	private static Properties prop = new Properties();
	private static String filePath = System.getProperty("user.home") + "/Desktop/Properties/account.properties";

	public static void newUser(String userName, String password) {

		try {
			prop.setProperty(userName, password);
			prop.store(new FileOutputStream(filePath), null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean userExists(String user) 
			throws FileNotFoundException, IOException {

		prop.load(new FileInputStream(filePath));
		return prop.getProperty(user) == null ? false : true;

	}
	
	public static boolean pwMatch(String user, String pw) 
			throws FileNotFoundException, IOException {

		prop.load(new FileInputStream(filePath));
		return prop.getProperty(user).equals(pw) ? true : false;

	}
	
}
