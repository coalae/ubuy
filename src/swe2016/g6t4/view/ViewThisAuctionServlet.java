package swe2016.g6t4.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ViewThisAuctionServlet zeigt die Einzelansicht einer Auktion an.
 */
@WebServlet("/ViewThisAuctionServlet")
public class ViewThisAuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default-Constructor
     */
    public ViewThisAuctionServlet() {
        super();
    }

	/**
	 * doGet leitet mittels Parameter der AuctionId auf viewthisauction.jsp weiter, wo man eine Einzelansicht bekommt - man muss dafür
	 * nicht zwingend eingeloggt sein.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String auctionid=request.getParameter("param1");
		request.setAttribute("auctionid", auctionid);
		RequestDispatcher view = request.getRequestDispatcher("viewthisauction.jsp");
		view.forward(request, response);
 	}

	/**
	 * doPost macht die Ausführung von doGet.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
