package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import model.PasswordUtilities;
import model.User;
import model.UserDB;

/**
 * Servlet implementation class PasswordAuthServlet
 */
@WebServlet("/PasswordAuthServlet")
public class PasswordAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//log4j logger
		private static Logger log = 
	    		Logger.getLogger(PasswordAuthServlet.class.getName());
		
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordAuthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init() throws ServletException {
		ServletContext sc = this.getServletContext();
		String path = sc.getRealPath("/WEB-INF/lib/log4j.properties");
		PropertyConfigurator.configure(path);
		log.info("Password Authentication Servlet Has Started");
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String passAuth = ServletUtils.validateInput(request.getParameter("passAuth"), "");
		
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
				
		
		UserDB udb = new UserDB();
		
		
		int status = 1;
		
		System.out.println("provided password: " + passAuth);
		System.out.println("pass in session object: " + u.getPassword());
		
		System.out.println("sessobj - userSalt: " + u.getSalt());
		
		String curSaltyHash = PasswordUtilities.saltAndHashPassword(passAuth, u.getSalt());
		
		System.out.println("provided end pass: " + curSaltyHash);
		
		String theTrueSaltyHash = u.getSaltyHash();
		System.out.println("session obj - end pass: " + curSaltyHash);
				
		System.out.println("Do results match? " + curSaltyHash.equals(theTrueSaltyHash));
		
		if(udb.passwordIsValid(u, passAuth)) {
			status = 0;
		}
		
		System.out.println("Return Status: " + status);
		
		PrintWriter out = response.getWriter();
		out.println(status);
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
		log.info("Password Authentication Servlet Has Closed");
	}

}
