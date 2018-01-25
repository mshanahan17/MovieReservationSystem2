package accounthandler;

import java.io.IOException;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = ServletUtils.validateInput(request.getParameter("email"), "");
        String password = ServletUtils.validateInput(request.getParameter("password"), "");
        
        if(Users.userExists(email)) {
        	if(Users.pwMatch(email, password)) {
        		response.sendRedirect("Customer/CustomerHomePage.jsp");
        	}
        	else {
                String pwError = "Passwords do not match!";
                request.setAttribute("pwError", pwError);
                request.getRequestDispatcher("Login.jsp").forward(request,response);
        	}
        }
        else {
            String emailError = "User Does Not Exist!";
            request.setAttribute("emailError", emailError);
            request.getRequestDispatcher("Login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}
