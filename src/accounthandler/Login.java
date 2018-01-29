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
	public Properties prop = new Properties();
	private ServletContext sc;
	private String filePath;

	@Override
	public void init() throws ServletException {
		lastModified = System.currentTimeMillis()/1000 * 1000;
		sc = this.getServletContext();
		filePath = sc.getRealPath("/WEB-INF/users.properties");
		
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(filePath);
			prop.load(fis);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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

		String email = ServletUtils.validateInput(request.getParameter("email"), "");
		String password = ServletUtils.validateInput(request.getParameter("password"), "");

		ServletContext sc = this.getServletContext();
		String propFilePath = sc.getRealPath("/WEB-INF/users.properties");

		Users user = new Users(email, password);

		String redirect = user.userExists(user, prop) && user.pwMatch(user, prop)
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
	
	private void connectionClosed() {
		try {
			String reportPath = sc.getRealPath("/data/reports.txt");
			FileWriter fWriter = new FileWriter(reportPath);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"); 
			Date date = new Date();
			fWriter.write("\nTime Stamp: " + dateFormat.format(date) );
			fWriter.write("Login Servlet Connection Closed");
			fWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
