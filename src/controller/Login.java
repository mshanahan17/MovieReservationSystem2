package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Theater;
import model.TheaterDB;
import model.User;
import model.UserDB;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private long lastModified;

	@Override
	public void init() throws ServletException {
		lastModified = System.currentTimeMillis() / 1000 * 1000;
		System.out.println("Login Servlet Has Started");
	}

	@Override
	protected long getLastModified(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return this.lastModified;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("pwError");
		String success = "WEB-INF/Customer/CustomerHomePage.jsp";
		String failure = "Registraion.jsp";
		String button = request.getParameter("login");
		if(button != null && button != "") {
			session.removeAttribute("total");
		}
		/*
		 * recieves and validates login information
		 */
		String email = ServletUtils.
				validateInput(request.getParameter("email"), "");
		String password = ServletUtils.
				validateInput(request.getParameter("password"), "");
		
		boolean rememberMe = request.getParameter("rememberMe") != null;
		boolean invalidEmail = email == null || email == "";
		boolean invalidPassword = password == null || password == "";
		
		//Sets email into cookie to be remembered for next login attempt
		if(rememberMe)
		{
		    Cookie c = new Cookie("userId", email);
		    c.setMaxAge(24*60*60);
		    response.addCookie(c); 
		}


		User user = (User) session.getAttribute("user");
		List<Theater> theaters = (List<Theater>) session.getAttribute("theaters");
		
		
		if(theaters == null) {
			theaters = new TheaterDB().getTheaters();
			session.setAttribute("theaters", theaters);
		}
		
		if(user == null && invalidEmail && invalidPassword) {
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			return;
		}
		//Validates login directing to registration if user is not found
		//or displays a password error on ligin if user is found but wrong password
		if(user == null) {
			UserDB userDb = new UserDB();

			user = userDb.getUserByEmailAddress(email);

			if(user == null) {
				request.getRequestDispatcher(failure).forward(request, response);
				return;
			}
			else if(!userDb.passwordIsValid(user, password)) {
				session.setAttribute("pwError", "Incorrect Password");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
				return;
			}
			// THIS IS HOW IT WAS BEFORE
//			else if(!user.getPassword().equals(password)) {
//				session.setAttribute("pwError", "Incorrect Password");
//				request.getRequestDispatcher("Login.jsp").forward(request, response);
//				return;
//			}
			
			session.setAttribute("user", user);
			
		}
		
		request.getRequestDispatcher(success).forward(request, response);


		/*
		 * redirects to correct webpage depending on if login info was correct or not.
		 */


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void destroy() {
		connectionClosed();
	}

	/*
	 * when servlet connection is terminated this method will write the time stamp
	 * and messaage then close the filewriter resources
	 */
	private void connectionClosed() {
		System.out.println("Login Servlet Has Closed");
	}

}
