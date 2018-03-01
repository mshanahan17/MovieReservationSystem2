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
 * Servlet implementation class ManageOrder
 */
@WebServlet("/ManageOrder")
public class ManageOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageOrder() {
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
		String buttonVal = request.getParameter("button");
		session.removeAttribute("orderId");
		session.removeAttribute("singleOrder");
		session.removeAttribute("movieOrder");
		
		if(user == null) {
			request.getRequestDispatcher("Login.jsp").forward(request,  response);
			return;
		}
		
		if(buttonVal != null && buttonVal != "") {
			String[] buttonSplit = buttonVal.split(" ");
			int orderId = Integer.parseInt(buttonSplit[2]);
			List<Order> orderItems = new OrderDB().getOrdersByOrderId(orderId);
			session.setAttribute("orderItems", orderItems);
			session.setAttribute("orderId", buttonSplit[2]);
			Order order = new OrderDB().getOrderById(orderId);
			session.setAttribute("singleOrder", order);
		}
		
		request.getRequestDispatcher("WEB-INF/Customer/ManageOrder.jsp")
			   .forward(request, response);
	}

}
