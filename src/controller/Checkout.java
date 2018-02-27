package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Order;
import model.ReviewDB;
import model.User;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
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
		session.removeAttribute("cartError");
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			request.getRequestDispatcher("WEB-INF/Customer/Login.jsp").forward(request, response);
		}
		
		String button0 = request.getParameter("button0");
		String button1 = request.getParameter("button1");
		String button2 = request.getParameter("button2");
		String button3 = request.getParameter("button3");
		String button4 = request.getParameter("button4");
		boolean orderRemoved = false;
		List<Order> orders = (List<Order>) session.getAttribute("partialOrders");
		double total = (double) session.getAttribute("total");
		
		if(button0 != null) {
			total -= orders.get(0).getCost();
			orders.remove(0);
			orderRemoved = true;
		}
		else if(button1 != null) {
			total -= orders.get(1).getCost();
			orders.remove(1);
			orderRemoved = true;
		}
		else if(button2 != null) {
			total -= orders.get(2).getCost();
			orders.remove(2);
			orderRemoved = true;
		}
		else if(button3 != null) {
			total -= orders.get(3).getCost();
			orders.remove(3);
			orderRemoved = true;
		}
		else if(button4 != null) {
			total -= orders.get(4).getCost();
			orders.remove(4);
			orderRemoved = true;
		}

		if(orderRemoved) {
			session.setAttribute("total", total);
			request.getRequestDispatcher("WEB-INF/Customer/ViewAndCheckoutShoppingCart.jsp").forward(request, response);
			return;
		}
		if(total == 0) {
			String errorMsg = "No items in cart.";
			session.setAttribute("cartError", errorMsg);
			request.getRequestDispatcher("WEB-INF/Customer/ViewAndCheckoutShoppingCart.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("WEB-INF/Customer/CustomerTransaction.jsp").forward(request, response);
	}

}
