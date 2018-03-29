package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MovieShowingDB;
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
		session.removeAttribute("ccError");
		session.removeAttribute("transactionError");
		String removeItem = request.getParameter("removeItem");
		boolean orderRemoved = false;
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			return;
		}
		
		List<Order> orders = (List<Order>) session.getAttribute("shoppingCart");
		double total = 0;
		
		if(session.getAttribute("total") != null) {
			total = (double) session.getAttribute("total");
		}
		
		MovieShowingDB movDB = new MovieShowingDB();
		int numTix;
		session.removeAttribute("movie");
		
		if(removeItem != null) {
			int index = Integer.parseInt(removeItem);
			
			total -= orders.get(index).getCost();
			numTix = -orders.get(index).getTicketQuantity();

			movDB.updateNumberPurchasedSeats(orders.get(index).getMovieShowing()
					, numTix);
			orders.remove(index);
			orderRemoved = true;
		}
		
		String path = getServletContext().getInitParameter("Customer Path");
		PrintWriter out = response.getWriter();
		
		if(orderRemoved) {
			session.setAttribute("total", total);
			StringBuilder sb = new StringBuilder();
			sb.append("<table class=\"table table-bordered table-striped\">\r\n" + 
					"<thead>\r\n" + 
					"<th>Movie</th>\r\n" + 
					"<th>Poster</th>\r\n" + 
					"<th>Theater</th>\r\n" + 
					"<th>Showtime</th>\r\n" + 
					"<th># of Tickets</th>\r\n" + 
					"<th>Total</th>\r\n" + 
					"<th>Remove</th>\r\n" + 
					"</thead>\r\n" + 
					"<tbody>");
			
			int count = 0;
			for(Order order: orders) {
				
				String totalCost = String.format("$%.2f", order.getCost());
				String title = order.getMovieShowing().getMovie().getTitle();
				String thumbnail = order.getMovieShowing().getMovie().getThumbnail();
				String theaterName = order.getMovieShowing().getShowroom().getTheater().getName();
				String startTime = order.getMovieShowing().getStartTime();
				int ticketQty = order.getTicketQuantity();
				sb.append("<tr>\r\n" + 
						"<td>" + title + "</td>\r\n" + 
						"<td><img src=\"data:image/gif; base64," + thumbnail + "\" style=\"max-height: 75px\"></td>\r\n" + 
						"<td>" + theaterName + "</td>\r\n" + 
						"<td>" + startTime + "</td>\r\n" + 
						"<td>" + ticketQty + "</td>\r\n" + 
						"<td>" + totalCost + "\r\n" + 
						"<td>\r\n" + 
						"<button onclick='remove(this)'  value='" + count++ + "'>Remove</button>\r\n" + 
						"</td>\r\n" + 
						"</tr>");
			}
			
			String updatedTotal = String.format("$%.2f", total);
			sb.append("</tbody>\r\n" + 
					  "</table>\r\n" +
					"<form action='Checkout' method='post'>\r\n" + 
					"<h3>Total: " + updatedTotal + "</h3>\r\n" + 
					"<br>\r\n" + 
					"<input type='submit' value='Checkout'>\r\n" + 
					"</form>");
			response.setContentType("text/html");
			out.println(sb.toString());
			return;
		}
		
		if(total == 0) {
			String errorMsg = "No items in cart.";
			session.setAttribute("cartError", errorMsg);
			request.getRequestDispatcher(path + "/ViewAndCheckoutShoppingCart.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher(path + "/ConfirmOrder.jsp").forward(request, response);
	}

}
