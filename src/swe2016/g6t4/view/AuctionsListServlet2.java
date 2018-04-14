package swe2016.g6t4.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AuctionsListServlet2 leitet auf auctionslist2.jsp weiter.
 */
@WebServlet("/AuctionsListServlet2")
public class AuctionsListServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default-Constructor
     */
    public AuctionsListServlet2() {
        super();
    }

	/**
	 * doGet leitet auf auctionslist2.jsp weiter.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("auctionslist2.jsp");
		view.forward(request, response);
	}

	/**
	 * doPost macht die Ausführung von doGet.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
