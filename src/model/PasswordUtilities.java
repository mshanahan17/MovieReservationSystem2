package model;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public final class PasswordUtilities {
	
	public static void main(String[] args) {
		genTestHash("I'macat77");
	}
	
	private PasswordUtilities() {
		// No instantiation for you
	}
	
	private static void genTestHash(String password) {
		System.out.println("Pass:");
		printReport(password);
		
		String s = PasswordUtilities.getSalt();
		System.out.println("Salt:");
		printReport(s);
		
		String saltedPass = password + s;
		System.out.println("SaltedPass:");
		printReport(saltedPass);
		
		String hsPass = PasswordUtilities.saltAndHashPassword(password, s);
		System.out.println("HashedSaltedPass:");
		printReport(hsPass);	
	}
	
	private static void printReport(String s) {
		System.out.println(s.length() + " : " + s + "\n");
		return;
	}
	
	public static String saltAndHashPassword(String password, String salt) {
		return hashify(password + salt);
	}
	
	// Uses Base64 class to convert bytes into String
	private static String hashify(String s) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256"); 
			md.update(s.getBytes());
			byte[] mdArray = md.digest();
			String st = Base64.encode(mdArray);
			return st;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String getSalt() {		 
		Random r = new SecureRandom();
		byte[] saltBytes = new byte[32];
		r.nextBytes(saltBytes);
		return Base64.encode(saltBytes);
	}
	
}
