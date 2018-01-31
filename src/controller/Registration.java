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

import model.Users;

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
        String fName = ServletUtils.validateInput(request.getParameter("fname"), "");
        String lName = ServletUtils.validateInput(request.getParameter("lname"), "");
        
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
        Boolean emailMatch = ServletUtils.validMatch(email[0], email[1]);
        Boolean passwordMatch = ServletUtils.validMatch(password[0], password[1]);
		
        /*
         * gets filepath for properties file to be sent to Users class
         */
		ServletContext sc = this.getServletContext();
		String filePath = sc.getRealPath("/WEB-INF/users.properties");
		
		/*
		 * If email and password entries match user is redirected to login
		 * page. Otherwise an error message is displayed depending on which
		 * entries did not match.
		 */
        if(emailMatch && passwordMatch){
            Users user = new Users(email[0], password[0]);
            user.newUser(user, filePath);
            response.sendRedirect("Login.jsp");
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
