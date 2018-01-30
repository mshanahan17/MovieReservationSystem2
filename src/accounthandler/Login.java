package accounthandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private long lastModified;
	private FileWriter fWriter = null;

	@Override
	public void init() throws ServletException {
		lastModified = System.currentTimeMillis()/1000 * 1000;
		
		/* initialize filewriter for log writing. Writes
		 * the time login servlet is started.
		 * Then destroy method writes when it is closed and
		 * closes filewriter resources
		 */
		ServletContext sc = this.getServletContext();
		String reportPath = sc.getRealPath("/data/reports.txt");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"); 
		Date date = new Date();
		try {
			fWriter = new FileWriter(reportPath);
			fWriter.write("\nTime Stamp: " + dateFormat.format(date) );
			fWriter.write("Login Servlet Connection Opened");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected long getLastModified(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return this.lastModified;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * recieves and validates login information
		 */
		String email = ServletUtils.validateInput(request.getParameter("email"), "");
		String password = ServletUtils.validateInput(request.getParameter("password"), "");

		Users user = new Users(email, password);

		ServletContext sc = this.getServletContext();
		String filePath = sc.getRealPath("/WEB-INF/users.properties");
		
		/*
		 * redirects to correct webpage depending on if login info
		 * was correct or not.
		 */
		String redirect = user.userExists(user, filePath) && user.pwMatch(user, filePath)
				? "Customer/CustomerHomePage.jsp"
				: "Registration.jsp";

		response.sendRedirect(redirect);

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
	 * when servlet connection is terminated this method will 
	 * write the time stamp and messaage then close the filewriter
	 * resources
	 */
	private void connectionClosed() {

			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"); 
			Date date = new Date();
			try {
				fWriter.write("\nTime Stamp: " + dateFormat.format(date) );
				fWriter.write("Login Servlet Connection Closed");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(fWriter != null) {
					try {
						fWriter.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

	}

}
