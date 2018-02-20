package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerTransactionConfirmation
 */
@WebServlet("/CustomerTransactionConfirmation")
public class CustomerTransactionConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerTransactionConfirmation() {
        super();
        // TODO Auto-generated constructor stub
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
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String ccType = request.getParameter("ccType");
		String ccNum = request.getParameter("ccNum");
		String secCode = request.getParameter("secCode");
		String date = request.getParameter("date");
		String billStreet = request.getParameter("billStreet");
		String billCity = request.getParameter("billCity");
		String billState = request.getParameter("billState");
		String billZip = request.getParameter("billZip");
		String shipStreet = request.getParameter("shipStreet");
		String shipCity = request.getParameter("shipCity");
		String shipState = request.getParameter("shipState");
		String shipZip = request.getParameter("shipZip");
		
		request.getRequestDispatcher("WEB-INF/Customer/CustomerTransactionConfirmation.jsp").forward(request,  response);
	}

}
