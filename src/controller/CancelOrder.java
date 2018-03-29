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
import model.User;

/**
 * Servlet implementation class CancelOrder
 */
@WebServlet("/CancelOrder")
public class CancelOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrder() {
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
		String button0 = request.getParameter("button0");
		String button1 = request.getParameter("button1");
		String button2 = request.getParameter("button2");
		String button3 = request.getParameter("button3");
		String button4 = request.getParameter("button4");
		
		HttpSession session = request.getSession();
		session.removeAttribute("cancelOrderError");
		session.removeAttribute("movieOrder");
		User user = (User) session.getAttribute("user");
		List<Order> orderItems = (List<Order>) session.getAttribute("orderItems");
		
		if(user == null) {
			request.getRequestDispatcher("Login.jsp").forward(request,  response);
			return;
		}
		if(orderItems == null) {
			String msg = "No order to cancel";
			session.setAttribute("cancelOrderError", msg);
			String path = getServletContext().getInitParameter("Customer Path");
			request.getRequestDispatcher(path + "/CancelOrder.jsp")
			   .forward(request, response);
			return;
		}
		
		Order movOrder = new Order();

		if(button0 != null && button0 != "") {
			movOrder = orderItems.get(0);
		}
		if(button1 != null && button1 != "") {
			movOrder = orderItems.get(1);
		}
		if(button2 != null && button2 != "") {
			movOrder = orderItems.get(2);
		}
		if(button3 != null && button3 != "") {
			movOrder = orderItems.get(3);
		}
		if(button4 != null && button4 != "") {
			movOrder = orderItems.get(4);
		}
		
		session.setAttribute("movieOrder", movOrder);
		request.getRequestDispatcher("WEB-INF/Customer/CancelOrder.jsp")
			   .forward(request, response);
	}

}
