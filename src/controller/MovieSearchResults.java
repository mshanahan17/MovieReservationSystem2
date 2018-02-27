package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import model.Movie;
import model.MovieDB;
import model.MovieShowing;
import model.Review;
import model.ReviewDB;
import model.User;

/**
 * Servlet implementation class MovieSearchResults
 */
@WebServlet("/MovieSearchResults")
public class MovieSearchResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieSearchResults() {
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
		String button0 = request.getParameter("button0");
		String button1 = request.getParameter("button1");
		String button2 = request.getParameter("button2");
		String button3 = request.getParameter("button3");
		String button4 = request.getParameter("button4");
		HttpSession session = request.getSession();
		
		List<MovieShowing> movieShowings = (List<MovieShowing>) session.getAttribute("movieShowings");
		List<Review> reviews = null;
		
		User user = (User) session.getAttribute("user");
		if(user == null) {
			request.getRequestDispatcher("Login.jsp").forward(request,  response);
			return;
		}
		
		if(button0 != null) {
			session.setAttribute("movie", movieShowings.get(0));
			reviews = new ReviewDB().
					getReviewsByMovieTitle(movieShowings.get(0).getMovie().getTitle());
		}
		else if(button1 != null) {
			session.setAttribute("movie", movieShowings.get(1));
			reviews = new ReviewDB().
					getReviewsByMovieTitle(movieShowings.get(1).getMovie().getTitle());
		}
		else if(button2 != null) {
			session.setAttribute("movie", movieShowings.get(2));
			reviews = new ReviewDB().
					getReviewsByMovieTitle(movieShowings.get(2).getMovie().getTitle());
		}
		else if(button3 != null) {
			session.setAttribute("movie", movieShowings.get(3));
			reviews = new ReviewDB().
					getReviewsByMovieTitle(movieShowings.get(3).getMovie().getTitle());
		}
		else if(button4 != null) {
			session.setAttribute("movie", movieShowings.get(4));
			reviews = new ReviewDB().
					getReviewsByMovieTitle(movieShowings.get(4).getMovie().getTitle());
		}
		
		session.setAttribute("reviews", reviews);
		request.getRequestDispatcher("WEB-INF/Customer/MovieDetailsSelection.jsp")
			   .forward(request, response);
	}

}
