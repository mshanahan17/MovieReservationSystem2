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
//		String pattern = "yyyy-mm-dd HH:mm";
//		DateTime dt = new DateTime();
//		DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
//		dt = dt.parse("2018-04-02 15:00:00");
//		String fmt = dtf.print(dt);
		List<Movie> movies = (new MovieDB()).searchMovies(theater, query, date);
		HttpSession session = request.getSession();

		session.setAttribute("movies", movies);
		
		request.getRequestDispatcher("WEB-INF/Customer/MovieSearchResults.jsp").forward(request,  response);

	}

}
