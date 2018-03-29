package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

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
@WebServlet("/CustomerReviewServlet")
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
				request.getParameter("review".trim()), "");
		String rating = request.getParameter("rating");
		
		String successResponse = "Your Review Was Successfully Submitted!";
		String failResponse = "An error was encountered submitting your review!";
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
		boolean submissionSuccess = false;
		
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
		
		StringBuilder sb = new StringBuilder();
		if(new ReviewDB().addReview(movieReview, rating)) {
			submissionSuccess = true;
			ReviewDB reviewDb = new ReviewDB();
			String movieTitle = movieShowing.getMovie().getTitle();
			List<Review> reviews = reviewDb.getReviewsByMovieTitle(movieTitle);
			
			
			sb.append("<table class=\"table table-bordered\">\r\n" + 
					  "<tbody id=\"reviews\">");
			
			for(Review reviewEntry: reviews) {
				
				String fName = reviewEntry.getUser().getFirstName();
				String lName = reviewEntry.getUser().getLastName();
				String revDate = reviewEntry.getDate();
				String revCont = reviewEntry.getContent();
				String revRating = reviewEntry.getRating();
				
				sb.append("<tr>\r\n" + 
						  "<td>" + fName + " " + lName + "</td>\r\n" + 
						  "<td>" + revDate + "</td>\r\n" + 
						  "<td>" + revCont + "</td>\r\n" + 
						  "<td>" + revRating + "</td>\r\n" + 
						  "</tr>");
			}
			
			sb.append("</tbody>\r\n" + 
					  "</table>");
		}
		
		PrintWriter out = response.getWriter();
		if(submissionSuccess) {
			response.setContentType("text/html");
			out.println(sb.toString());
			return;
		}
		else {
			out.println(0);
			return;
		}
//		request.getRequestDispatcher("WEB-INF/Customer/CustomerReviewConfirmation.jsp")
//			   .forward(request, response);
	}

}
