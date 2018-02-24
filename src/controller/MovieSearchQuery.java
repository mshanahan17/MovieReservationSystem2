package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		System.out.println(date);

		HttpSession session = request.getSession();

		ArrayList<String> movies = (ArrayList<String>) session.getAttribute("movieList");

		if (movies == null) {
			movies = new ArrayList<String>();
			session.setAttribute("movieList", movies);
		}

		if ((query != null) && !(query.trim().equals(""))) {
			movies = new ArrayList<String>();
			session.removeAttribute("movieList");
			session.setAttribute("movieList", movies);
			movies.add(query);
			movies.add(query);
			movies.add(query);
			movies.add(query);
			movies.add(query);
		}
		
		request.getRequestDispatcher("WEB-INF/Customer/MovieSearchResults.jsp").forward(request,  response);

	}

}
