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

import model.Order;
import model.OrderDB;

/**
 * Servlet implementation class PlaceOrder
 */
@WebServlet("/PlaceOrder")
public class PlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		double total = (double) session.getAttribute("total");
		List<Order> orders = (List<Order>) session.getAttribute("shoppingCart");
		OrderDB orderDB = new OrderDB();

		orderDB.addOrdersToUser(orders, total);
		session.setAttribute("completeOrder", orders);
		session.setAttribute("recentTotal", total);
		session.removeAttribute("shoppingCart");
		session.removeAttribute("total");
		
		
		StringBuilder sb = new StringBuilder();
		
		String tableStart = "<table class=\"table table-bordered table-striped\">\r\n" + 
							"<thead>\r\n" + 
							"<th>Movie</th>\r\n" + 
							"<th># of Tickets</th>\r\n" + 
							"<th>Total</th>\r\n" + 
							"<th>Theater Room</th>\r\n" + 
							"</thead>\r\n" + 
							"<tbody>";
		sb.append(tableStart);
		for(Order order: orders) {
			String cost = String.format("$%.2f", order.getCost());
			sb.append("<tr>\r\n" + 
					"<td>" + order.getMovieShowing().getMovie().getTitle() + "</td>\r\n" + 
					"<td>" + order.getTicketQuantity() + "</td>\r\n" + 
					"<td>" + cost + "</td>\r\n" + 
					"<td>" + order.getMovieShowing().getShowroom().getTheater().getName() + "</td>\r\n" + 
					"</tr>");
		}
		
		String totalStr = String.format("$%.2f", total);
		sb.append("</tbody>\r\n" + 
				"</table>\r\n" + 
				"<br>\r\n" + 
				"<h3 id=\"total\" value=\"${total}\">Total: " + totalStr + "</h3>");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println(sb.toString());
	}

}
