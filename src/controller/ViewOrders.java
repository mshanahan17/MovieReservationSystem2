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
import model.OrderDB;
import model.User;

/**
 * Servlet implementation class ViewOrders
 */
@WebServlet("/ViewOrders")
public class ViewOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOrders() {
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
		String path = getServletContext().getInitParameter("Customer Path");
		path = path + "/ViewOrders.jsp";
		HttpSession session = request.getSession();
		session.removeAttribute("noOrders");
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			request.getRequestDispatcher("Login.jsp").forward(request,  response);
			return;
		}
		
		List<Order> orders = new OrderDB().getOrdersByUser(user);
		
		if(orders == null) {
			session.setAttribute("noOrders", "You do not have any orders yet!");
		}
		session.setAttribute("pastOrders", orders);
		request.getRequestDispatcher(path).forward(request, response);
	}

}
