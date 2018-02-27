package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Movie;
import model.Review;
import model.User;

/**
 * Servlet implementation class CustomerReview
 */
@WebServlet("/CustomerReview")
public class CustomerReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerReview() {
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
		String review = ServletUtils.validateInput(
				request.getParameter("review"), "");
		String rating = request.getParameter("rating");
		
		String confirmationResponse = "Success";
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
		System.out.println(date);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Movie movie = (Movie) session.getAttribute("movie");
		if(user == null) {
			request.getRequestDispatcher("Login.jsp")
			   .forward(request, response);
		}
		
		Review movieReview = new Review();
		movieReview.setContent(review);
		movieReview.setDate(date);
		movieReview.setMovie(movie);
		movieReview.setRating(rating);
		movieReview.setUser(user);
		
		session.setAttribute("review", confirmationResponse);
		
		request.getRequestDispatcher("WEB-INF/Customer/CustomerReviewConfirmation.jsp")
			   .forward(request, response);
	}

}
