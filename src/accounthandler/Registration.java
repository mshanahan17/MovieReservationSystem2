package accounthandler;

import java.io.IOException;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
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
            Users.newUser("Matt2", "Bigly");
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

}
