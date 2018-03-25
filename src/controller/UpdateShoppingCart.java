package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MovieShowing;
import model.MovieShowingDB;
import model.Order;
import model.User;

/**
 * Servlet implementation class UpdateShoppingCart
 */
@WebServlet("/UpdateShoppingCart")
public class UpdateShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateShoppingCart() {
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
		String path1 = getServletContext().getInitParameter("Customer Path");
		String path = path1 + "/ViewAndCheckoutShoppingCart.jsp";
		String qtyTickets = request.getParameter("ticketQty");
		String checkOut = request.getParameter("checkOut");
		HttpSession session = request.getSession();
		session.removeAttribute("cartError");
		session.removeAttribute("orderError");

		
		User user = (User) session.getAttribute("user");
		if(user == null) {
			request.getRequestDispatcher("Login.jsp").forward(request,  response);
			return;
		}
		if(checkOut != null && checkOut.equals("1")) {
			request.getRequestDispatcher(path).forward(request,  response);
			return;
		}
		
		MovieShowing movShow = (MovieShowing) session.getAttribute("movie");

		if(movShow == null || qtyTickets == null || qtyTickets == "") {
			request.getRequestDispatcher(path).forward(request,  response);
			return;
		}
		
		double total;
		if(session.getAttribute("total") == null) {
			total = 0;
		}
		else {
			total = (Double) session.getAttribute("total");
		}
		
		int numTickets = 0;
		
		
		PrintWriter out = response.getWriter();
		//prevents race conditions for purchasing last tickets.
		synchronized(this) {
			if(qtyTickets != null) {
				numTickets = Integer.parseInt(qtyTickets);
				int capacity = movShow.getShowroom().getCapacity();
				int available = capacity - movShow.getNumOfPurchasedSeats();
				if(numTickets > available) {
					String error = "Not enough tickets remaining, only " + available + " tickets remaining.";
					session.setAttribute("noCapacity", error);
//					request.getRequestDispatcher(path1 + "/MovieDetailsSelection.jsp").forward(request, response);
					out.println(error);
					return;
				}
				else {
					MovieShowingDB movDB = new MovieShowingDB();
					movDB.updateNumberPurchasedSeats(movShow, numTickets);
					movShow.updatePurchasedSeatCount(numTickets);
				}
			}
		}

		
		ArrayList<Order> shoppingCart = (ArrayList<Order>) session.getAttribute("shoppingCart");
		
		if(shoppingCart == null) {
			shoppingCart = new ArrayList();
		}
		
		if(shoppingCart.size() > 4) {
			String orderError = "Maximum of 5 orders please remove an item from your cart to add another!";
			session.setAttribute("orderError", orderError);
			out.println(orderError);
			return;
		}
		Order order = new Order();
		double cost = movShow.getCost() * numTickets;
		order.setTicketQuantity(numTickets);
		order.setCost(cost);
		order.setMovieShowing(movShow);
		total += cost;
		order.setCustomer(user);
		order.setDate(movShow.getStartTime());
		shoppingCart.add(order);
		session.setAttribute("shoppingCart", shoppingCart);
		session.setAttribute("total", total);

		out.println(1);
	}

}
