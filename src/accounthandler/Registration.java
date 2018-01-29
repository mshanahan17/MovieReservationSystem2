package accounthandler;

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

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
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


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fName = ServletUtils.validateInput(request.getParameter("fname"), "");
        String lName = ServletUtils.validateInput(request.getParameter("lname"), "");
        
        String[] email = request.getParameterValues("email");
        String[] password = request.getParameterValues("password");
        ServletUtils.validateAllInput(email);
        ServletUtils.validateAllInput(password);
        
        Boolean emailMatch = ServletUtils.validMatch(email[0], email[1]);
        Boolean passwordMatch = ServletUtils.validMatch(password[0], password[1]);
		
		
        if(emailMatch && passwordMatch){
            Users user = new Users(email[0], password[0]);
            user.newUser(user, prop, filePath);
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
	
	private void connectionClosed() {
		try {
			String reportPath = sc.getRealPath("/data/reports.txt");
			FileWriter fWriter = new FileWriter(reportPath);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"); 
			Date date = new Date();
			fWriter.write("\nTime Stamp: " + dateFormat.format(date) );
			fWriter.write("Registration Servlet Connection Closed");
			fWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
