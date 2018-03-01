package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Order;
import model.OrderDB;
import model.User;

/**
 * Servlet implementation class CancelOrderTransaction
 */
@WebServlet("/CancelOrderTransaction")
public class CancelOrderTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrderTransaction() {
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null) {
			request.getRequestDispatcher("Login.jsp").forward(request,  response);
			return;
		}
		
		Order movOrder = (Order) session.getAttribute("movieOrder");
		
		if(movOrder != null) {
			System.out.println(movOrder);
			OrderDB orderDB = new OrderDB();
			orderDB.removeOrderItem(movOrder);
		}
		request.getRequestDispatcher("WEB-INF/Customer/CancellationConfirmation.jsp")
			   .forward(request, response);
	}
	

}
