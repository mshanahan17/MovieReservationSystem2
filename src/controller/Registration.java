package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.apache.commons.mail.EmailException;

import model.User;
import model.UserDB;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private long lastModified;
	
	@Override
	public void init() throws ServletException {
		lastModified = System.currentTimeMillis()/1000 * 1000;
		System.out.println("Registration Servlet Has Started");
	}
	
	@Override
	protected long getLastModified(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return this.lastModified;
	}


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	/* 
    	 * unused parameters at this time will use in futre stages possibly
    	 */
        String fName = ServletUtils.validateInput(request.getParameter("fName"), "");
        String lName = ServletUtils.validateInput(request.getParameter("lName"), "");
        
        /* email is used as login info so both email and password
         * are validated
         */
        String[] email = request.getParameterValues("email");
        String[] password = request.getParameterValues("password");
        ServletUtils.validateAllInput(email);
        ServletUtils.validateAllInput(password);
        
        /*
         * Checks to see the repeat entries of the form match.
         */
        UserDB userDB = new UserDB();
        Boolean emailMatch = ServletUtils.validMatch(email[0], email[1]);
        Boolean passwordMatch = ServletUtils.validMatch(password[0], password[1]);
		Boolean userAlreadyExists = userDB.userExistsByEmailAddress(email[0]);

		/*
		 * If email and password entries match user is redirected to login
		 * page. Otherwise an error message is displayed depending on which
		 * entries did not match.
		 */
        if(emailMatch && passwordMatch && !userAlreadyExists){
            User user = new User();
            user.setFirstName(fName);
            user.setLastName(lName);
            user.setEmailAddress(email[0]);
            user.setPassword(password[0]);

            UserDB userDb = new UserDB();
            userDb.registerUser(user);
            
            //Send out confirmation e-mail
            try {
				JavaMailer.sendConfirmationEmail(email[0]);
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            response.sendRedirect("Login.jsp");
        }
        else if(userAlreadyExists) {
        	String emailError = "E-mail Already Exists!";
            request.setAttribute("emailError", emailError);
            request.getRequestDispatcher("Registration.jsp").forward(request,response);
        }
        else if(passwordMatch){
            String emailError = "E-mails do not match!";
            request.setAttribute("emailError", emailError);
            request.getRequestDispatcher("Registration.jsp").forward(request,response);
        }
        else {
            String pwError = "Passwords do not match!";
            request.setAttribute("pwError", pwError);
            request.getRequestDispatcher("Registration.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    
	@Override
	public void destroy() {
		connectionClosed();
	}
	
	/*
	 * when servlet connection is terminated this method will 
	 * write the time stamp and messaage then close the filewriter
	 * resources
	 */
	private void connectionClosed() {
		System.out.println("Registration Server Has Closed");
	}
	

}
