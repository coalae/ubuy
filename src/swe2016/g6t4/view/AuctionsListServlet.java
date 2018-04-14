package swe2016.g6t4.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AuctionsListServlet zeigt eine Liste aller aktiven Auktionen an.
 */
@WebServlet("/AuctionsListServlet")
public class AuctionsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default-Constructor
     */
    public AuctionsListServlet() {
        super();
    }

	/**
	 * doGet leitet auf auctionlist.jsp weiter.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("auctionslist.jsp");
		view.forward(request, response);
	}

	/**
	 * doPost macht die Ausführung von doGet.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
