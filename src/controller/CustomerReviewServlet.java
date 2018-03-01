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
import model.MovieShowing;
import model.Review;
import model.ReviewDB;
import model.User;

/**
 * Servlet implementation class CustomerReview
 */
@WebServlet("/CustomerReview")
public class CustomerReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerReviewServlet() {
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
		
		String successResponse = "Your Review Was Successfully Submitted!";
		String failResponse = "An error was encountered submitting your review!";
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
		String confirmationResponse;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		MovieShowing movieShowing = (MovieShowing) session.getAttribute("movie");
		if(user == null) {
			request.getRequestDispatcher("Login.jsp")
			   .forward(request, response);
			return;
		}
		
		Review movieReview = new Review();
		movieReview.setContent(review);
		movieReview.setDate(date);
		movieReview.setMovie(movieShowing.getMovie());
		movieReview.setRating(rating);
		movieReview.setUser(user);
		
		if(new ReviewDB().addReview(movieReview)) {
			confirmationResponse = successResponse;
		}
		else {
			confirmationResponse = failResponse;
		}
		session.setAttribute("review", confirmationResponse);
		
		request.getRequestDispatcher("WEB-INF/Customer/CustomerReviewConfirmation.jsp")
			   .forward(request, response);
	}

}
