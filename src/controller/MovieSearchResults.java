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
		HttpSession session = request.getSession();
		List<Movie> movies = (List<Movie>) session.getAttribute("movies");
		if(button0 != null) {
			session.setAttribute("movie", movies.get(0));
		}
		request.getRequestDispatcher("WEB-INF/Customer/MovieDetailsSelection.jsp")
			   .forward(request, response);
	}

}
