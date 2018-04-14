package swe2016.g6t4.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe2016.g6t4.controller.AuctionManagement;

/**
 * SearchAuctionByKeywordServlet sucht anhand eines vom User eingegebenen Keywords in den Attributen title und description der Auktionen
 * alle mit diesem Keyword und gibt sie als Liste aus auf der entsprechenden JSP.
 */
@WebServlet("/SearchAuctionByKeywordServlet")
public class SearchAuctionByKeywordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default-Constructor
     */
    public SearchAuctionByKeywordServlet() {
        super();
    }

	/**
	 * doGet leitet auf searchauctionbykeyword.jsp weiter, wo der User (muss nicht eingeloggt sein) ein Keyword eingeben kann.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher view = request.getRequestDispatcher("searchauctionbykeyword.jsp");
		view.forward(request, response);
	}

	/**
	 * doPost nimmt das eingegebene Keyword entgegen und leitet auf searchauctionbykeyword2.jsp weiter, wenn die Eingabe ok war.
	 * Wenn die Eingabe fehlerhaft war, wird auf index.jsp weitergeleitet und eine Fehlermeldung angezeigt.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		AuctionManagement am = new AuctionManagement();
		response.setContentType("text/html");
		String keywordToSearch = (String) request.getParameter("keyword");
		
		if(keywordToSearch==null || keywordToSearch.equals("")) {
			String message = "The keyword field was empty! Search could not be executed.";
			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}
		
		request.setAttribute("keywordToSearch", keywordToSearch);
		RequestDispatcher view = request.getRequestDispatcher("searchauctionbykeyword2.jsp");
		view.forward(request, response);
	}

}
