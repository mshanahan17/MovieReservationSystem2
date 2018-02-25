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
		String pattern = "yyyy-mm-dd HH:mm";
		DateTime dt = new DateTime();
		dt.parse("2018-04-02 15:00", DateTimeFormat.forPattern(pattern));
		List<Movie> movies = (new MovieDB()).searchMovies("theater1", "the scared little kitten", "2018-04-04 17:30:00");
		HttpSession session = request.getSession();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(movies.get(0).getThumbnail(), "jpg", baos );
		baos.flush();
		byte[] imageInByteArray = baos.toByteArray();
		baos.close();
		String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
		request.getSession().setAttribute("thumbnail", b64);

		session.setAttribute("movies", movies);
		
		request.getRequestDispatcher("WEB-INF/Customer/MovieSearchResults.jsp").forward(request,  response);

	}

}
