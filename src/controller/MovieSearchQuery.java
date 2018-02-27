package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import model.Movie;
import model.MovieDB;
import model.MovieShowing;
import model.MovieShowingDB;
import model.User;

/**
 * Servlet implementation class MovieSearchQuery
 */
@WebServlet("/MovieSearchQuery")
public class MovieSearchQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MovieSearchQuery() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String query = ServletUtils.validateInput(request.getParameter("search"), "");
		String theater = request.getParameter("theaters");
		String date = request.getParameter("showDate");

		HttpSession session = request.getSession();
		List<MovieShowing> movieShowings = (List<MovieShowing>) session.getAttribute("movieShowing");
		
		User user = (User) session.getAttribute("user");
		if(user == null) {
			request.getRequestDispatcher("Login.jsp").forward(request,  response);

		}
		
		if(movieShowings == null) {
			movieShowings = new MovieShowingDB().searchMovieShowings(theater, query, date);
		}

		session.setAttribute("movieShowings", movieShowings);
		
		request.getRequestDispatcher("WEB-INF/Customer/MovieSearchResults.jsp").forward(request,  response);

	}

}
