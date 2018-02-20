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
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String success = "WEB-INF/Customer/CustomerHomePage.jsp";
		String failure = "Registraion.jsp";
		/*
		 * recieves and validates login information
		 */
		String email = ServletUtils.
				validateInput(request.getParameter("email"), "");
		String password = ServletUtils.
				validateInput(request.getParameter("password"), "");

		User user = (User) session.getAttribute("user");
		ArrayList<String> theaters = new ArrayList();
		theaters.add("Theater 1");
		theaters.add("Theater 2");
		theaters.add("Theater 3");
		theaters.add("Theater 4");
		theaters.add("Theater 5");
		session.setAttribute("theater", theaters);
		
		if(user == null) {
			UserDB userDb = new UserDB();

			user = userDb.getUserByEmailAddress(email);
			
			if(user == null || !user.getPassword().equals(password)) {
				request.getRequestDispatcher(failure).forward(request, response);
			}

			
			session.setAttribute("user", user);
			request.getRequestDispatcher(success).forward(request, response);
		}
		else {
			
			request.getRequestDispatcher(success).forward(request, response);
		}


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
