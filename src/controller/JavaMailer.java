package controller;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class JavaMailer {
	
	public static void sendConfirmationEmail(String to) throws EmailException {
		
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthentication("csce464practice@gmail.com", "CSCE464PASSWORD");
		email.setSSLOnConnect(true);
		email.setFrom("csce464practice@gmail.com");
		email.setSubject("Confirmation E-mail");
		email.setMsg("Your registraion was succesful!");
		email.addTo(to);
		email.send();
	}

}
