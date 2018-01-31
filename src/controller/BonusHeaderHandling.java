package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BonusHeaderHandling
 */
@WebServlet("/BonusHeaderHandling")
public class BonusHeaderHandling extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BonusHeaderHandling() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acceptHeaderString = request.getHeader("accept");
		
        boolean isChrome = false;
        if(acceptHeaderString.contains("webp")) {
     	   isChrome = true;
        }
        
        response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println(ServletUtils.headWithTitle("Webp Image"));
		out.println
	      ("<BODY BGCOLOR=\"WHITE\">\n" +
	       "<H1 ALIGN=\"CENTER\">Star Wars</H1>\n");
	    if(isChrome) {
	       out.println("<H3> A webp formatted image.</H3>");
	       out.println("<CENTER><IMG SRC=pics/starwars.webp height=\"500\" width=\"300\"></CENTER>");
	    } else {
	    	   out.println("<H2>Sorry You Aren't Using Chrome Browser!</H2>");
	    }
	    
	    out.println("</BODY></HTML>");
	    out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
